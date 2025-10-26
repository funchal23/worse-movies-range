package br.com.outsera.worse_movie.infrastructure.rest;


import br.com.outsera.worse_movie.application.UseCaseOut;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranges")
public class RangeController {
    private final UseCaseOut<AwardRangeResponse> useCaseOut;

    public RangeController(UseCaseOut<AwardRangeResponse> useCaseOut) {
        this.useCaseOut = useCaseOut;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AwardRangeResponse getAwardRange() {
        return useCaseOut.execute();
    }
}
