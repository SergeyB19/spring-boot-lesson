package com.dmdev.springboot.lesson.config;

import com.dmdev.springboot.lesson.conditional.FirstConditional;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Slf4j
@Conditional(FirstConditional.class)
//@Profile("dev")
@Configuration
public class ApplicationConfiguration {
//    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);
    @PostConstruct
    public void init() {
        log.warn("app is loaded!!!");

    }
}
