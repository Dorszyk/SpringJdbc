package org.example6;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InvoiceService {

    @Transactional
    public void saveBill() {
    }
}
