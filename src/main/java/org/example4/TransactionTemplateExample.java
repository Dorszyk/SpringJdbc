package org.example4;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


@Slf4j
@Service
@AllArgsConstructor
public class TransactionTemplateExample {
    private static final String SQL = "INSERT INTO PERSON (NAME,AGE) VALUES (:name,:age)";
    private final SimpleDriverDataSource simpleDriverDataSource;
    private TransactionTemplate transactionTemplate;



    public void example1(){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                someMethod();
                if(true){
                    status.setRollbackOnly();
                    log.error("Transaction rollback someMethod");
                }
            }
        });
    }

    public void example2(){
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                someVoidMethod();
                if(true){
                    status.setRollbackOnly();
                    log.error("Transaction rollback someVoidMethod");
                }
            }
        });
    }

    private Integer someMethod (){
        int result = 0;
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        Person person1 = Person.builder().name("Miłosz").age(18).build();
        Person person2 = Person.builder().name("Tomasz").age(25).build();
        Person person3 = Person.builder().name("Piotr").age(21).build();

        result+= jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person1));
        result+= jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person2));
        result+= jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person3));
        return result;
    }

    private void someVoidMethod(){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        Person person1 = Person.builder().name("Katarzyna").age(28).build();
        Person person2 = Person.builder().name("Alicja").age(25).build();
        Person person3 = Person.builder().name("Martyna").age(21).build();

        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person1));
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person2));
        jdbcTemplate.update(SQL, new BeanPropertySqlParameterSource(person3));
    }

}
