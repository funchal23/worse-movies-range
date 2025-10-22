package br.com.outsera.worse_movie.infrastructure.rest;


import br.com.outsera.worse_movie.application.UseCase;
import br.com.outsera.worse_movie.infrastructure.rest.request.AwardRangeRequest;
import br.com.outsera.worse_movie.infrastructure.rest.response.AwardRangeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranges")
public class RangeController {
    private final UseCase<AwardRangeResponse, AwardRangeRequest> useCase;

    public RangeController(UseCase<AwardRangeResponse, AwardRangeRequest> useCase) {
        this.useCase = useCase;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public AwardRangeResponse getAwardRange(
            @RequestParam(defaultValue = "false") boolean disregardMoreThanOneWinnerPerYear) {
        return useCase.execute(AwardRangeRequest.builder()
                .disregardMoreThanOneWinnerPerYear(disregardMoreThanOneWinnerPerYear)
                .build());
    }
}
