package org.spring.springboot.conf;

import com.alipay.jarslink.api.ModuleLoader;
import com.alipay.jarslink.api.ModuleManager;
import com.alipay.jarslink.api.impl.ModuleLoaderImpl;
import com.alipay.jarslink.api.impl.ModuleManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModuleLoader moduleLoader() {
        return new ModuleLoaderImpl();
    }

    @Bean
    public ModuleManager moduleManager() {
        return new ModuleManagerImpl();
    }
}