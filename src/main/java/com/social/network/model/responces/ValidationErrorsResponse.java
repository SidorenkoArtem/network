package com.social.network.model.responces;

import com.social.network.exceptions.ErrorCode;
import com.social.network.model.dto.ValidationErrorFieldDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorsResponse {

    private final int errorCode = ErrorCode.NOT_VALID_REQUEST_DATA.getErrorCode();
    private final List<ValidationErrorFieldDto> errors;

}
