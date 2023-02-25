package org.project.integration;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.business.CustomerService;
import org.project.business.ProducerService;
import org.project.business.ProductService;
import org.project.business.PurchaseService;
import org.project.business.ShoppingCartService;
import org.project.domain.Customer;
import org.project.domain.Producer;
import org.project.domain.Product;
import org.project.domain.Purchase;
import org.project.domain.StoreFixtures;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;


@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartServiceTest {

    private CustomerService customerService;
    private ShoppingCartService shoppingCartService;
    private ProducerService producerService;
    private PurchaseService purchaseService;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(customerService);
        Assertions.assertNotNull(shoppingCartService);
        Assertions.assertNotNull(producerService);
        Assertions.assertNotNull(purchaseService);
        customerService.removeAll();
        producerService.removeAll();
    }

    @Test
    @DisplayName("Polecenie9")
    void thatProductWasBoughtSuccessfully() {
        //given
        final Customer customer = customerService.create(StoreFixtures.someCustomer());
        final Producer producer = producerService.create(StoreFixtures.someProducer());
        final Product product1 = productService.create(StoreFixtures.someProduct(producer).withProductCode("g22s").withProductName("Shoes"));
        final Product product2 = productService.create(StoreFixtures.someProduct(producer).withProductCode("Kl09").withProductName("TV"));
        List<Purchase> before = purchaseService.findAll(customer.getEmail(), product1.getProductCode());

        //when
        shoppingCartService.makeAPurchase(customer.getEmail(), product1.getProductCode(), 54);

        //then
        List<Purchase> after = purchaseService.findAll(customer.getEmail(), product1.getProductCode());
        Assertions.assertEquals(before.size() + 1, after.size());
    }

}
