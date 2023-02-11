package org.example3;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NamedParameterTemplateExamples {
    private final SimpleDriverDataSource simpleDriverDataSource;

    public void example(){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(simpleDriverDataSource);

        String sql = "INSERT INTO PERSON (name,age,education,profession) VALUES (:name, :age,:education,:profession)";

        Person person = Person.builder()
                .name("Agnieszka")
                .age(27)
                .education("WSFiA")
                .profession("Trener")
                .build();

        BeanPropertySqlParameterSource params3 = new BeanPropertySqlParameterSource(person);
        template.update(sql, params3);
    }
}
