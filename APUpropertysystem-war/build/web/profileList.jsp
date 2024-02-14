<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.systemAdmin"%>
<%@page import="model.systemOwner"%>
<%@page import="model.systemCustomer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROFILE LIST</title>
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
            String lrole = (String) hts.getAttribute("lrole");
            System.out.println("name::" + name);
            List<systemAdmin> fsysA = (List<systemAdmin>) hts.getAttribute("fsysA");
            List<systemOwner> fsysO = (List<systemOwner>) hts.getAttribute("fsysO");
            List<systemCustomer> fsysC = (List<systemCustomer>) hts.getAttribute("fsysC");
        %>
        <br/>
        <div class="mar">
            <form action="profileSearch.jsp" type="POST">
                <input type="hidden" name="name" value="<%=name%>"/>
                <input type="hidden" name="role" value="<%=role%>"/>
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>


        <%
            if (lrole.equals("Customer") || lrole.equals("All")) {
        %>

        <br/>
        <h1 class="mar" >CUSTOMER LIST:</h1>

        <%
            for (systemCustomer sysC : fsysC) {
                String username = sysC.getId();
                String password = sysC.getPassword();
                char gender = sysC.getGender();
                String contact = sysC.getContact();
                String address = sysC.getAddress();
                int age = sysC.getAge();

        %>
        <div class="mar">
            <form action="profileListUpdateLogic" type="POST">
                <input type="hidden" name="aname" value="<%=name%>"/>
                <input type="hidden" name="role" value="Customer"/>
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
                            <input type="text" name="gmail" value="<%= sysC.getGmail()%>"/>
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
                            Account Created On <%= " " + sysC.getDOC() %>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="REMOVE PROFILE" formaction="accountRemovalLogic" class="btn"/>
                <input type="submit" value="UPDATE PROFILE" class="btn" />
            </form>
        </div>
        <%

                }
            }
        %>

        <%
            if (lrole.equals("Owner") || lrole.equals("All")) {
        %>

        <br/><br/>
        <h1 class="mar" >OWNER LIST:</h1>

        <%
            for (systemOwner sysO : fsysO) {
                String username = sysO.getId();
                String password = sysO.getPassword();
                char gender = sysO.getGender();
                String contact = sysO.getContact();
                String address = sysO.getAddress();
                int age = sysO.getAge();

        %>
        <div class="mar">
            <form action="profileListUpdateLogic" type="POST">
                <input type="hidden" name="aname" value="<%=name%>"/>
                <input type="hidden" name="role" value="Owner"/>
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
                            <input type="text" name="gmail" value="<%= sysO.getGmail()%>"/>
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
                            Account Created On <%= " " + sysO.getDOC()%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Account Approved By Admin <%= " " + sysO.getSystemAdmin().getId()%>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="REMOVE PROFILE" formaction="accountRemovalLogic" class="btn"/>
                <input type="submit" value="UPDATE PROFILE" class="btn" />
            </form>
        </div>
        <%

                }
            }
        %>

        <%
            if (lrole.equals("Admin") || lrole.equals("All")) {

        %>

        <br/><br/>
        <h1 class="mar" >ADMIN LIST:</h1>

        <%            for (systemAdmin sysA : fsysA) {
                if (!(sysA.getId().equals(name))) {
                    String username = sysA.getId();
                    String password = sysA.getPassword();
                    char gender = sysA.getGender();
                    String contact = sysA.getContact();
                    String address = sysA.getAddress();
                    int age = sysA.getAge();

        %>
        <div class="mar">
            <form action="profileListUpdateLogic" type="POST">
                <input type="hidden" name="aname" value="<%=name%>"/>
                <input type="hidden" name="role" value="Admin"/>
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
                            <input type="text" name="gmail" value="<%= sysA.getGmail()%>"/>
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
                            Account Created On <%= " " + sysA.getDOC()%>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="REMOVE PROFILE" formaction="accountRemovalLogic" class="btn"/>
                <input type="submit" value="UPDATE PROFILE" class="btn" />
            </form>
        </div>
        <%
                    }
                }
            }
        %>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
