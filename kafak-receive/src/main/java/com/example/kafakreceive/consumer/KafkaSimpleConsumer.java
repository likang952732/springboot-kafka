package com.example.kafakreceive.consumer;

import com.example.kafakreceive.constant.Topic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 14:04   
 */
@Component
@Slf4j
public class KafkaSimpleConsumer {
    // 简单消费者
    @KafkaListener(groupId = "simpleGroup", topics = Topic.SIMPLE)
    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer) {
        log.info("消费者收到消息: {}, topic: {}", record.value(),  topic);
        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }
}
