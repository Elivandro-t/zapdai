package com.dr7.dr7.infra.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CodigoInvalidoExeption extends RuntimeException {

        public CodigoInvalidoExeption(String message) {
            super(message);
        }

}
