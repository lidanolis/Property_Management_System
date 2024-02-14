<%@page import="model.systemProperty"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROPERTY REPORT</title>
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
            String total = (String) hts.getAttribute("total");
            String rent = (String) hts.getAttribute("rent");
            String buy = (String) hts.getAttribute("buy");

            List<systemProperty> allP = (List<systemProperty>) hts.getAttribute("allP");
            List<Integer> tranc = (List<Integer>) hts.getAttribute("tranc");
        %>
        <div class="mar">
            <h1 style="text-align: center">PROPERTY REPORT</h1>
        </div>
        <br/>
        <div class="mar">
            <form action="" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value=""<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>

                <table class="tab">
                    <tr>
                        <td class="txt">
                            Total Number of Property(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="total" value="<%=total%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Number of "Rent" Property(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="rent" value="<%=rent%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Number of "Buy" Property(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="buy" value="<%=buy%>" readonly/>
                        </td>
                    </tr>
                </table>
                <br/>

                <table class="tab">
                    <tr class="seperator">
                        <td class="txt">
                            Property ID
                        </td>
                        <td class="txt">
                            Property Name
                        </td>
                        <td class="txt"> 
                            Sales Count
                        </td>
                    </tr>

                    <%
                        for (int i = 0; i < allP.size(); i++) {
                    %>
                    <tr>
                        <td>
                            <input type="text" size="30" name="aprop" value="<%= allP.get(i).getId()%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="propname" value="<%= allP.get(i).getName()%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="propcount" value="<%= tranc.get(i)%>" readonly/>
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
