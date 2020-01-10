package com.work.consumer.service;

import com.rabbitmq.client.Channel;
import com.work.consumer.config.MQConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author victor
 * @desc hello 消息队列消费者
 */
@Component
@RabbitListener(queues = MQConstant.HELLO_QUEUE_NAME)
public class HelloProcessor {
	
	@RabbitHandler
    public void process(String content,Channel channel, Message message) throws Exception{
        System.out.println("HelloReceiver收到: " + content + ";收到时间" + new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        try {
            System.out.println("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("receiver fail");
        }
        //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}