package com.example.kafakreceive.consumer;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 17:16   
 */

import com.alibaba.fastjson.JSON;
import com.example.kafakreceive.constant.Topic;
import com.example.kafakreceive.domain.Programmer;
import com.example.kafakreceive.domain.User;
import com.example.kafakreceive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class KafkaBeanConsumer {
    @Resource
    private UserService userService;

    @KafkaListener(groupId = "beanGroup",topics = Topic.BEAN)
    public void consumer(ConsumerRecord<String, Object> record) {
        log.info("消费者接收到消息");
        User user = JSON.parseObject(record.value().toString(), User.class);
        log.info("消费者收到消息: {}", user.toString());
        userService.addUser(user);
    }
}
