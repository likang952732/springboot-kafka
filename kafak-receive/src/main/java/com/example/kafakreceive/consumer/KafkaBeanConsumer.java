package com.example.kafakreceive.consumer;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 17:16   
 */

import com.alibaba.fastjson.JSON;
import com.example.kafakreceive.constant.Topic;
import com.example.kafakreceive.domain.Programmer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaBeanConsumer {
    @KafkaListener(groupId = "beanGroup",topics = Topic.BEAN)
    public void consumer(ConsumerRecord<String, Object> record) {
        log.info("消费者收到消息: {}", JSON.parseObject(record.value().toString(), Programmer.class));
    }
}
