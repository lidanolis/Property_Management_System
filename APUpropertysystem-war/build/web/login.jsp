<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
    </head>
        <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <div class="nav mar">
            <ul>
                <li> <a href="main.jsp">BACK</a>
                </li>
                <li> <a href="register.jsp">REGISTER</a>
                </li>  
            </ul>
        </div>
        <br></br>
        
        <div class="mar">
            <form action="loginLogic" type="POST">
                <table  class="tab">
                    <tr>
                        <td class="txt">
                            Username:
                        </td>
                        <td>
                            <input type="text" name="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Password:
                        </td>
                        <td>
                            <input type="text" name="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Role:
                        </td>
                        <td>
                            <select name="role">
                                <option value="Admin">Admin</option>
                                <option value="Owner">Owner</option>
                                <option value="Customer">Customer</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="LOGIN" class="btn"/>
            </form>
        </div>
        
    </body>
</html>
