package pl.grabinski.slayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.WebJarsResourceResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/webjars/")
                .setCacheControl(CacheControl.maxAge(30L, TimeUnit.DAYS).cachePublic())
                .resourceChain(true)
                .addResolver(new WebJarsResourceResolver());
    }
}