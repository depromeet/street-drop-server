package com.depromeet.common.error.dto;

import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
public class StreetDropErrorCodeList {

    private static volatile StreetDropErrorCodeList instance;
    private final List<StreetDropErrorCode> streetDropErrorCodeList;

    private StreetDropErrorCodeList() {
        streetDropErrorCodeList = createStreetDropErrorCodeList();
    }

    public static StreetDropErrorCodeList getInstance(){
        if (instance == null) {
            synchronized (StreetDropErrorCodeList.class) {
                if (instance == null) {
                    instance = new StreetDropErrorCodeList();
                }
            }
        }
        return instance;
    }

    private synchronized List<StreetDropErrorCode> createStreetDropErrorCodeList() {
        List<StreetDropErrorCode> streetDropErrorCodeList = new ArrayList<>();
        Set<Class<? extends ErrorCodeInterface>> list = new Reflections().getSubTypesOf(ErrorCodeInterface.class);
        for (Class<? extends ErrorCodeInterface> errorCodes : list) {
            if (errorCodes.isEnum()){
                for (var errorCode : errorCodes.getEnumConstants()) {
                    if (errorCode != null){
                        var errorResponseCode = errorCode.getErrorResponseCode();
                        var error = errorCode.toErrorCode();
                        var streetDropError = new StreetDropErrorCode(errorResponseCode, error);
                        streetDropErrorCodeList.add(streetDropError);
                    }
                }
            }

        }

        return streetDropErrorCodeList;
    }
}
