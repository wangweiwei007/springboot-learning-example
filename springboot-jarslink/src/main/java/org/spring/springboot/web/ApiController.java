package org.spring.springboot.web;

import com.alipay.jarslink.api.Action;
import com.alipay.jarslink.api.Module;
import com.alipay.jarslink.api.ModuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: weiwei wang
 * @date: 2019/3/29
 * @description:
 */

@RestController("/api")
public class ApiController {

    @Value("${springboot.jarslink.project.name}")
    private String projectName;

    @Autowired
    private ModuleManager moduleManager;

    public Object buildInfo(@RequestHeader String actionName, @RequestBody String data) {
        Module findModule = moduleManager.find(projectName);
        Action<String, String> action = findModule.getAction("invoiceAction");
        String result = action.execute(data);
        return result;
    }
}
