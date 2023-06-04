package com.depromeet.common.validator;

import com.depromeet.common.annotation.IsLongitude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<IsLongitude, Double> {

    private static final double MIN_LONGITUDE = -180;
    private static final double MAX_LONGITUDE = 180;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value == null || !(value < MIN_LONGITUDE) && !(value > MAX_LONGITUDE);
    }
}