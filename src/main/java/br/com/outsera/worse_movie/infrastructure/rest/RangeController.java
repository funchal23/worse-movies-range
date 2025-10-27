package br.com.outsera.worse_movie.infrastructure.rest;

import br.com.outsera.worse_movie.application.port.GetRangeIntervalsUseCase;
import br.com.outsera.worse_movie.infrastructure.rest.response.RangeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranges")
public class RangeController {
    private final GetRangeIntervalsUseCase getRangeIntervalsUseCase;

    public RangeController(GetRangeIntervalsUseCase getRangeIntervalsUseCase) {
        this.getRangeIntervalsUseCase = getRangeIntervalsUseCase;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public RangeResponse getAwardRange() {
        return RangeResponse.toResponse(getRangeIntervalsUseCase.execute());
    }
}
