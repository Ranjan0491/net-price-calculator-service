package com.global.saving.group.enums;

import lombok.Getter;

@Getter
public enum ErrorDetail {
    BAD_REQUEST("GSG_400", "Request parameters does not have proper values")
    ;

    private String code;
    private String message;

    ErrorDetail(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
