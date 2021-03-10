# Integrating WebLogic Server with RabbitMQ(JMS)
This repo shows how to connect an application deployed on WebLogic to RabbitMQ(JMS). The sample application included uses a JSP webpage to send and receive message from a RabbitMQ queue.

## Configuring JMS JNDI namespace

We need to create `.bindings` file which will act as a file-based JNDI provider to create JMS binding to RabbitMQ QueueConnectionFactory and Queue Objects.
A sample `.bindings` file is provided in this repo which configures the following JNDI objects:

ReceiverQCF

SenderQCF

RabbitQueue

To connect Rabbit MQ using JMS API, `RefFSContextFactory` has to be used as Initial Context Factory to lookup JNDI objects. 

## Configuring a WebLogic foreign JMS server

### Create a JMS Module

![Creating JMS Module](/images/01_Create_JMS_Module.png)

### Create a foreign server
![Creating Foreign Server](/images/02_Create_Foreign_Server.png)

### Create foreign connection factories

### Create foreign Destination Queues

## Building the sample application and deploying it on WebLogic
