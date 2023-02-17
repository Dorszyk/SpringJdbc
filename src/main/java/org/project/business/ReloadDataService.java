package org.project.business;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Slf4j
@Service
@AllArgsConstructor
public class ReloadDataService {

    private CustomerService customerService;
    private ProducerService producerService;
    private RandomDataService randomDataService;

    @Transactional
    public void reloadData() {
        customerService.removeAll();
        producerService.removeAll();
        for (int i = 0; i < 10; i++) {
            randomDataService.create();
        }
    }
}
