package com.legal_guardian_soa_canete_2023.testUnitary;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import com.legal_guardian_soa_canete_2023.model.legalGuardian;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import com.legal_guardian_soa_canete_2023.repository.legalGuardianRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
public class testDataUnitary {

    @Autowired
    legalGuardianRepository legalGuardianRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateLegalGuardian() {
        legalGuardian newLegalGuardian = new legalGuardian(
                "Carla Alejandra",
                "Pereira",
                "Sanchez",
                "DNI",
                "45781478",
                "Sin dirección exacta",
                "963258420",
                "CarlaMasNada@gmail.com",
                "A");

        webTestClient.post()
                .uri("/api/legalGuardian")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(newLegalGuardian), legalGuardian.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(legalGuardian.class)
                .value(legalGuardian::getId, notNullValue())
                .value(legalGuardian::getName, equalTo("Carla Alejandra"))
                .value(legalGuardian::getFather_last_name, equalTo("Pereira"))
                .value(legalGuardian::getMother_last_name, equalTo("Sanchez"))
                .value(legalGuardian::getDocumentType, equalTo("DNI"))
                .value(legalGuardian::getDocumentNumber, equalTo("45781478"))
                .value(legalGuardian::getAddress, equalTo("Sin dirección exacta"))
                .value(legalGuardian::getCell_phone, equalTo("963258420"))
                .value(legalGuardian::getEmail, equalTo("CarlaMasNada@gmail.com"));
    }

    @Test
    public void testListLegalGuardian() {
        webTestClient.get().uri("/api/legalGuardian/list")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(legalGuardian.class)
                .consumeWith(response -> {
                    List<legalGuardian> dataList = response.getResponseBody();
                    assertTrue("La lista no debe de estar vacía: ", dataList != null && !dataList.isEmpty());
                    assertTrue("La lista debe contener al menos " + 1 + " elementos", dataList.size() >= 1);
                });

    }

    @Test
    public void testListInactiveLegalGuardian() {
        webTestClient.get().uri("/api/legalGuardian/list/inactive")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(legalGuardian.class)
                .consumeWith(response -> {
                    List<legalGuardian> dataList = response.getResponseBody();
                    assertTrue("La lista no debe de estar vacía: ", dataList != null && !dataList.isEmpty());
                    assertTrue("La lista debe contener al menos " + 1 + " elementos", dataList.size() >= 1);
                });

    }

    @Test
    public void testListActiveLegalGuardian() {
        webTestClient.get().uri("/api/legalGuardian/list/active")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(legalGuardian.class)
                .consumeWith(response -> {
                    List<legalGuardian> dataList = response.getResponseBody();
                    assertTrue("La lista no debe de estar vacía: ", dataList != null && !dataList.isEmpty());
                    assertTrue("La lista debe contener al menos " + 1 + " elementos", dataList.size() >= 1);
                });

    }

    @ParameterizedTest
    @ValueSource(ints = {21})
    public void testUpdateLegalGuardian(int dataId) {
        legalGuardian UpdateLegalGuardian = new legalGuardian(
                "Carla Alejandra",
                "Pereira",
                "Sanchez",
                "DNI",
                "45781478",
                "Sin dirección exacta",
                "985478001",
                "CarlaMasNada@gmail.com",
                "A");

        webTestClient.put().uri("/api/legalGuardian/{id}", dataId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(UpdateLegalGuardian)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(legalGuardian.class)
                .consumeWith(response -> {
                    legalGuardian updatedData = response.getResponseBody();
                });
    }
}
