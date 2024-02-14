<%@page import="java.util.List"%>
<%@page import="model.systemTransaction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PENDING TRANSACTION</title>
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
            List<systemTransaction> lctr = (List<systemTransaction>) hts.getAttribute("transactionList");
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");
                        System.out.println("name::" + name);

        %>
        <br/>
        <div class="mar">
            <form action="returnLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="ownerHome.jsp">

                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>
        <br/>

        <% for (systemTransaction c : lctr) {
        %>

        <div class="mar">
            <form action="transactionApproveLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="id" value="<%= c.getId()%>">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Customer:
                        </td>
                        <td>
                            <input type="text" size="30" name="tcustomer" value="<%= c.getCustomerId()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Owner:
                        </td>
                        <td>
                            <input type="text" size="30" name="towner" value="<%= c.getOwnerId()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Date of Acquire:
                        </td>
                        <td>
                            <input type="text" size="30" name="tdate" value="<%= c.getDOC()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total:
                        </td>
                        <td>
                            <input type="text" size="30" name="ttotal" value="<%= c.getTotal()%>" readonly/>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="APPROVE"  class="btn"/>
                <input type="submit" value="REJECT" formaction="transactionRejectLogic"  class="btn"/>
            </form>
        </div>

        <% }%>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
