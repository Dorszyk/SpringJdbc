package org.example1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        SimpleJdbcInsertExample example = context.getBean(SimpleJdbcInsertExample.class);
        example.simpleJdbcInsertExample();

    }
}