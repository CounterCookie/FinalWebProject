<%-- 
    Document   : admin
    Created on : Nov 30, 2016, 6:52:39 PM
    Author     : 695923
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%String user =(String) session.getAttribute("user");
       session.setAttribute("user", user);
       String message= request.getParameter("message");
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h2>Twits Admin</h2>
        <%
            if(message!=null){
                out.println(message);
            }
        %>
        <p>Welcome, <%=user%> <a href="main.jsp">Go to channels</a> <a href="index.jsp"> Logout</a></p>
        
        <h2>Add New User</h2>
        <form action="UserOps" method="Post">
            Username:<input type="text" name="username"><br>
            Password:<input type="text" name="password"><br>
            Confirm:<input type="text" name="confirm"><br>
            Is Admin <input type="checkbox" name="admin" value="true"><br>
            Is Locked <input type="checkbox" name="locked" value="true"><br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>
