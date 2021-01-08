package com.example.kafaksend.controller;
import com.alibaba.fastjson.JSON;
import com.example.kafaksend.constant.Topic;
import com.example.kafaksend.domain.Programmer;
import com.example.kafaksend.domain.User;
import com.example.kafaksend.producer.KafKaCustomrProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

/*
 @Description
 *@author kang.li
 *@date 2019/10/24 16:16   
 */
@RestController
@Slf4j
public class SendMsgController {
    @Autowired
    private KafKaCustomrProducer producer;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /***
     * 发送消息体为基本类型的消息
     */

    @GetMapping("sendSimple")
    public void sendSimple() {
        producer.sendMessage(Topic.SIMPLE, "hello spring boot kafka");
    }

    @GetMapping("sendGroup")
    public void sendGroup() {
        for (int i = 0; i < 4; i++) {
            // 第二个参数指定分区，第三个参数指定消息键 分区优先
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(Topic.GROUP, i % 4, "key", "hello group " + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("发送消息失败: {}", throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, Object> sendResult) {
                    log.info("发送消息到指定主题的具体分区结果: {}", sendResult.toString());
                }
            });
        }
    }

    /***
     * 发送消息体为 bean 的消息
     */
    @GetMapping("/sendBean")
    public String sendBean() {
        //Programmer programmer = new Programmer("xiaoming", 12, 21212.33f, new Date());
        log.info("开始发送");
        User user = new User();
        user.setName("赵六");
        user.setPassword("123");
        user.setLoginName("test3");
        user.setAddress("张江");
        user.setEmail("123445@qq.com");
        producer.sendMessage(Topic.BEAN, JSON.toJSON(user).toString());
        return "发送完毕";
    }
}
