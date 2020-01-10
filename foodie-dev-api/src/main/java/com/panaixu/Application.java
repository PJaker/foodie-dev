package com.panaixu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @PACKAGE_NAME: com.panaixu
 * @Auther: PJaker
 * @DATE: 2019/12/23
 * @TIME: 14:57
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.panaixu.mapper")
//扫描所有包，以及相关组件包
@ComponentScan(basePackages = {"com.panaixu","org.n3r.idworker"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
