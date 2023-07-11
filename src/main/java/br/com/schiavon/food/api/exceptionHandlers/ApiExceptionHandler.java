package br.com.schiavon.food.api.exceptionHandlers;

import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

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
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.RELACIONAMENTO_ENTIDADE_NAO_ENCONTRADO;
        Problem problem = createProblem(status.value(), problemType, e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest){
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = createProblem(status.value(), ProblemType.ENTIDADE_EM_USO, e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        System.out.println(rootCause.getClass());

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
           return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "O corpo da requisição esta invalido. Por favor, verifique se há um erro de sintaxe";
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String causa = null;

        if(ex instanceof IgnoredPropertyException){
            causa = "não deve ser informada";
        } else if (ex instanceof UnrecognizedPropertyException) {
            causa = "não existe";
        }

        String propriedade = ex.getPath().stream().map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu algum valor, porém está propriedade %s.",
                propriedade, causa);
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
        String valor = (String) ex.getValue();
        String tipo = ex.getTargetType().getSimpleName();
        String propriedade = ex.getPath().stream().map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é um tipo invalido." +
                "Corrija informando um valor compatível com o tipo '%s'.", propriedade, valor, tipo);
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail);

        return handleExceptionInternal(ex, problem, headers, status, request);
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
