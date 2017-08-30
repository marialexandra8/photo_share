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
    @Value("${images.rootPath}")
    private String imagesRoot;
    @Value("${images.users.logo.rootPath}")
    private String usersRoot;
    @Value("${images.contests.logo.rootPath}")
    private String contestsRoot;
    @Value("${images.users.contest.url}")
    private String usersUploads;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/swagger/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/users/logos/**").addResourceLocations("file:" + imagesRoot + usersRoot + "/");
        registry.addResourceHandler("/users/contests/**").addResourceLocations("file:" + imagesRoot + usersUploads + "/");
        registry.addResourceHandler("/contests/logos/**").addResourceLocations("file:" + imagesRoot + contestsRoot + "/");
        registry.addResourceHandler("/swagger/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);

    }
}
