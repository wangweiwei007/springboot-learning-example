package spring.mybatis.generator.util;


import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ObjectUtils;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisGeneratorUtil {

    // generatorConfig模板路径
    private static String generatorConfig_vm = "/template/generatorConfig.vm";
    // Service模板路径
    private static String service_vm = "/template/Service.vm";
    // ServiceMock模板路径
    private static String serviceMock_vm = "/template/ServiceMock.vm";
    // ServiceImpl模板路径
    private static String serviceImpl_vm = "/template/ServiceImpl.vm";

    /**
     * 根据模板生成generatorConfig.xml文件
     * @param jdbc_driver   驱动路径
     * @param jdbc_url      链接
     * @param jdbc_username 帐号
     * @param jdbc_password 密码
     * @param module        项目模块
     * @param database      数据库
     * @param table_prefix  表前缀
     * @param package_name  包名
     */
    public static void generator(
            String jdbc_driver,
            String jdbc_url,
            String jdbc_username,
            String jdbc_password,
            String module,
            String database,
            String table_prefix,
            String package_name,
            Map<String, String> last_insert_id_tables) throws Exception{

        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
            //service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
            //serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
            //serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");
        } else {
            generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
            //service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
            //serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
            //serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
        }

        String targetProject = module; //+ "/" + module + "-dao";
        String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "").replaceFirst("/", "");
        String generatorConfig_xml = MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
        targetProject = basePath ;// + targetProject;
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name LIKE '" + table_prefix + "_%';";

        System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
        List<Map<String, Object>> tables = new ArrayList<Map<String, Object>>();
        try {
            VelocityContext context = new VelocityContext();
            Map<String, Object> table;

            // 查询定制前缀项目的所有表
            JdbcUtil jdbcUtil = new JdbcUtil(jdbc_driver, jdbc_url, jdbc_username, jdbc_password);
            List<Map> result = jdbcUtil.selectByParams(sql, null);
            for (Map map : result) {
                System.out.println(map.get("TABLE_NAME"));
                table = new HashMap<String, Object>();
                table.put("table_name", map.get("TABLE_NAME"));
                table.put("model_name", StringUtil.lineToHump(map.get("TABLE_NAME").toString()));
                tables.add(table);
            }
            jdbcUtil.release();

            String targetProject_sqlMap = basePath + module ;//+ "/" + module + "-rpc-service";
            context.put("tables", tables);
            context.put("generator_javaModelGenerator_targetPackage", package_name + ".dao.model");
            context.put("generator_sqlMapGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("generator_javaClientGenerator_targetPackage", package_name + ".dao.mapper");
            context.put("targetProject", targetProject);
            context.put("targetProject_sqlMap", targetProject_sqlMap);
            context.put("generator_jdbc_password", jdbc_password);
            context.put("last_insert_id_tables", last_insert_id_tables);
            VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
            // 删除旧代码
            //deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/model"));
            //deleteDir(new File(targetProject + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
            //deleteDir(new File(targetProject_sqlMap + "/src/main/java/" + package_name.replaceAll("\\.", "/") + "/dao/mapper"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

        System.out.println("========== 开始运行MybatisGenerator ==========");
        List<String> warnings = new ArrayList<String>();
        File configFile = new File(generatorConfig_xml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }

    }

    // 递归删除非空文件夹
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteDir(files[i]);
            }
        }
        dir.delete();
    }
}
