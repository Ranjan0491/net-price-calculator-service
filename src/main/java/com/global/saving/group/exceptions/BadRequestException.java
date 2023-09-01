package com.global.saving.group.exceptions;

import com.global.saving.group.enums.ErrorDetail;

import java.util.Map;

public class BadRequestException extends CoreException {

    public BadRequestException(Map<String, String> invalidParams) {
        super(ErrorDetail.BAD_REQUEST, invalidParams);
    }
}
