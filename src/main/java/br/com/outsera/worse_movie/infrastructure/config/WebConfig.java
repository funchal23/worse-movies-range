package br.com.outsera.worse_movie.infrastructure.config;

import br.com.outsera.worse_movie.infrastructure.rest.interceptor.RangeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RangeInterceptor rangeInterceptor;

    public WebConfig(RangeInterceptor rangeInterceptor) {
        this.rangeInterceptor = rangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rangeInterceptor)
                .addPathPatterns("/ranges/**");
    }
}
