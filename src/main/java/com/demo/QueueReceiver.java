package com.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.NamingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QueueReceiver implements MessageListener {

    private javax.jms.QueueReceiver queueReceiver;

    @Override
    public void onMessage(Message message)
    {
        try {
            String jmsTextMessage;
            if (message instanceof TextMessage) {
                jmsTextMessage = ((TextMessage) message).getText();
            } else {
                jmsTextMessage = message.toString();
            }
            System.out.println("Message Received: " + jmsTextMessage);
            // Add the received message to the repository
            final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            MessagesRepository.getInstance().add(new StringBuilder()
                    .append("<b>")
                    .append(dateFormat.format(new Date()).toString())
                    .append(": ")
                    .append("</b>")
                    .append(jmsTextMessage)
                    .toString());
        } catch (JMSException jmse) {
            System.err.println("An exception occurred: " + jmse.getMessage());
        }
    }

    public void init(Context ctx, String queueName)throws NamingException, JMSException
    {
        QueueSession queueSession = JmsUtil.getQueueSession(ctx);
        Queue queue = (Queue) ctx.lookup(queueName);
        queueReceiver = queueSession.createReceiver(queue);
        queueReceiver.setMessageListener(this);
    }

    public void close() throws JMSException
    {
        if(queueReceiver !=null)
            queueReceiver.close();
        JmsUtil.cleanUp();
    }

}

