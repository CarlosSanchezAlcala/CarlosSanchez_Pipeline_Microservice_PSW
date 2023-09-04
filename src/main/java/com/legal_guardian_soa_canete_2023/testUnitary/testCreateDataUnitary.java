package com.legal_guardian_soa_canete_2023.testUnitary;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.legal_guardian_soa_canete_2023.model.legalGuardian;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import com.legal_guardian_soa_canete_2023.repository.legalGuardianRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
public class testCreateDataUnitary {

    @Autowired
    legalGuardianRepository legalGuardianRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateLegalGuardian() {
        legalGuardian newLegalGuardian = new legalGuardian(
                "María Alejandra",
                "Sanchez",
                "Valerio",
                "DNI",
                "78545847",
                "Av. California 784",
                "984754148",
                "MariaValerioSanchez@gmail.com");

        webTestClient.post()
                .uri("/api/legalGuardian")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newLegalGuardian), legalGuardian.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(legalGuardian.class)
                .value(legalGuardian::getId, notNullValue())
                .value(legalGuardian::getName, equalTo("María Alejandra"))
                .value(legalGuardian::getMother_last_name, equalTo("Sanchez"))
                .value(legalGuardian::getFather_last_name, equalTo("Valerio"))
                .value(legalGuardian::getDocumentType, equalTo("DNI"))
                .value(legalGuardian::getDocumentNumber, equalTo("78545847"))
                .value(legalGuardian::getAddress, equalTo("Av. California 784"))
                .value(legalGuardian::getCell_phone, equalTo("984754148"))
                .value(legalGuardian::getEmail, equalTo("MariaValerioSanchez@gmail.com"));
    }
}
