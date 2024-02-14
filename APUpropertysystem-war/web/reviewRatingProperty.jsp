<%@page import="java.util.List"%>
<%@page import="model.systemTransaction"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review & Rating</title>
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
            String cpropName = (String) hts.getAttribute("propName");
            String cpropType = (String) hts.getAttribute("propType");
            String cpropDur = (String) hts.getAttribute("propDur");
            String cpriceRange = (String) hts.getAttribute("priceRange");
            String cpropPrice = (String) hts.getAttribute("propPrice");
            String cpropStatus = (String) hts.getAttribute("propStatus");
                        System.out.println("name::" + name);
        %>
        <div class="mar">
            <form action="propertyViewLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">

                <input type="hidden" name="propName" value="<%= cpropName%>">
                <input type="hidden" name="propType" value="<%= cpropType%>">
                <input type="hidden" name="propDur" value="<%= cpropDur%>">
                <input type="hidden" name="priceRange" value="<%= cpriceRange%>">
                <input type="hidden" name="propPrice" value="<%= cpropPrice%>">
                <input type="hidden" name="propStatus" value="<%= cpropStatus%>">

                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>

        <%
            if (!(lctr.isEmpty())) {
                for (systemTransaction c : lctr) {
                    if (c.getApproveStatus().equals("Approved")) {

                        int rating = 0;
                        String review = null;
                        rating = c.getCustomerRating() == 0 ? 0 : c.getCustomerRating() == 1 ? 1 : c.getCustomerRating() == 2 ? 2 : c.getCustomerRating() == 3 ? 3 : c.getCustomerRating() == 4 ? 4 : 5;
                        review = c.getCustomerReview() == null ? "-" : c.getCustomerReview();
                        if (rating == 0) {
                            continue;
                        }
        %>

        <div class="mar">
            <form action="" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="id" value="<%= c.getId()%>">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Customer:
                        </td>
                        <td>
                            <input type="text" size="30" name="tcustomer" value="<%=c.getCustomerId()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Rating:
                        </td>
                        <td>
                            <input type="text" size="30" name="trating" value="<%= rating%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Review:
                        </td>
                        <td>
                            <input type="text" size="30" name="treview" value="<%= review%>" readonly/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br/>
        <% }
                }
            } else {
                System.out.println("no tran");
            }
        %>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
