package org.project.integration;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.business.CustomerService;
import org.project.business.OpinionService;
import org.project.business.ProducerService;
import org.project.business.ProductService;
import org.project.business.PurchaseService;
import org.project.business.ReloadDataService;
import org.project.domain.Customer;
import org.project.domain.Opinion;
import org.project.domain.Producer;
import org.project.domain.Product;
import org.project.domain.Purchase;
import org.project.domain.StoreFixtures;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceTest {
    private ReloadDataService reloadDataService;
    private CustomerService customerService;
    private OpinionService opinionService;
    private ProducerService producerService;
    private ProductService productService;
    private PurchaseService purchaseService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(reloadDataService);
        Assertions.assertNotNull(customerService);
        Assertions.assertNotNull(purchaseService);
        Assertions.assertNotNull(opinionService);
        Assertions.assertNotNull(producerService);
        Assertions.assertNotNull(productService);
        reloadDataService.reloadData();
    }

    @Test
    @DisplayName("Polecenie 4 cz.1")
    void thatCustomerIsRemovedCorrectly() {
        //given
        final Customer customer = customerService.create(StoreFixtures.someCustomer().withDateOfBirth(LocalDate.of(1950, 10, 4)));
        final Producer producer = producerService.create(StoreFixtures.someProducer());
        final Product product1 = productService.create(StoreFixtures.someProduct(producer).withProductCode("g22s").withProductName("Shoes"));
        final Product product2 = productService.create(StoreFixtures.someProduct(producer).withProductCode("Kl09").withProductName("TV"));
        purchaseService.create(StoreFixtures.somePurchase(customer, product1).withQuantity(1));
        purchaseService.create(StoreFixtures.somePurchase(customer, product2).withQuantity(3));
        opinionService.create(customer, StoreFixtures.someOpinion(customer, product1));
        Assertions.assertEquals(customer, customerService.find(customer.getEmail()));

        // when
        customerService.remove(customer.getEmail());

        // then
        RuntimeException exception = Assertions
                .assertThrows(RuntimeException.class, () -> customerService.find(customer.getEmail()));
        Assertions.
                assertEquals(String.format("Customer with email: [%s] doesn't exist", customer.getEmail()), exception.getMessage());

        Assertions.assertTrue(purchaseService.findAll(customer.getEmail()).isEmpty());
        Assertions.assertTrue(opinionService.findAll(customer.getEmail()).isEmpty());

    }
    @Test
    @DisplayName("Polecenie 4 cz.2")
    void thatPurchaseIsNotRemovedWhenCustomerRemovingFails() {
        // given
        final Customer customer = customerService.create(StoreFixtures.someCustomer().withDateOfBirth(LocalDate.of(1950, 10, 4)));
        final Producer producer = producerService.create(StoreFixtures.someProducer());
        final Product product1 = productService.create(StoreFixtures.someProduct(producer).withProductCode("g22s").withProductName("Shoes"));
        final Product product2 = productService.create(StoreFixtures.someProduct(producer).withProductCode("Kl09").withProductName("TV"));
        final Purchase purchase1 = purchaseService.create(StoreFixtures
                .somePurchase(customer, product1)
                .withQuantity(1)
                .withDateTime(OffsetDateTime.of(2020, 1, 2, 11, 34, 10, 0, ZoneOffset.ofHours(2))));
        final Purchase purchase2 = purchaseService.create(StoreFixtures
                .somePurchase(customer, product2)
                .withQuantity(3)
                .withDateTime(OffsetDateTime.of(2020, 1, 4, 11, 34, 10, 0, ZoneOffset.ofHours(2))));
        final Opinion opinion = opinionService.create(customer, StoreFixtures.someOpinion(customer, product1));

        Assertions.assertEquals(customer, customerService.find(customer.getEmail()));

        // when
        RuntimeException exception =
                Assertions.assertThrows(RuntimeException.class,
                        () -> customerService.remove(customer.getEmail()));
        Assertions.assertEquals(String.format("Could not remove customer because he/she is older than 40, email: [%s]", customer.getEmail()),
                exception.getMessage());

        // then
        Assertions.assertEquals(customer, customerService.find(customer.getEmail()));
        Assertions.assertEquals(
                List.of(
                        purchase1
                                .withDateTime(purchase1.getDateTime().withOffsetSameInstant(ZoneOffset.UTC))
                                .withCustomer(Customer.builder().id(customer.getId()).build())
                                .withProduct(Product.builder().id(product1.getId()).build()),
                        purchase2
                                .withDateTime(purchase2.getDateTime().withOffsetSameInstant(ZoneOffset.UTC))
                                .withCustomer(Customer.builder().id(customer.getId()).build())
                                .withProduct(Product.builder().id(product2.getId()).build())
                ),
                purchaseService.findAll(customer.getEmail()));
        Assertions.assertEquals(List.of(
                        opinion
                                .withCustomer(Customer.builder().id(customer.getId()).build())
                                .withProduct(Product.builder().id(product1.getId()).build())
                                .withDateTime(opinion.getDateTime().withOffsetSameInstant(ZoneOffset.UTC))
                ),
                opinionService.findAll(customer.getEmail())
        );
    }
    @Test
    @DisplayName("Polecenie 8")
    void thatCustomersGivingUnwantedOpinionsAreRemovedCorrectly() {
        // given
        reloadDataService.reloadData();
        Assertions.assertEquals(100, customerService.findAll().size());

        // when
        customerService.removeUnwantedCustomers();

        // then
        Assertions.assertEquals(64, customerService.findAll().size());
    }
}
