<%@page import="java.time.LocalDate"%>
<%@page import="model.systemCustomer"%>
<%@page import="model.systemOwner"%>
<%@page import="model.systemAdmin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROFILE</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <%
            systemCustomer sysC = null;
            systemOwner sysO = null;
            systemAdmin sysA = null;

            HttpSession hts = request.getSession(false);
            String role = (String) hts.getAttribute("role");

            if (role.equals("Admin")) {
                sysA = (systemAdmin) hts.getAttribute("account");

            } else if (role.equals("Owner")) {
                sysO = (systemOwner) hts.getAttribute("account");

            } else if (role.equals("Customer")) {
                sysC = (systemCustomer) hts.getAttribute("account");

            }

            String username = sysA != null ? sysA.getId() : sysC != null ? sysC.getId() : sysO.getId();
            String password = sysA != null ? sysA.getPassword() : sysC != null ? sysC.getPassword() : sysO.getPassword();
            char gender = sysA != null ? sysA.getGender() : sysC != null ? sysC.getGender() : sysO.getGender();
            String contact = sysA != null ? sysA.getContact() : sysC != null ? sysC.getContact() : sysO.getContact();
            String address = sysA != null ? sysA.getAddress() : sysC != null ? sysC.getAddress() : sysO.getAddress();
            String gmail = sysA != null ? sysA.getGmail() : sysC != null ? sysC.getGmail() : sysO.getGmail();
            LocalDate date = sysA != null ? sysA.getDOC() : sysC != null ? sysC.getDOC() : sysO.getDOC();
            int age = sysA != null ? sysA.getAge() : sysC != null ? sysC.getAge() : sysO.getAge();
            String returnPath = sysA != null ? "adminHome.jsp" : sysC != null ? "customerHome.jsp" : "ownerHome.jsp";
            String approval = sysO != null ? sysO.getSystemAdmin().getId() : null;
        %>

        <div class="mar">
            <form action="profileUpdateLogic" type="POST">
                <input type="hidden" name="role" value="<%=role%>"/>
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
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
                            <input type="text" size="30" name="password" value="<%=password%>"/>
                        </td>
                    </tr>
                    <br/><br/>
                    <tr>
                        <td class="txt">
                            Gmail:
                        </td>
                        <td>
                            <input type="text" name="gmail" value="<%=gmail%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Gender:
                        </td>
                        <td>
                            <select name="gender">
                                <option value="M" <%= gender == 'M' ? "selected" : null%> >Male</option>
                                <option value="F" <%= gender == 'F' ? "selected" : null%> >Female</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Contact Number:
                        </td>
                        <td>
                            <input type="text" size="30" name="contact" value="<%=contact%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Address:
                        </td>
                        <td>
                            <input type="text" name="address" value="<%=address%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Age:
                        </td>
                        <td>
                            <input type="text" size="30" name="age" value="<%=age%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            Account Created On <%= " " + date%>
                        </td>
                    </tr>
                    <%
                        if (approval != null) {
                    %>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Account Approved By Admin <%= " " + approval%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
                <input type="submit" value="UPDATE PROFILE" class="btn"/>
            </form>
        </div>
    </body>
</html>
