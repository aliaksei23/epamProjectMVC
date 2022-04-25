<%--
  Created by IntelliJ IDEA.
  User: kamaz
  Date: 4/23/2022
  Time: 7:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Registration here =${reg_here}
<br/>
<form action="controller">
    <%--    <input type="hidden" name="command" value="registration"/>--%>
    Name: <input type="text" name="User_name" value=""/>
    <br/>
    Surname: <input type="text" name="user_surname" value=""/>
    <br/>
    Email: <input type="email" name="user_email" value=""/>
    <br/>
    Phone number: <input type="text" name="user_phone_number" value=""/>
    <br/>
    Login: <input type="text" name="login" value=""/>
    <br/>
    Password: <input type="password" name="pass" minlength="4" value=""/>
    <br/>
    Confirm password : <input type="password" name="confirm-password" minlength="4"/>
    <br/>
</form>
</body>
</html>
