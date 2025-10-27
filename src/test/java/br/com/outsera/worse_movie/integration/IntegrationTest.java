package br.com.outsera.worse_movie.integration;

import br.com.outsera.worse_movie.infrastructure.rest.response.RangeResponse;
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
        ResponseEntity<RangeResponse> response =
                restTemplate.getForEntity("/ranges", RangeResponse.class);
        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().max());
        Assertions.assertNotNull(response.getBody().min());
        Assertions.assertEquals(2, response.getBody().max().size());
        Assertions.assertEquals(2, response.getBody().min().size());

        var minList = response.getBody().min();
        var maxList = response.getBody().max();

        String producerA = "Matthew Vaughn";
        String producerB = "Joel Silver";

        Assertions.assertTrue(
                minList.get(0).getProducer().contains(producerA) || minList.get(0).getProducer().contains(producerB)
        );
        Assertions.assertTrue(
                minList.get(1).getProducer().contains(producerA) || minList.get(1).getProducer().contains(producerB)
        );
        Assertions.assertTrue(maxList.get(0).getProducer().contains(producerA));
        Assertions.assertTrue(maxList.get(1).getProducer().contains(producerA));

        Assertions.assertTrue(
                minList.stream().anyMatch(m -> m.getInterval() == 1)
        );
        Assertions.assertTrue(
                minList.stream().allMatch(m -> m.getInterval() == 1)
        );
        Assertions.assertTrue(
                minList.stream().anyMatch(m ->
                        (m.getPreviousWin() == 2002 && m.getFollowingWin() == 2003) ||
                                (m.getPreviousWin() == 1990 && m.getFollowingWin() == 1991)
                )
        );
        Assertions.assertTrue(
                maxList.stream().allMatch(m -> m.getInterval() == 22)
        );
        Assertions.assertTrue(
                maxList.stream().anyMatch(m ->
                        (m.getPreviousWin() == 1980 && m.getFollowingWin() == 2002) ||
                                (m.getPreviousWin() == 2015 && m.getFollowingWin() == 2037)
                )
        );
    }
}
