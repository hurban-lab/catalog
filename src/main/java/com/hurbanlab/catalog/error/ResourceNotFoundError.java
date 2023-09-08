package com.hurbanlab.catalog.error;

public class ResourceNotFoundError extends CatalogError{

    public ResourceNotFoundError(String errorCode) {
        super(errorCode);
    }

    public ResourceNotFoundError(String errorCode, String message) {
        super(errorCode, message);
    }

}
