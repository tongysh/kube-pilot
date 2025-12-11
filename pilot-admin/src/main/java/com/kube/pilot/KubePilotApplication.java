package com.kube.pilot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;

/**
 * 启动程序
 *
 * @author tongysh
 */

@SpringBootApplication
public class KubePilotApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(KubePilotApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
        System.out.println("kube pilot application started");
    }

}
