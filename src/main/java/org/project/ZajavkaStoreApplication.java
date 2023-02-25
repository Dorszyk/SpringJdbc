package org.project;


import org.project.business.CustomerService;
import org.project.business.RandomDataService;
import org.project.domain.Customer;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ZajavkaStoreApplication {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        RandomDataService randomDataService = context.getBean(RandomDataService.class);
        randomDataService.create();

        CustomerService customerService = context.getBean(CustomerService.class);
        List<Customer> allCustomers = customerService.findAll();
        System.out.println(allCustomers);

    }
}
