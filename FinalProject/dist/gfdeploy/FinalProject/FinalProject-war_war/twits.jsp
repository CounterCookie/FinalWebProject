<%-- 
    Document   : twits
    Created on : Nov 30, 2016, 2:23:51 PM
    Author     : 695923
--%>

<%@page import="newBean.UserSLSB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabledisplay" class="helpers.MainPageHelper2" />
<!DOCTYPE html>
<%  if (session.getAttribute("user") == null) {
        UserSLSB usb = new UserSLSB();
        request.setAttribute("alert", usb.loginError());
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
    String user = (String) session.getAttribute("user");
    String channel = request.getParameter("channel");
    String channelid = request.getParameter("channelid");
    request.getSession().setAttribute("channelid", channelid);
    request.getSession().setAttribute("channel", channel);
    boolean status = tabledisplay.checkOwner(Integer.parseInt(channelid), user);%>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="javaBackground.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <h1>Welcome to <%=channel%>!</h1>
                <p>Welcome, <%=user%> <a href="LoginOps?logout=true"><input type="button" value="Logout" class="btn-default btn-xs"></a></p>
                <ul class="nav navbar-nav">
                    <li><a href="main.jsp">Home</a></li>
                    <li><a href="searchPage.jsp">Search for channels</a></li>
                    <li><a href="ownedChannels.jsp">My Channels</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <%if (status) {

            %><form action="twitops2" method="POST">
                <div class="form-group">
                    <textarea class="form-control" id="exampleTextarea" rows="3" name="newTwit" placeholder="What's going on?"></textarea><br> <input type="submit" name="Add" class="btn btn-info btn-sm">
                </div>

            </form><%            }%>

            <table class="table table-hover">
                <%
                    if (status) {
                %>
                <%=tabledisplay.ownerTable(Integer.parseInt(channelid))%>
                <% } else {
                %>
                <%=tabledisplay.channelTable(Integer.parseInt(channelid))%>
                <%}
                %>

            </table>
        </div>
    </body>
</html>
