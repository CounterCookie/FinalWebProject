<%-- 
    Document   : main
    Created on : Nov 29, 2016, 1:35:41 PM
    Author     : 695923
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabledisplay" class="helpers.MainPageHelper" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String user1 =(String) session.getAttribute("user");
    %>
    <body>
        <h1>Twit Main Page</h1>
        <p>Welcome, <%=user1%> <a href="index.jsp">Logout</a></p>
        <h2>Channels I'm Following</h2>
        <table border="1">
            <th>Channel</th><th>User</th><th>Last Twit</th><th>Unfollow</th>
            <%=tabledisplay.displayTable(user1)%>
        </table>    
        <br>
        <h2>Find channels</h2>
        <form action="#" method="POST">
            Username:<input type="text" name="userChannel">
            <br>
            Channel<input type="text" name="channelName">
            <br>
            <input type="submit" name="findChannel">
        </form>
        <%
            String userChannel= request.getParameter("userChannel");
            String channelName = request.getParameter("channelName");
            if(userChannel!=null&&channelName!=null){
                %>
                <%=tabledisplay.displayFind(channelName, userChannel, user1)%>
                <%
            }
        %>
        <h2>My Channels</h2>
        <form action="ChannelOps" method="POST">
            New Channel:<input type="text" name="newChannel"><input type="submit" value="Add Channel">
        </form>
        <table border="1">
            <th>Channel</th><th>Last Twit</th><th>Delete</th>
        </table>
    </body>
</html>
