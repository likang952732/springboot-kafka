package com.example.kafakreceive.consumer;

import com.example.kafakreceive.constant.Topic;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 16:54   
 */
@Component
@Slf4j
public class KafkaGroupConsumer {
    // 分组 1 中的消费者 1
    @KafkaListener(id = "consumer1-1", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = Topic.GROUP, partitions = {"0", "1"})
            })
    public void consumer1_1(ConsumerRecord<String, Object> record) {
        log.info("consumer1-1 收到消息: {}", record.value());
    }

    // 分组 1 中的消费者 2
    @KafkaListener(id = "consumer1-2", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = Topic.GROUP, partitions = {"2", "3"})
            })
    public void consumer1_2(ConsumerRecord<String, Object> record) {
        log.info("consumer1-2 收到消息: {}", record.value());
    }

    // 分组 1 中的消费者 3
    @KafkaListener(id = "consumer1-3", groupId = "group1", topicPartitions =
            {@TopicPartition(topic = Topic.GROUP, partitions = {"0", "1"})
            })
    public void consumer1_3(ConsumerRecord<String, Object> record) {
        log.info("consumer1-3 收到消息: {}", record.value());
    }

    // 分组 2 中的消费者
    @KafkaListener(id = "consumer2-1", groupId = "group2", topics = Topic.GROUP)
    public void consumer2_1(ConsumerRecord<String, Object> record) {
        log.info("consumer2-1 收到消息: {}", record.value());
    }
}
