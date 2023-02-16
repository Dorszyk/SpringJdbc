package org.example6;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AccountService {

    private InvoiceService invoiceService;

    @Transactional
    public void saveBill(){
        invoiceService.saveBill();
    }


}
