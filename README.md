# Integrating WebLogic Server with RabbitMQ(JMS)
This repo shows how to connect an application deployed on WebLogic to RabbitMQ(JMS). The sample application connects to RabbitMQ to publish a message onto a queue and also reads back the same message from the queue.

## Configuring JMS JNDI namespace

We need to create `.bindings` file which will act as a file-based JNDI provider to create the JMS binding to RabbitMQ QueueConnectionFactory and Queue Objects.
The `sample.bindings` file provided configures the following JNDI objects:

* ForeignConnectionFactory

* ForeignQueue

```
#Define the Connection Factory object
ForeignConnectionFactory/ClassName=javax.jms.ConnectionFactory
ForeignConnectionFactory/FactoryName=com.rabbitmq.jms.admin.RMQObjectFactory
ForeignConnectionFactory/RefAddr/0/Content=jms/ForeignConnectionFactory
ForeignConnectionFactory/RefAddr/0/Type=name
ForeignConnectionFactory/RefAddr/0/Encoding=String
ForeignConnectionFactory/RefAddr/1/Content=javax.jms.ConnectionFactory
ForeignConnectionFactory/RefAddr/1/Type=type
ForeignConnectionFactory/RefAddr/1/Encoding=String
ForeignConnectionFactory/RefAddr/2/Content=com.rabbitmq.jms.admin.RMQObjectFactory
ForeignConnectionFactory/RefAddr/2/Type=factory
ForeignConnectionFactory/RefAddr/2/Encoding=String

# Define the location of the broke, likely localhost, and the VirtualHost these resources will reside in
ForeignConnectionFactory/RefAddr/3/Content=127.0.0.1
ForeignConnectionFactory/RefAddr/3/Type=host
ForeignConnectionFactory/RefAddr/3/Encoding=String
ForeignConnectionFactory/RefAddr/4/Content=/
ForeignConnectionFactory/RefAddr/4/Type=virtualHost
ForeignConnectionFactory/RefAddr/4/Encoding=String

# Define an example Queue
ForeignQueue/ClassName=javax.jms.Queue
ForeignQueue/FactoryName=com.rabbitmq.jms.admin.RMQObjectFactory
ForeignQueue/RefAddr/0/Content=jms/Queue
ForeignQueue/RefAddr/0/Type=name
ForeignQueue/RefAddr/0/Encoding=String
ForeignQueue/RefAddr/1/Content=javax.jms.Queue
ForeignQueue/RefAddr/1/Type=type
ForeignQueue/RefAddr/1/Encoding=String
ForeignQueue/RefAddr/2/Content=com.rabbitmq.jms.admin.RMQObjectFactory
ForeignQueue/RefAddr/2/Type=factory
ForeignQueue/RefAddr/2/Encoding=String
ForeignQueue/RefAddr/3/Content=ForeignQueue
ForeignQueue/RefAddr/3/Type=destinationName
ForeignQueue/RefAddr/3/Encoding=String
```

To connect Rabbit MQ using JMS API, `RefFSContextFactory` is used as the Initial Context Factory to lookup JNDI objects using the bindings file created above.
The `.bindings` file must be stored in a local file path and should also be updated in the `JMSUtil` class for the variable `WEBLOGIC_JMS_URL` 

## Building the sample application and deploying it on WebLogic

`mvn clean install`

This creates a .war which needs to be deployed on Weblogic 12.1.3

The below screenshot displays the JSP page of the application after deploying onto WebLogic:
![JSP Frontend](/images/JSP_Frontend.png)

Type any text in the given text area and click on **Send**. This will send the message to the RabbitMQ queue (`ForeignQueue`) and the listener configured will read back the same message from the queue and display it on the screen.

