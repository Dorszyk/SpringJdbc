package org.example1;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleJdbcInsertExample {
    private final SimpleDriverDataSource simpleDriverDataSource;

    public void simpleJdbcInsertExample(){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(simpleDriverDataSource);
        simpleJdbcInsert.setTableName("PERSON");

        Person person = Person.builder()
                .id(1L)
                .name("Piotr")
                .age(36)
                .profession("Naukowiec")
                .education("Politechnika Gdanska")
                .build();

        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(person);
        int result = simpleJdbcInsert.execute(parameterSource);
        System.out.println(result);
    }
}
