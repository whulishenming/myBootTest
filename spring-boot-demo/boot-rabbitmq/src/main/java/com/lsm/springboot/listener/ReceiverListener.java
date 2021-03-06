package com.lsm.springboot.listener;

import com.lsm.springboot.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lishenming on 2017/6/12.
 */
@Component
public class ReceiverListener {

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.OBJECT_QUEUE_NAME, containerFactory="rabbitListenerContainerFactory")
    public void processObject(Map<String, String> message) {

        System.out.println("Receiver object : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = {RabbitMQConfig.STRING_QUEUE_NAME, RabbitMQConfig.STRING_QUEUE_NAME_2}, containerFactory="rabbitListenerContainerFactory")
    public void processString(String message) {

        System.out.println("Receiver String message : " + message);

    }

   /* @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.STRING_QUEUE_NAME, containerFactory="rabbitListenerContainerFactory")
    public void processString(Message message, Channel channel) {
        String messageId = message.getMessageProperties().getMessageId();
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strMessage = new String(message.getBody());
        System.out.println("Receiver string : " + message);

//        throw new  RuntimeException("error");

    }*/



    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.MESSAGE, containerFactory="rabbitListenerContainerFactory")
    public void processTopicMassage(String message) {

        System.out.println("Receiver topic massage : " + message);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.MESSAGES, containerFactory="rabbitListenerContainerFactory")
    public void processTopicMassages(String message) {

        System.out.println("Receiver topic massages : " + message);
    }

    @RabbitListener(queues = "fanout.A", containerFactory="rabbitListenerContainerFactory")
    public void processFanoutA(String message) {

        System.out.println("fanout.A Receiver : " + message);
    }

    @RabbitListener(queues = "fanout.B", containerFactory="rabbitListenerContainerFactory")
    public void processFanoutB(String message) {

        System.out.println("fanout.B Receiver : " + message);
    }

    @RabbitListener(queues = "fanout.C", containerFactory="rabbitListenerContainerFactory")
    public void processFanoutC(String message) {

        System.out.println("fanout.C Receiver : " + message);
    }
}
