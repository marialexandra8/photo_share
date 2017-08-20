package com.maria.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created on 8/19/2017.
 */
@Configuration
public class StaticResourcesConfig extends WebMvcConfigurerAdapter {
    @Value("${files.rootPath}")
    private String imagesRoot;
    @Value("${files.users.rootFolder}")
    private String usersRoot;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/swagger/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/users/**").addResourceLocations("image:" + imagesRoot + usersRoot + "/");
        registry.addResourceHandler("/swagger/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }
}
