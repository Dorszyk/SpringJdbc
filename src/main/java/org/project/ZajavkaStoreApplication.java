package org.project;


import org.project.business.ReloadDataService;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ZajavkaStoreApplication {
    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ReloadDataService reloadDataService = context.getBean(ReloadDataService.class);
        reloadDataService.reloadData();

    }
}
