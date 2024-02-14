<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROFILE SEARCH</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>

        <%
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String returnPath = "adminHome.jsp";
            System.out.println("name::" + name);
        %>
        <div class="mar">
            <form action="profileListLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>"/>
                <input type="hidden" name="role" value="Admin"/>
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Username:
                        </td>
                        <td>
                            <input type="text" size="30" name="lname"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Leave Empty If No Specific Criteria Is Required 
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Gender:
                        </td>
                        <td>
                            <select name="lgender">
                                <option value="All" >All</option>
                                <option value="M" >Male</option>
                                <option value="F" >Female</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Age:
                        </td>
                        <td>
                            <input type="text" size="30" name="lage"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Leave Empty If No Specific Criteria Is Required 
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Role:
                        </td>
                        <td>
                            <select name="lrole">
                                <option value="All" >All</option>
                                <option value="Admin" >Admin</option>
                                <option value="Customer" >Customer</option>
                                <option value="Owner" >Owner</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
                <input type="submit" value="SEARCH" class="btn"/>
            </form>
        </div>
    </body>
</html>
