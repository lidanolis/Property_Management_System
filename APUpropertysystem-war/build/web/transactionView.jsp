<%@page import="model.systemProperty"%>
<%@page import="model.systemTransaction"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Record</title>
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
            List<systemTransaction> allT = (List<systemTransaction>) hts.getAttribute("allT");
            List<systemProperty> allP = (List<systemProperty>) hts.getAttribute("allP");
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");
            String returnPath = null;
            if (role.equals("Customer")) {
                returnPath = "customerHome.jsp";
            } else {
                returnPath = "ownerHome.jsp";
            }
            System.out.println("name::" + name);
        %>
        <br/>
        <div class="mar">
            <form action="returnLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <br/>
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>

        <br>
        <div class="mar">
            <%
                int i = 0;
                for (systemTransaction aT : allT) {%>
            <table class="tab">
                <tr>
                    <td class="txt">
                        Property:
                    </td>
                    <td>
                        <input type="text" value="<%= allP.get(i).getName()%>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="txt">
                        Payment:
                    </td>
                    <td>
                        <input type="text" value="<%= aT.getTotal()%>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="txt">
                        Date Acquired
                    </td>
                    <td>
                        <input type="text" value="<%= aT.getDOC()%>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="txt">
                        Payment Status:
                    </td>
                    <td>
                        <input type="text" value="<%= aT.getApproveStatus()%>" readonly/>
                    </td>
                </tr>
                <tr>
                    <td class="txt">
                        Acquired By Customer:
                    </td>
                    <td>
                        <input type="text" value="<%= aT.getCustomerId()%>" readonly/>
                    </td>
                </tr>
            </table>
            <br/>
            <% i++;
                }
            %>

        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
