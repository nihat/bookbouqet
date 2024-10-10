package com.bookbouqet.handler;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;


public enum BusinessErrorCodes {

    NO_CODE("0",HttpStatus.NOT_IMPLEMENTED,"Not implemented yet"),
    INCORRECT_CURRENT_PASSWORD ("300",HttpStatus.BAD_REQUEST,"Current password is incorrect"),
    NEW_PASSWORD_DOES_NOT_MATCH("301",HttpStatus.BAD_REQUEST,"New password does not match"),
    ACCOUNT_LOCKED("302",HttpStatus.FORBIDDEN,"Account is locked"),
    ACCOUNT_DISABLED("303",HttpStatus.FORBIDDEN,"Account is disabled"),
    BAD_CREDENTIALS("304",HttpStatus.FORBIDDEN,"Username and / or password is incorrect");


    @Getter
    private final String code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;


    BusinessErrorCodes(String code, HttpStatus httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }


}
