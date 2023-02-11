package org.example2;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SimpleJdbcCallExample {
    private final SimpleDriverDataSource simpleDriverDataSource;

    public void example(){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(simpleDriverDataSource);
        simpleJdbcCall.withFunctionName("calc_sum");

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("value1",4)
                .addValue("value2",9);
        Integer result = simpleJdbcCall.executeFunction(Integer.class,sqlParameterSource);
        System.out.println(result);
    }
}
