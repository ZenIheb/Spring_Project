package Models.Assetes;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/server/**") // Adjust the mapping pattern to match your API endpoints
            .allowedOrigins("http://localhost:3000") // Allow requests from this origin
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
            .allowedHeaders("Content-Type", "Authorization" ,"Access-Control-Allow-Origin") // Allowed headers
            .allowCredentials(true); // Allow cookies and other credentials
    }
}
