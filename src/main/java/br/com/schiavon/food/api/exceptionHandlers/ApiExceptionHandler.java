package br.com.schiavon.food.api.exceptionHandlers;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?>
    handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest webRequest){
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblem(status.value(), ProblemType.ENTIDADE_NAO_ENCONTRADA, e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(RelacionamentoEntidadeNaoEncontradoException.class)
    public ResponseEntity<?>
    handleRelacionamentoEntidadeNaoEncontradoException(RelacionamentoEntidadeNaoEncontradoException e, WebRequest webRequest){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest){
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if(body == null){
            body = new Problem(status.value(), status.getReasonPhrase());
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem createProblem(int status, ProblemType problemType, String detail){
        return new Problem(status, problemType.type, problemType.title, detail);
    }
}
