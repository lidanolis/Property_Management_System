<%@page import="model.systemProperty"%>
<%@page import="model.systemTransaction"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROPERTY REVIEW & RATING REPORT</title>
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
            List<systemProperty> allP = (List<systemProperty>) hts.getAttribute("allP");
            List<systemTransaction> allT = (List<systemTransaction>) hts.getAttribute("allT");
            List<Integer> tranc = (List<Integer>) hts.getAttribute("tranc");
            System.out.println("name::" + name);
        %>
        <div class="mar">
            <h1 style="text-align: center">PROPERTY RATING & REVIEW REPORT</h1>
        </div>
        <br/>

        <%            int count = 0;
            for (systemProperty aprop : allP) {
                if (tranc.get(count) != 0) {

        %>
        <div class="mar">
            <form action="" type="POST">
                <table class="tab">
                    <tr class="seperator">
                        <td class="txt">
                            Property:
                        </td>
                        <td class="txt">
                            <%= aprop.getName()%>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Average Rating:
                        </td>
                        <td>
                            <input type="text" size="30" name="rating" value="<%=tranc.get(count)%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Reviews:
                        </td>
                    </tr>
                    <%
                        for (systemTransaction aTran : allT) {
                            if (aTran.getPropertyId() == aprop.getId() && aTran.getCustomerRating() != 0) {

                    %>
                    <tr>
                        <td>
                            <input type="text" size="30" name="review" value="<%= aTran.getCustomerReview()%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="reviewrating" value="<%= aTran.getCustomerRating()%>" readonly/>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>

                <br/>
            </form>
        </div>
        <% }
                count++;
            }
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
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
