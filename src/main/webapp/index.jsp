<%@ page contentType="text/html; encoding=utf-8" %>
<%@ page import="com.demo.MessagesRepository"%>
<html>
<head>
    <title>WebLogic Foreign JMS with RabbitMQ</title>
    <style>
        ul.no-bullets {
            padding: 0;
            list-style-type: none;
        }

        table.alternate-row-colors tr:nth-child(even) {
            background: #CCC
        }

        table.alternate-row-colors tr:nth-child(odd) {
            background: #FFF
        }
    </style>
</head>
<body>
<h1>Weblogic JMS RabbitMQ Test</h1>
<h2>Send message</h2>
<form action="${pageContext.request.contextPath}/messages" method="POST">
    <ul class="no-bullets">
        <li>
            <label>
                <textarea name="msg" rows="4" cols="50"></textarea>
            </label>
        </li>
        <li><input type="submit" value="send"/></li>
    </ul>
</form>
<h2>Received messages</h2>
<%! MessagesRepository msg = MessagesRepository.getInstance(); %>
<% if (msg.size() != 0) {%>
<%= msg %>
<%} else {%>
No messages... yet
<% } %>

</body>
</html>