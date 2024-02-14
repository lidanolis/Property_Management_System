<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTER AN ACCOUNT</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <div class="nav mar">
            <ul>
                <li> <a href="main.jsp">BACK</a>
                </li>
                <li> <a href="login.jsp">LOGIN</a>
                </li>  
            </ul>
        </div>


        <br></br>

        <div class="mar">
            <form action="registerLogic" type="POST">
                <table class="tab">
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
                    
                    <br/><br/>
                    <tr>
                        <td class="txt">
                            Gmail:
                        </td>
                        <td>
                            <input type="text" name="gmail"/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Gender:
                        </td>
                        <td>
                            <select name="gender">
                                <option value="M">Male</option>
                                <option value="F">Female</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Contact Number:
                        </td>
                        <td>
                            <input type="text" name="contact"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Address:
                        </td>
                        <td>
                            <input type="text" name="address"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Age:
                        </td>
                        <td>
                            <input type="text" name="age"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Role:
                        </td>
                        <td>
                            <select name="role">
                                <option value="Owner">Owner</option>
                                <option value="Customer">Customer</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="REGISTER" class="btn"/>
            </form>
        </div>
    </body>
</html>
