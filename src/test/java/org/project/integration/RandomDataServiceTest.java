package org.project.integration;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.business.*;
import org.project.domain.*;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.List;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RandomDataServiceTest {

    private RandomDataService randomDataService;

    private CustomerService customerService;

    private OpinionService opinionService;

    private ProducerService producerService;

    private ProductService productService;

    private PurchaseService purchaseService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(randomDataService);
        Assertions.assertNotNull(customerService);
        Assertions.assertNotNull(opinionService);
        Assertions.assertNotNull(producerService);
        Assertions.assertNotNull(productService);
        Assertions.assertNotNull(purchaseService);
        customerService.removeAll();
        producerService.removeAll();
    }

    @Test
    @DisplayName("Polecenie 3")
    void thatRandomDataIsInsertedCorrectly() {
        // given
        List<Customer> allCustomersBefore = customerService.findAll();
        List<Opinion> allOpinionsBefore = opinionService.findAll();
        List<Producer> allProducersBefore = producerService.findAll();
        List<Product> allProductsBefore = productService.findAll();
        List<Purchase> allPurchasesBefore = purchaseService.findAll();

        // when
        randomDataService.create();

        // then
        List<Customer> allCustomersAfter = customerService.findAll();
        List<Opinion> allOpinionsAfter = opinionService.findAll();
        List<Producer> allProducersAfter = producerService.findAll();
        List<Product> allProductsAfter = productService.findAll();
        List<Purchase> allPurchasesAfter = purchaseService.findAll();

        Assertions.assertEquals(allCustomersBefore.size() + 1, allCustomersAfter.size());
        Assertions.assertEquals(allOpinionsBefore.size() + 1, allOpinionsAfter.size());
        Assertions.assertEquals(allProducersBefore.size() + 1, allProducersAfter.size());
        Assertions.assertEquals(allProductsBefore.size() + 1, allProductsAfter.size());
        Assertions.assertEquals(allPurchasesBefore.size() + 1, allPurchasesAfter.size());
    }
}
