package com.depromeet.domains.notification.publisher;

import com.depromeet.domains.notification.vo.SaveTokenMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTokenPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    public void sendMessage(SaveTokenMessage saveTokenMessage) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "notification.key.1", saveTokenMessage);
    }
}
