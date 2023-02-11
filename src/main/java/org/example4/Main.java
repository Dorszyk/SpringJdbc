package org.example4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        TransactionTemplateExample jdbcTemplateExamples = context.getBean(TransactionTemplateExample.class);
        jdbcTemplateExamples.example1();
        jdbcTemplateExamples.example2();
    }
}