package com.depromeet.external.swagger.config;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Component
public class ErrorRequestHeaderCustomizer {

    public void generateErrorRequestHeader(Operation operation, List<ErrorCode> errorCodeList) {
        var defaultParameters = getDefaultParameter(operation);
        var newErrorCodeParameters = generateErrorRequestHeader(errorCodeList);
        defaultParameters.add(newErrorCodeParameters);
        if (defaultParameters.size() == 1) {
            operation.setParameters(defaultParameters);
        }
    }

    public void generateErrorRequestHeader(Operation operation, ErrorCode errorCode) {
        var defaultParameters = getDefaultParameter(operation);
        var newErrorCodeParameters = generateErrorRequestHeader(singletonList(errorCode));
        defaultParameters.add(newErrorCodeParameters);
        if (defaultParameters.size() == 1) {
            operation.setParameters(defaultParameters);
        }
    }

    private List<Parameter> getDefaultParameter(Operation operation) {
        List<Parameter> parameters = operation.getParameters();
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        return parameters;
    }

    private Parameter generateErrorRequestHeader(List<ErrorCode> errorCode) {
        var parameter = new Parameter();
        parameter.setName("STREET-DROP-ERROR-TEST-CODE");
        parameter.setIn("header");
        parameter.setDescription("에러를 확인하기 위한 코드입니다. 테스트 서버 환경에서 해당 에러코드를 STREET-DROP-ERROR-TEST-CODE 헤더에 추가하여 테스트할 수 있습니다.");
        parameter.setRequired(false);
        parameter.setDeprecated(false);
        parameter.setSchema(generateErrorRequestHeaderSchema(errorCode));
        return parameter;
    }

    private Schema<?> generateErrorRequestHeaderSchema(List<ErrorCode> errorCode) {
        var schema = new Schema<>();
        schema.setEnum(Arrays.asList(errorCode.stream().map(ErrorCode::getErrorResponseCode).toArray()));
        schema.setType("string");
        return schema;
    }
}
