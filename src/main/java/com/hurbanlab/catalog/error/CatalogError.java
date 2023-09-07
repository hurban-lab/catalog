package com.hurbanlab.catalog.error;

import lombok.Data;

@Data
public class CatalogError extends Exception{

    private String errorCode;

}
