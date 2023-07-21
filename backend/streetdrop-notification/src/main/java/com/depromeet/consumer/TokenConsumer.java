package com.depromeet.consumer;

import com.depromeet.dto.request.TokenRequestDto;
import com.depromeet.service.TokenService;
import com.depromeet.vo.SaveTokenMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class TokenConsumer {

    private final TokenService tokenService;

    @RabbitListener(queues = "#{queue.name}")
    public void consume(SaveTokenMessage message){
        TokenRequestDto tokenRequestDto = new TokenRequestDto(message.getUserId(), message.getToken());
        log.info("{} {}", message.getToken(), message.getUserId());
        log.info("{} {}", tokenRequestDto.getToken(), tokenRequestDto.getUserId());
    }
}