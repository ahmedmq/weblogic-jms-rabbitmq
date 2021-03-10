package com.demo;

import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MessagesServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String msg = req.getParameter("msg");
        try {
            sendAndReadFromQueue(msg);
        } catch (JMSException | NamingException e) {
            e.printStackTrace();
            MessagesRepository.getInstance().add("ERROR!!"+ e.getMessage());
        }
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

    private void sendAndReadFromQueue(String message) throws JMSException, NamingException {
        QueuePublisher publisher = null;
        QueueReceiver receiver = null;
        try {
            InitialContext ctx = JmsUtil.getInitialContext();
            publisher = new QueuePublisher();
            publisher.init(ctx,JmsUtil.QUEUE_JNDI_NAME);
            publisher.send(message);

            // Read message of the queue
            receiver = new QueueReceiver();
            receiver.init(ctx,JmsUtil.QUEUE_JNDI_NAME);
        } finally {
            if (publisher != null)
                publisher.close();
            if (receiver != null)
                receiver.close();
        }
    }
}
