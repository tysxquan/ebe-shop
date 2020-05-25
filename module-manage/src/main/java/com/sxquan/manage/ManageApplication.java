package com.sxquan.manage;


        import org.mybatis.spring.annotation.MapperScan;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
        import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title ManageApplication
 * @Description 启动类
 * @Author sxquan
 * @Date 2019/12/8 16:43
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.sxquan.manage.*.mapper")
public class ManageApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class,args);
    }

    //war 打包继承
    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    //     return application.sources(ManageApplication.class);
    // }

}
