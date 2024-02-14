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

            if (lctr == null) {
                String RRPath = "Home.jsp?name=" + name + "&amp;role=" + role;
                request.getRequestDispatcher(RRPath).forward(request, response);
            }
            System.out.println("name::" + name);
            String reviewType = role.equals("Customer") ? "Property Review" : "Review About Customer";
            String ratingType = role.equals("Customer") ? "Property Rating" : "Rating About Customer";
            String rp = role.equals("Customer") ? "customerHome.jsp" : "ownerHome.jsp";
            String returnPath = "returnLogic";
        %>
        <br/>
        <div class="mar">
            <form action="returnLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=rp%>"/>
                <br/>
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>

        <br>

        <% for (systemTransaction c : lctr) {
                if (c.getApproveStatus().equals("Approved")) {
                    int rating = 0;
                    String review = null;
                    if (role.equals("Customer")) {
                        rating = c.getCustomerRating() == 0 ? 0 : c.getCustomerRating() == 1 ? 1 : c.getCustomerRating() == 2 ? 2 : c.getCustomerRating() == 3 ? 3 : c.getCustomerRating() == 4 ? 4 : 5;
                        review = c.getCustomerReview() == null ? "" : c.getCustomerReview();
                    } else if (role.equals("Owner")) {
                        rating = c.getOwnerRating() == 0 ? 0 : c.getOwnerRating() == 1 ? 1 : c.getOwnerRating() == 2 ? 2 : c.getOwnerRating() == 3 ? 3 : c.getOwnerRating() == 4 ? 4 : 5;
                        review = c.getOwnerReview() == null ? "" : c.getOwnerReview();
                    }

        %>

        <div class="mar">
            <form action="reviewRatingUpdateLogic" type="POST">
                <input type="hidden" name="name" value=<%=name%>>
                <input type="hidden" name="role" value=<%=role%>>
                <input type="hidden" name="id" value=<%= c.getId()%>>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Property:
                        </td>
                        <td>
                            <input type="text" size="30" name="tproperty" value=<%= c.getPropertyId()%> readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Customer:
                        </td>
                        <td>
                            <input type="text" size="30" name="tcustomer" value=<%= c.getCustomerId()%> readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Owner:
                        </td>
                        <td>
                            <input type="text" size="30" name="towner" value=<%= c.getOwnerId()%> readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            <%= ratingType%>
                        </td>
                        <td>
                            <select name="trating">
                                <option value="0" <%= rating == 0 ? "selected" : null%>>-</option>
                                <option value="1" <%= rating == 1 ? "selected" : null%>>1</option>
                                <option value="2" <%= rating == 2 ? "selected" : null%>>2</option>
                                <option value="3" <%= rating == 3 ? "selected" : null%>>3</option>
                                <option value="4" <%= rating == 4 ? "selected" : null%>>4</option>
                                <option value="5" <%= rating == 5 ? "selected" : null%>>5</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            <%= reviewType%>
                        </td>
                        <td>
                            <input type="text" size="30" name="treview" value="<%= review%>"/>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="UPDATE REVIEW & RATING" class="btn"/>
            </form>
        </div>
        <br/>

        <% } else if (c.getApproveStatus().equals("Pending")) {
        %>
        <div class="mar">
            <form action="" type="POST">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Property:
                        </td>
                        <td>
                            <input type="text" size="30" name="tproperty" value="<%= c.getPropertyId()%>" readonly/>
                        </td>
                    </tr>
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
                        <td>
                            <input type="text" size="30" name="reject" value="Pending Transaction" readonly/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br/>

        <%
        } else {
        %>

        <div class="mar">
            <form action="" type="POST">
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Property:
                        </td>
                        <td>
                            <input type="text" size="30" name="tproperty" value="<%= c.getPropertyId()%>" readonly/>
                        </td>
                    </tr>
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
                        <td>
                            <input type="text" size="30" name="reject" value="Transaction Rejected" readonly/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <br/>
        <%
                }
            }
        %>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
