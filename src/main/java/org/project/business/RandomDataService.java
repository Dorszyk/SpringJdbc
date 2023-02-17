package org.project.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.project.domain.*;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor

public class RandomDataService {

    private final RandomDataPreparationService randomDataPreparationService;

    private final CustomerRepository customerDataBaseRepository;

    private final CustomerService.ProducerRepository producerDataBaseRepository;

    private final ProductRepository productDataBaseRepository;

    private final PurchaseRepository purchaseDataBaseRepository;

    private final OpinionRepository opinionRepository;

    public void create() {
        Customer customer = customerDataBaseRepository.createCustomer(randomDataPreparationService.someCustomer());
        Producer producer = producerDataBaseRepository.createProducer(randomDataPreparationService.someProducer());
        Product product = productDataBaseRepository.createProduct(randomDataPreparationService.someProduct(producer));
        Purchase purchase = purchaseDataBaseRepository.createPurchase(randomDataPreparationService.somePurchase(customer, product));
        Opinion opinion = opinionRepository.createOpinion(randomDataPreparationService.someOpinion(customer, product));

        log.debug("Random customer created: [[{}]", customer);
        log.debug("Random producer created: [[{}]", producer);
        log.debug("Random product created: [[{}]", product);
        log.debug("Random purchase created: [[{}]", purchase);
        log.debug("Random opinion created: [[{}]", opinion);
    }

}
