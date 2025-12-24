package k23cnt3.QxtMerryChristmas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebStaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:E:/Project3/k23cnt3.QxtMerryChristmas/uploads/");
    }
}
// @Configuration
//public class WebStaticConfig implements WebMvcConfigurer
//*/@Configuration
//→ Đánh dấu đây là class cấu hình cho Spring Boot
//
//🔹 WebMvcConfigurer
//→ Cho phép can thiệp cấu hình MVC, cụ thể là cách Spring phục vụ file tĩnh