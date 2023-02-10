package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        JdbcTemplateExample example = context.getBean(JdbcTemplateExample.class);
        example.select();
        example.insert();
        example.update();
        example.delete();
        example.insert();
        example.select();
    }
}