package org.project.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.business.OpinionService;
import org.project.business.ProductRemovalService;
import org.project.business.ProductService;
import org.project.business.PurchaseService;
import org.project.business.ReloadDataService;
import org.project.domain.Opinion;
import org.project.domain.Product;
import org.project.domain.Purchase;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Objects;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceTest {
    private ReloadDataService reloadDataService;
    private OpinionService opinionService;
    private ProductService productService;
    private PurchaseService purchaseService;
    private ProductRemovalService productRemovalService;

    @BeforeEach
    public void setUp() {
        Assertions.assertNotNull(reloadDataService);
        Assertions.assertNotNull(opinionService);
        Assertions.assertNotNull(productService);
        Assertions.assertNotNull(purchaseService);
        Assertions.assertNotNull(productRemovalService);
        reloadDataService.reloadData();
    }

    @Test
    @DisplayName("Polecenie 10")
        //given
    void thatProductIsWipedCompletely() {
        final var productCode = "60560-1072";
        Product before = productService.find(productCode);
        List<Opinion> opinionBefore = opinionService.findAllByProduct(productCode);
        List<Purchase> purchasesBefore = purchaseService.findAllByProductCode(productCode);
        Assertions.assertTrue(Objects.nonNull(before));
        Assertions.assertEquals(3,opinionBefore.size());
        Assertions.assertEquals(5,purchasesBefore.size());

        //when
        productRemovalService.removeCompletely(productCode);
        //then

        Throwable exception = Assertions.assertThrows(RuntimeException.class,() -> productService.find(productCode));
        Assertions.assertEquals(String.format("Product with productCode: [%s] doesn't exist",productCode),exception.getMessage());
        Assertions.assertTrue(opinionService.findAllByProduct(productCode).isEmpty());
        Assertions.assertTrue(purchaseService.findAllByProductCode(productCode).isEmpty());
    }



}
