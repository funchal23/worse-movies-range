package br.com.outsera.worse_movie.integration;

import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldSuccessGetRangeAward() {
        ResponseEntity<AwardRangeResponse> response =
                restTemplate.getForEntity("/ranges", AwardRangeResponse.class);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getMax());
        Assertions.assertNotNull(response.getBody().getMin());
        Assertions.assertEquals(1, response.getBody().getMax().size());
        Assertions.assertEquals(1, response.getBody().getMin().size());
        Assertions.assertEquals("Matthew Vaughn", response.getBody().getMax().get(0).getProducer());
        Assertions.assertEquals("Joel Silver", response.getBody().getMin().get(0).getProducer());
    }

    @Test
    void shouldSuccessGetRangeAwardWithDisregardTrue() {
        ResponseEntity<AwardRangeResponse> response =
                restTemplate.getForEntity("/ranges?disregardMoreThanOneWinnerPerYear=true", AwardRangeResponse.class);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getMax());
        Assertions.assertNotNull(response.getBody().getMin());
        Assertions.assertEquals(1, response.getBody().getMax().size());
        Assertions.assertEquals(1, response.getBody().getMin().size());
        Assertions.assertEquals("Buzz Feitshans", response.getBody().getMax().get(0).getProducer());
        Assertions.assertEquals("Buzz Feitshans", response.getBody().getMin().get(0).getProducer());
    }
}
