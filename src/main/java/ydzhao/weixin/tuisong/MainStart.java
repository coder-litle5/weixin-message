package ydzhao.weixin.tuisong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *@ClassName MainStart
 *@Description TODO
 *@Author ydzhao
 *@Date 2022/8/2 15:44
 */

@SpringBootApplication
@EnableScheduling
public class MainStart extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MainStart.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }
}
