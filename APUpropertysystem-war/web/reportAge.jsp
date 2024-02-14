<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AGE REPORT</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <script>
            function totop() {
                window.scrollTo(0, 0);
            }
        </script>
        <%
            String returnPath = "adminHome.jsp";

            HttpSession hts = request.getSession(false);
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");
            System.out.println("name::" + name);
            List<Integer> cage = (List<Integer>) hts.getAttribute("cage");
            List<Integer> oage = (List<Integer>) hts.getAttribute("oage");
            List<Integer> aage = (List<Integer>) hts.getAttribute("aage");

            List<Integer> cagec = (List<Integer>) hts.getAttribute("cagec");
            List<Integer> oagec = (List<Integer>) hts.getAttribute("oagec");
            List<Integer> aagec = (List<Integer>) hts.getAttribute("aagec");
        %>
        <div class="mar">
            <h1 style="text-align: center">AGE REPORT</h1>
        </div>
        <br/>
        <div class="mar">
            <form action="" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <table class="tab">
                    <tr class="seperator">
                        <td class="txt">
                            User
                        </td>
                        <td class="txt">
                            Age
                        </td>
                        <td class="txt">
                            Count(Per Person)
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Customer
                        </td>
                    </tr>
                    <%
                        for (int i = 0; i < cage.size(); i++) {
                    %>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="text" size="30" name="cage" value="<%= cage.get(i)%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="cagec" value="<%=cagec.get(i)%>" readonly/>
                        </td>
                    </tr>
                    <% }
                    %>

                    <tr class="seperator">
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Owner
                        </td>
                    </tr>
                    <%
                        for (int i = 0; i < oage.size(); i++) {
                    %>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="text" size="30" name="oage" value="<%=oage.get(i)%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="oagec" value="<%=oagec.get(i)%>" readonly/>
                        </td>
                    </tr>
                    <% }
                    %>

                    <tr class="seperator">
                        <td>
                        </td>
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Admin
                        </td>
                    </tr>
                    <%
                        for (int i = 0; i < aage.size(); i++) {
                    %>
                    <tr>
                        <td>
                        </td>
                        <td>
                            <input type="text" size="30" name="aage" value="<%=aage.get(i)%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="aagec" value="<%=aagec.get(i)%>" readonly/>
                        </td>
                    </tr>
                    <% }
                    %>
                </table>
                <br/>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
            </form>
        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
