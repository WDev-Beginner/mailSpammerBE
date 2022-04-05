package com.egrine.mailspammer.utility.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipientNotFoundException extends RuntimeException{
}
