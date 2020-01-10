package com.work.rabbit;

import com.work.rabbit.constant.MQConstant;
import com.work.rabbit.service.IMessageQueueService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private IMessageQueueService messageQueueService;

    @Test
    public void sendDelayMsg() throws Exception {
        messageQueueService.send(MQConstant.HELLO_QUEUE_NAME, "测试发送消息");
    }

    @Test
    public void sendDelayMsg2() throws Exception {
        messageQueueService.send(MQConstant.HELLO_QUEUE_NAME,"测试延迟发送消息",3000);
    }
}
