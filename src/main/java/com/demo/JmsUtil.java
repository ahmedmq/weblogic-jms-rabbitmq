package com.demo;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class JmsUtil {

    public final static String QUEUE_JNDI_NAME = "ForeignQueue";
    private static final String WEBLOGIC_JMS_URL = "file:/Users/amushtaq/bindings/";
    private final static String WEBLOGIC_JNDI_FACTORY_NAME = "com.sun.jndi.fscontext.RefFSContextFactory";
    private final static String CONNECTION_FACTORY_JNDI_NAME = "ForeignConnectionFactory";

    private static QueueConnection queueConnection = null;
    private static QueueSession queueSession = null;

    public static InitialContext getInitialContext()
            throws NamingException
    {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, WEBLOGIC_JNDI_FACTORY_NAME);
        env.put(Context.PROVIDER_URL, WEBLOGIC_JMS_URL);
        return new InitialContext(env);
    }

    public static QueueSession getQueueSession(Context ctx) throws NamingException, JMSException {
        QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ctx.lookup(CONNECTION_FACTORY_JNDI_NAME);
        queueConnection = queueConnectionFactory.createQueueConnection();
        queueConnection.start();
        queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        return queueSession;
    }

    public static void cleanUp() throws JMSException{
        if(queueSession != null)
            queueSession.close();
        if(queueConnection !=null)
            queueConnection.close();
    }

}
