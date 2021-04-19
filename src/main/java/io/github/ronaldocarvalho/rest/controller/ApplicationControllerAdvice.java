package io.github.ronaldocarvalho.rest.controller;

import io.github.ronaldocarvalho.exception.PedidoNotFoundException;
import io.github.ronaldocarvalho.exception.RegraNegocioException;
import io.github.ronaldocarvalho.rest.controller.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNotFoundException.class)
    public ApiErrors handlePedidoNotFoundException(PedidoNotFoundException e){
        return new ApiErrors(e.getLocalizedMessage());
    }
}