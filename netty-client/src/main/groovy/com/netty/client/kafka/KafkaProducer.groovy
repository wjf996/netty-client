package com.netty.client.kafka

import com.alibaba.fastjson.JSON
import com.netty.client.dto.Person
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.lang.Nullable
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.util.concurrent.ListenableFutureCallback
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WangJunfeng
 * @version 1.0
 * @description: kafka生产者
 * @date 2020/8/28 16:20
 */
@Slf4j
@RestController
class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate

    @PostMapping("/kafka/send")
    void send(@Validated @RequestBody Person person) {
        log.info("请求发送参数：{}",person)
        kafkaTemplate.send("rain", JSON.toJSONString(person)).addCallback(new ListenableFutureCallback<SendResult>() {
            @Override
            void onFailure(Throwable throwable) {
                log.info("消息发送失败。。。。。。。")
            }
            @Override
            void onSuccess(@Nullable SendResult sendResult) {
                log.info("消息发送成功。。。。。。。")
            }
        })
    }
}
