<%@page import="java.util.List"%>
<%@page import="model.systemOwner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OWNER APPROVAL</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <script>
            function totop() {
                window.scrollTo(0, 0);
            }
        </script>
        <%
            HttpSession hts = request.getSession(false);
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");
            List<systemOwner> fsysO = (List<systemOwner>) hts.getAttribute("fsysO");
            System.out.println("name::" + name);
        %>

        <%
            for (systemOwner sysO : fsysO) {
                String username = sysO.getId();
                String password = sysO.getPassword();
                char gender = sysO.getGender();
                String gen = (gender == 'M') ? "Male" : "Female";
                String contact = sysO.getContact();
                String address = sysO.getAddress();
                int age = sysO.getAge();

        %>
        <div class="mar">
            <form action="ownerApproveLogic" type="POST">
                <input type="hidden" name="aname" value="<%=name%>"/>
                <input type="hidden" name="arole" value="<%=role%>"/>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Username:
                        </td>
                        <td>
                            <input type="text" size="30" name="name" value="<%=username%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Password:
                        </td>
                        <td>
                            <input type="text" size="30" name="password" value="<%=password%>" readonly/>
                        </td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td class="txt">
                            Gmail:
                        </td>
                        <td>
                            <input type="text" name="gmail" value="<%= sysO.getGmail() %>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Gender:
                        </td>
                        <td>
                            <input type="text" size="30" name="gender" value="<%=gen%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Contact Number:
                        </td>
                        <td>
                            <input type="text" size="30" name="contact" value="<%=contact%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Address:
                        </td>
                        <td>
                            <input type="text" name="address" value="<%=address%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Age:
                        </td>
                        <td>
                            <input type="text" size="30" name="age" value="<%=age%>" readonly/>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="REJECT" formaction="ownerRejectLogic" class="btn"/>
                <input type="submit" value="APPROVE" class="btn"/>
            </form>
        </div>
        <%

            }
        %>
        <br/>
        <div class="mar">
            <form action="returnLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="adminHome.jsp"/>
                <br/>
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
