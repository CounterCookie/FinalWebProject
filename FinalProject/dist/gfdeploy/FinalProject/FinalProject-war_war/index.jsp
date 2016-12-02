<%-- 
    Document   : index
    Created on : Nov 28, 2016, 3:29:41 PM
    Author     : 695923
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS --> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="javaBackground.js"></script>
        <link rel="stylesheet" href="css/finalcss.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/animate.css/3.1.1/animate.css">
        <title>JSP Page</title>
    </head>

    <body>
        <div class="notification notification-success registered">
            You Registered successfully
        </div>
        <div class="notification loginError">
            Please log in
        </div>
        <div class="notification failRegister">
            Username already taken!
        </div>
         <div class="notification locked">
            Account has been locked!
        </div>
         <div class="notification invalidUser">
            Invalid username or password!
        </div>
        <div class="notification loginFields">
            Please fill in all fields
        </div>
        <div class="notification notification-success logout">
            Logged out, See you next time!
        </div>
        <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="notification login-alert">
                Please fill in every field!
            </div>
            <div class="notification passwords_dont_match">
                You passwords don't match!
            </div>


            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Register</h1><br>
                    <form id="registerForm" action="registerOps" method="POST">
                        <input type="text" name="user" placeholder="Username" id="username">
                        <input type="password" name="pass" placeholder="Password" id="password">
                        <input type="password" name="confirm" placeholder="confirm" id="confirm">
                        <input type="hidden" name="registerButton">
                        <input type="submit" name="login" class="login loginmodal-submit" id="register" value="Join Twit!">
                    </form>
                </div>
            </div>
        </div>

        <script>
            $('#register').click(function (e) {
                e.preventDefault();

                var element = $(this).parent().parent().parent();

                var usernameEmail = $('#username').val();
                var password = $('#password').val();
                var confirm = $('#confirm').val();

                if (usernameEmail == '' || password == '') {

                    // wigle and show notification
                    // Wigle
                    var element = $(this).parent().parent().parent();
                    $(element).addClass('animated rubberBand');
                    $(element).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        $(element).removeClass('animated rubberBand');
                    });

                    // Notification
                    // reset
                    $('.notification.login-alert').removeClass('bounceOutRight notification-show animated bounceInRight');
                    // show notification
                    $('.notification.login-alert').addClass('notification-show animated bounceInRight');

                    $('.notification.login-alert').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                        setTimeout(function () {
                            $('.notification.login-alert').addClass('animated bounceOutRight');
                        }, 2000);
                    });
                } else {
                    if (password != confirm) {
                        var element = $(this).parent().parent().parent();
                        $(element).addClass('animated rubberBand');
                        $(element).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            $(element).removeClass('animated rubberBand');
                        });

                        // Notification
                        // reset
                        $('.notification.passwords_dont_match').removeClass('bounceOutRight notification-show animated bounceInRight');
                        // show notification
                        $('.notification.passwords_dont_match').addClass('notification-show animated bounceInRight');

                        $('.notification.passwords_dont_match').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                            setTimeout(function () {
                                $('notification.passwords_dont_match').addClass('animated bounceOutRight');
                            }, 2000);
                        });
                    } else {
                        $(element).fadeOut(function () {
                            $('.logged-in').fadeIn();
                            $('#registerForm').submit();
                        });
                    }//endif
                }
            });


        </script>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="pr-wrap">
                        <div class="pass-reset">
                            <label>
                               This is just a place holder</label>
                            <input type="email" placeholder="Email" />F
                            <input type="submit" value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
                        </div>
                    </div>
                    <div class="box">
                        <div class="wrap">
                            <p class="form-title">
                                Twit Login</p>
                            <form class="login" action="LoginOps" method="POST">
                                <input type="text" placeholder="Username" name="user" id="username" />
                                <input type="password" placeholder="Password" name="pass" id="password" />
                                <input type="submit" value="Sign In" id="loginsubmit" class="btn btn-success btn-sm" />
                                <a href="register.jsp" data-toggle="modal" data-target="#login-modal">Register</a>
                            </form>
                            <%
                                String message = request.getParameter("message");
                                if (message != null) {
                            %>
                            <p class="error_message"><%=message%></p>
                            <%
                                }
                            %>               
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%=request.getAttribute("alert")==null?"":request.getAttribute("alert")%>
</html>


