package com.depromeet.common.annotation.validator;


import com.depromeet.common.annotation.NotBannedWord;
import com.depromeet.common.repository.BannedWordRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
public class BannedWordValidator implements ConstraintValidator<NotBannedWord, String> {

    private final BannedWordRepository bannedWordRepository;
    @Override
    @Transactional(readOnly = true)
    public boolean isValid(String value, ConstraintValidatorContext context) {
        var contentWords = List.of(value.split(" "));
        var bannedWord = bannedWordRepository.findBannedWordsInWordList(contentWords);
        if (!bannedWord.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Cannot use banned word : " + bannedWord.get(0))
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}