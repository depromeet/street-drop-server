package com.depromeet.streetdrop.domains.common.validator;

import com.depromeet.streetdrop.domains.common.annotation.IsLatitude;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<IsLatitude, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value == null || !(value < -90) && !(value > 90);
    }
}
