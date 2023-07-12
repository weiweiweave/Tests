package com.finance.banking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionJSONInfo {

    private String url;
    private String message;
    private String cause;
}
