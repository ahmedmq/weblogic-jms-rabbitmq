package com.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;

public class QueuePublisher {

    private QueueSender queueSender;
    private Queue queue;
    private TextMessage textMessage;

    public void init(Context ctx, String queueName)throws NamingException, JMSException
    {
        QueueSession queueSession = JmsUtil.getQueueSession(ctx);
        queue = (Queue) ctx.lookup(queueName);
        queueSender = queueSession.createSender(queue);
        textMessage = queueSession.createTextMessage();
    }

    public void send(String message) throws JMSException {
        textMessage.setText(message);
        queueSender.send(textMessage);
    }

    public void close() throws JMSException {
        if(queueSender !=null)
            queueSender.close();
        JmsUtil.cleanUp();
    }

}