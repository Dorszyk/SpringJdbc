package org.project.integration;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.business.OpinionService;
import org.project.business.ReloadDataService;
import org.project.domain.Opinion;
import org.project.infrastructure.configuration.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(classes = {ApplicationConfiguration.class})

@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OpinionServiceTest {

    private ReloadDataService reloadDataService;
    private OpinionService opinionService;

    @BeforeEach
    void setUp() {
        Assertions.assertNotNull(reloadDataService);
        Assertions.assertNotNull(opinionService);
        reloadDataService.reloadData();
    }

    @Test
    @DisplayName("Polecenie 7")
    void thatUnwantedOpinionsAreRemoved() {
        //given
        Assertions.assertEquals(140, opinionService.findAll().size());
        List<Opinion> unwantedOpinions = opinionService.findUnwantedOpinions();

        //when
        opinionService.removeUnwantedOpinions();

        //then
        Assertions.assertEquals(140 - unwantedOpinions.size(), opinionService.findAll().size());

    }
}
