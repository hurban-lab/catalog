package com.hurbanlab.catalog.error;

import lombok.Data;

@Data
public class CatalogError extends Exception {

    private String errorCode;
    public CatalogError(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public CatalogError(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public CatalogError(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
    }

    public CatalogError(String errorCode, Throwable ex) {
        super(ex);
        this.errorCode = errorCode;
    }
}
