package org.example2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        SimpleJdbcCallExample example = context.getBean(SimpleJdbcCallExample.class);
        example.example();

    }
}