<%-- 
    Document   : twits
    Created on : Nov 30, 2016, 2:23:51 PM
    Author     : 695923
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabledisplay" class="helpers.MainPageHelper" />
<!DOCTYPE html>
<% String user = (String) session.getAttribute("user");
   String channel = request.getParameter("channel");
   String channelid = request.getParameter("channelid");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Twits</h1>
        <p>Welcome <%=user%>, <a href="index.jsp">logout</a></p>
        <h1>Channel: <%=channel%></h1>
        <p><b>Twits</b></p>
        <table border="1">
            <th>Twit</th><th>Date/Time</th><th>Delete</th>
            <%=tabledisplay.channelTable(Integer.parseInt(channelid))%>
        </table>
        <a href="main.jsp">Back to main</a>
    </body>
</html>
