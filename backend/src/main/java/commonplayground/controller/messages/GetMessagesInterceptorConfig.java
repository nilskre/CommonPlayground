package commonplayground.controller.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class GetMessagesInterceptorConfig implements WebMvcConfigurer {

    private GetMessagesInterceptor getMessagesInterceptor;

    @Autowired
    public GetMessagesInterceptorConfig(GetMessagesInterceptor getMessagesInterceptor) {
        this.getMessagesInterceptor = getMessagesInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getMessagesInterceptor).addPathPatterns("/getMyMessages");
    }
}
