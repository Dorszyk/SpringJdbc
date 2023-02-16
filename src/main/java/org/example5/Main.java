package org.example5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        TransactionTemplateExample jdbcTemplateExamples = context.getBean(TransactionTemplateExample.class);
        jdbcTemplateExamples.someMethod();
        jdbcTemplateExamples.someVoidMethod();
    }
}