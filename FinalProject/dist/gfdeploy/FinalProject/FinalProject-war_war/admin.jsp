<%-- 
    Document   : admin
    Created on : Nov 30, 2016, 6:52:39 PM
    Author     : 695923
--%>

<%@page import="newBean.UserSLSB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabledisplay" class="helpers.AdminPageHelper" />
<!DOCTYPE html>
<html>
    <%String user = (String) session.getAttribute("user");
        session.setAttribute("user", user);
        String message = request.getParameter("message");
    %>
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
        <title>Admin</title>
    </head>
    <body>

        <nav class="navbar navbar-default navbar-static-top">
            <div class="container">
                <h1>Admin Page</h1>
                <p>Welcome, <%=user%> <a href="LoginOps?logout=true"><input type="button" value="Logout" class="btn-default btn-xs"></a></p>
                <ul class="nav navbar-nav">
                    <li><a href="main.jsp">Go to Channels</a></li>

                </ul>
            </div>
        </nav>
        <div class="container">
            <%
                if (session.getAttribute("user") == null) {
                    UserSLSB usb = new UserSLSB();
                    request.setAttribute("alert", usb.loginError());
                    request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                } else if (session.getAttribute("admin") == null) {
                    response.sendRedirect("https://lmgtfy.com/?q=how+to+become+admin");
                }

                if (message != null) {
                    out.println(message);
                }
            %>
            <h2>Add New User</h2>
            <form action="UserOps2" method="Post">
                <table>
                    <tr><td>Username:</td><td><input type="text" name="username"></td></tr>
                    <tr><td>Password:</td><td><input type="text" name="password"></td></tr>
                    <tr><td>Confirm:</td><td><input type="text" name="confirm"></td></tr>
                    <tr><td>Is Admin</td><td><input type="checkbox" name="admin" value="1"></td></tr>
                    <tr><td>Is Locked</td><td> <input type="checkbox" name="locked" value="1"></td></tr>
                    <tr><td> <input type="submit" value="Add"></td></tr>
                </table>
            </form>

            <h2>User Details</h2>
            <table class="table table-hover">
                <th>Username</th><th>Type</th><th>Locked</th><th>Reset PW</th><th>Delete</th>
                    <%=tabledisplay.displayTable()%>
            </table>
        </div>
    </body>
</html>
