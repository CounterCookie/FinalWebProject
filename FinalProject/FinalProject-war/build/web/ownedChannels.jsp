<%-- 
    Document   : main
    Created on : Nov 29, 2016, 1:35:41 PM
    Author     : 695923
--%>

<%@page import="newBean.UserSLSB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabledisplay" class="helpers.MainPageHelper2" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="javaBackground.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.css">
    </head>
    <%
        if (session.getAttribute("user") == null) {
            UserSLSB usb = new UserSLSB();
            request.setAttribute("alert", usb.loginError());
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
        String user1 = (String) session.getAttribute("user");
        session.setAttribute("user1", user1);
    %>
    <body>
        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <h1>My Channels</h1>
                <p>Welcome, <%=user1%> <a href="LoginOps?logout=true"><input type="button" value="Logout" class="btn-default btn-xs"></a></p>
                <ul class="nav navbar-nav">
                    <li><a href="main.jsp">Home</a></li>
                    <li><a href="searchPage.jsp">Search for channels</a></li>
                    <li><a href="ownedChannels.jsp">My Channels</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <h2>My Channels</h2>
            <form action="ChannelOps" method="POST">
                New Channel:<input type="text" name="newChannel"><input type="submit" value="Add Channel" class="btn btn-info btn-sm">
            </form>
            <table class="table table-hover">
                <th>Channel</th><th>Last Twit</th><th>Delete</th>
                    <%=tabledisplay.displayOwnedChanell(user1)%>
            </table>
        </div>

    </body>
</html>
