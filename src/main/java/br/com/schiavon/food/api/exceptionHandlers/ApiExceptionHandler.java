package br.com.schiavon.food.api.exceptionHandlers;

import br.com.schiavon.food.core.validation.ValidationPatchException;
import br.com.schiavon.food.domain.exceptions.EntidadeEmUsoException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.EntidadeNaoEncontradaException;
import br.com.schiavon.food.domain.exceptions.RelacionamentoEntidadeNaoEncontradoException;
import br.com.schiavon.food.domain.exceptions.UsuarioNegocioException;
import br.com.schiavon.food.domain.exceptions.naoencontrada.RestauranteLoteNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String MSG_GENERICA_PARA_USUARIO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, " +
            "entre em contato com o administrador do sistema.";

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?>
    handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Problem problem = createProblem(status.value(), ProblemType.RECURSO_NAO_ENCONTRADO, e.getMessage(), e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(RestauranteLoteNaoEncontradaException.class)
    public ResponseEntity<?>
    handleRestauranteLoteNaoEncontradaException(RestauranteLoteNaoEncontradaException e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problem problem = createProblem(status.value(), ProblemType.RECURSO_NAO_ENCONTRADO, e.getMessage(), e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(RelacionamentoEntidadeNaoEncontradoException.class)
    public ResponseEntity<?>
    handleRelacionamentoEntidadeNaoEncontradoException(RelacionamentoEntidadeNaoEncontradoException e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ProblemType problemType = ProblemType.RELACIONAMENTO_ENTIDADE_NAO_ENCONTRADO;
        Problem problem = createProblem(status.value(), problemType, e.getMessage(), e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problem = createProblem(status.value(), ProblemType.ENTIDADE_EM_USO, e.getMessage(), e.getMessage());

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(UsuarioNegocioException.class)
    public ResponseEntity<?> handleSenhaDeUsuarioNaoCoincidemException(UsuarioNegocioException ex,
                                                                       WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Problem problem = createProblem(status.value(), ProblemType.SENHA_NAO_COINCIDEM, ex.getMessage(), ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, webRequest);
    }

    @ExceptionHandler(ValidationPatchException.class)
    public ResponseEntity<?> handleValidationPatchException(ValidationPatchException e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        BindingResult bindingResult = e.getBindingResult();
        return trataValidationExceptions(e, new HttpHeaders(), status, webRequest, bindingResult);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExceptionGenerico(Exception e, WebRequest webRequest) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String detail = MSG_GENERICA_PARA_USUARIO;
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SISTEMA, detail, null);

        return handleExceptionInternal(e, problem, new HttpHeaders(), status, webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        System.out.println(rootCause.getClass());

        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        String detail = "O corpo da requisição esta invalido. Por favor, verifique se há um erro de sintaxe";
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail, null);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String causa = null;

        if (ex instanceof IgnoredPropertyException) {
            causa = "não deve ser informada";
        } else if (ex instanceof UnrecognizedPropertyException) {
            causa = "não existe";
        }

        String propriedade = ex.getPath().stream().map(reference -> reference.getFieldName())
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade '%s' recebeu algum valor, porém está propriedade %s. Corriga ou remova.",
                propriedade, causa);
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail, null);

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
        Problem problem = createProblem(status.value(), ProblemType.ERRO_DE_SINTAXE, detail, null);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido." +
                        " Corrija e informe um valor compatível com o tipo '%s'", ex.getName(), ex.getValue(),
                ex.getRequiredType().getSimpleName());
        Problem problem = createProblem(status.value(), ProblemType.PARAMETRO_INVALIDO, detail, null);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String detail = String.format("O recurso '%s', que você tentou acessar, é inesistente.", ex.getRequestURL());
        Problem problem = createProblem(status.value(), ProblemType.RECURSO_NAO_ENCONTRADO, detail, null);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        return trataValidationExceptions(ex, headers, status, request, bindingResult);
    }

    private ResponseEntity<Object> trataValidationExceptions(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request, BindingResult bindingResult) {
        List<ProblemFieldValidation> fields = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return new ProblemFieldValidation(name, message);
                })
                .collect(Collectors.toList());

        String detail = "Um ou mais campos são invalidos, Faça o preenchimento correto e tente novamente";
        Problem problem = createProblem(status.value(), ProblemType.DADOS_INVALIDOS, detail, detail);
        problem.setFields(fields);

        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new Problem(status.value(), status.getReasonPhrase());
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem createProblem(int status, ProblemType problemType, String detail, String userMessage) {
        if (userMessage == null) {
            userMessage = MSG_GENERICA_PARA_USUARIO;
        }
        return new Problem(status, problemType.type, problemType.title, detail, userMessage);
    }
}
