<%-- 
    Document   : twits
    Created on : Nov 30, 2016, 2:23:51 PM
    Author     : 695923
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String user = (String) session.getAttribute("user");
   String channel = request.getParameter("channel");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Twits</h1>
        <p>Welcome <%=user%>, <a href="index.jsp">logout</a></p>
        <h1>Channel: <%=channel%></h1>
    </body>
</html>
