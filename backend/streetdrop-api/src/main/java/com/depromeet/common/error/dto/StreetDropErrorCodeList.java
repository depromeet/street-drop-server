package com.depromeet.common.error.dto;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Slf4j
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

        try {
            ClassPathScanningCandidateComponentProvider s = new ClassPathScanningCandidateComponentProvider(false);

            TypeFilter tf = new AssignableTypeFilter(ErrorCodeInterface.class);
            s.addIncludeFilter(tf);

            Set<BeanDefinition> components = s.findCandidateComponents("com.depromeet");

            for (BeanDefinition component : components) {
                Class<?> className = Class.forName(component.getBeanClassName());

                if (className.isEnum()) {
                    for (var errorCode : className.getEnumConstants()) {
                        if (errorCode != null) {
                            String errorResponseCode = (String) errorCode.getClass().getMethod("getErrorResponseCode").invoke(errorCode);
                            ErrorCode error = (ErrorCode) errorCode.getClass().getMethod("toErrorCode").invoke(errorCode);
                            var streetDropError = new StreetDropErrorCode(errorResponseCode, error);
                            streetDropErrorCodeList.add(streetDropError);
                        }
                    }
                }
            }

        } catch (Exception ignored) {
        }

        return streetDropErrorCodeList;
    }
}
