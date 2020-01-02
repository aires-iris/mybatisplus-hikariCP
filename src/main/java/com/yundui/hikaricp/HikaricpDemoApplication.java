package com.yundui.hikaricp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fzx
 */
@SpringBootApplication
@MapperScan(basePackages = "com.yundui.hikaricp.mapper")
public class HikaricpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HikaricpDemoApplication.class, args);
    }

}
