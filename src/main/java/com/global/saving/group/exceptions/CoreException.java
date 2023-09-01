package com.global.saving.group.exceptions;

import com.global.saving.group.enums.ErrorDetail;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CoreException extends RuntimeException {
    private String code;
    private Map<String, String> invalidParams;

    public CoreException(ErrorDetail errorDetail, Map<String, String> invalidParams) {
        this(errorDetail);
        this.code = errorDetail.getCode();
        this.invalidParams = invalidParams;
    }

    public CoreException(ErrorDetail errorDetail) {
        super(errorDetail.getMessage());
    }
}
