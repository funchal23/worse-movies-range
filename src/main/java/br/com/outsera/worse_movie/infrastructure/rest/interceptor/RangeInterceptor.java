package br.com.outsera.worse_movie.infrastructure.rest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RangeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contentType = request.getContentType();
        String accept = request.getHeader("Accept");
        if (contentType != null && !contentType.contains("application/json")) {
            response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                {
                  "error": "UnsupportedMediaType",
                  "detail": {
                    "message": "Media type informado não é suportado na aplicação. É esperado application/json"
                  }
                }
            """);
            return false;
        }

        if (accept != null && !accept.contains("application/json") && !accept.contains("*/*")) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.setContentType("application/json");
            response.getWriter().write("""
                {
                  "error": "NotAcceptable",
                  "detail": {
                    "message": "Accept informado não é suportado na aplicação. É esperado application/json"
                  }
                }
            """);
            return false;
        }
        return true;
    }
}
