package org.example6;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class TransactionTemplateExample {
    private  SomeOtherBean someOtherBean;

    @Transactional
    public Integer someMethod (){
       return someOtherBean.example();
    }


}
