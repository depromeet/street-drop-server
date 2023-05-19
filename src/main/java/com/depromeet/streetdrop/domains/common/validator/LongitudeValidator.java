package com.depromeet.streetdrop.domains.common.validator;

import com.depromeet.streetdrop.domains.common.annotation.IsLongitude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<IsLongitude, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value == null || !(value < -180) && !(value > 180);
    }
}