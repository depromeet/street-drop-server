package com.depromeet.common.annotation.validator;


import com.depromeet.common.annotation.NotBannedWord;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
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
        var contentWord = List.of(value.split(" "));
        var bannedWord = bannedWordRepository.findBannedWordsInWordList(contentWord);
        if (bannedWord.isPresent()) {
            throw new BusinessException(ErrorCode.CANNOT_USE_BANNED_WORD, bannedWord.get() + " is Banned Word");
        }
        return true;
    }
}