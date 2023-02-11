package org.example3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);

        NamedParameterTemplateExamples example = context.getBean(NamedParameterTemplateExamples.class);
        example.example();

    }
}