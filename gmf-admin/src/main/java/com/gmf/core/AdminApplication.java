package com.gmf.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * @author gmf
 * @date 2020/12/31 10:35
 * @descrition:
 */
@SpringBootApplication
//@MapperScan("com.gmf.core.admin.mapper")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
