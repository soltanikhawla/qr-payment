package com.DataMappingApplication.Service;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class GraphAuthenticationConfig {




    @Bean
    public IAuthenticationProvider authProvider() {
        return new GraphAuthenticationProvider("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Im5nLW1hdGVybyIsIm5hbWUiOiJab25nYmluIiwiZW1haWwiOiJuemIzMjlAMTYzLmNvbSIsImF2YXRhciI6Ii4vYXNzZXRzL2ltYWdlcy9hdmF0YXIuanBnIn19.bmctbWF0ZXJv");
    }
}