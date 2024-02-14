

<%@page import="model.systemOwner"%>
<%@page import="model.systemCustomer"%>
<%@page import="model.systemTransaction"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER REVIEW & RATING REPORT</title>
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
            List<systemTransaction> allT = (List<systemTransaction>) hts.getAttribute("allT");
            List<systemCustomer> fallc = (List<systemCustomer>) hts.getAttribute("fallC");
            List<systemOwner> fallo = (List<systemOwner>) hts.getAttribute("fallO");
            System.out.println("name::" + name);
            List<Integer> tranc = (List<Integer>) hts.getAttribute("tranc");
            List<Integer> trano = (List<Integer>) hts.getAttribute("trano");
            System.out.println("name::" + name);
        %>

        <div class="mar">
            <h1 style="text-align: center">USER RATING & REVIEW REPORT</h1>
        </div>


        <br/>
        <h1 class="mar" >CUSTOMER LIST:</h1>
        <%            int count = 0;
            for (systemCustomer ac : fallc) {
                if (tranc.get(count) != 0) {

        %>
        <div class="mar">
            <form action="" type="POST">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Customer Name:
                        </td>
                        <td class="txt">
                            <%= ac.getId()%>
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
                            if (aTran.getCustomerId().equals(ac.getId()) && aTran.getOwnerRating() != 0) {

                    %>
                    <tr>
                        <td>
                            <input type="text" size="30" name="review" value="<%= aTran.getOwnerReview()%>" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="reviewrating" value="<%= aTran.getOwnerRating()%>" readonly/>
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
        <br/><br/>
        <h1 class="mar" >OWNER LIST:</h1>
        <%
            count = 0;
            for (systemOwner ao : fallo) {
                if (trano.get(count) != 0) {

        %>
        <div class="mar">
            <form action="" type="POST">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Owner Name:
                        </td>
                        <td class="txt">
                            <%= ao.getId()%>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Average Rating:
                        </td>
                        <td>
                            <input type="text" size="30" name="rating" value="<%=trano.get(count)%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Reviews:
                        </td>
                    </tr>
                    <%
                        for (systemTransaction aTran : allT) {
                            if (aTran.getOwnerId().equals(ao.getId()) && aTran.getCustomerRating() != 0) {

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
    </body>
</html>
