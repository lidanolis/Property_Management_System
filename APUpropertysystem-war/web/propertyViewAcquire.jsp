<%@page import="java.util.List"%>
<%@page import="model.systemProperty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW PROPERTY</title>
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
            List<systemProperty> allProp = (List<systemProperty>) hts.getAttribute("properties");
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");
            System.out.println("name::" + name);
            String cpropName = (String) hts.getAttribute("propName");
            String cpropType = (String) hts.getAttribute("propType");
            String cpropDur = (String) hts.getAttribute("propDur");
            String cpriceRange = (String) hts.getAttribute("priceRange");
            String cpropPrice = (String) hts.getAttribute("propPrice");
            String cpropStatus = (String) hts.getAttribute("propStatus");
            float balance = (Float) hts.getAttribute("balance");
        %>
        <br/>
        <div class="mar">
            <form action="propertySearch.jsp" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <br/>
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>

        <br>

        <%
            for (systemProperty aProp : allProp) {
                String status = aProp.getStatus().equals("Available") ? "Available" : aProp.getStatus().equals("NAvailable") ? "Not Available" : "Rented/Sold";
                float total = aProp.getPrice() - aProp.getDiscount();
                int possibleD = (int) (balance / total);
        %>

        <div class="mar">
            <form action="propertyAcquireLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="propID" value="<%= aProp.getId()%>">

                <input type="hidden" name="cpropName" value="<%= cpropName%>">
                <input type="hidden" name="cpropType" value="<%= cpropType%>">
                <input type="hidden" name="cpropDur" value="<%= cpropDur%>">
                <input type="hidden" name="cpriceRange" value="<%= cpriceRange%>">
                <input type="hidden" name="cpropPrice" value="<%= cpropPrice%>">
                <input type="hidden" name="cpropStatus" value="<%= cpropStatus%>">

                <table class="tab">
                    <tr>
                        <td class="txt">
                            *Property Id: <%= " " + aProp.getId() %>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Name:
                        </td>
                        <td>
                            <input type="text" size="30" name="propName" value="<%= aProp.getName()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Description:
                        </td>
                        <td>
                            <input type="text" size="30" name="propDesc" value="<%= aProp.getDescription()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Sales Type:
                        </td>

                        <%
                            if (aProp.getSales_type().equals("Buy")) {
                        %>

                        <td>
                            <input type="text" size="30" name="propType" value="Buy" readonly/>
                        </td>

                        <%
                        } else if (aProp.getSales_type().equals("Rent")) {
                        %>

                        <td>
                            <input type="text" size="30" name="propType" value="Rent" readonly/>
                        </td>
                        <td>
                            <input type="text" size="30" name="propDur" value="<%= aProp.getPriceDayMonthYear().equals("Day") ? "Day" : aProp.getPriceDayMonthYear().equals("Month") ? "Month" : "Year"%>" readonly/>
                        </td>

                        <%
                            }
                        %>

                    </tr>
                    <tr>
                        <td class="txt">
                            Base Price:
                        </td>
                        <td>
                            <input type="text" size="30" name="propPrice" value="<%= aProp.getPrice()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Discount:
                        </td>
                        <td>
                            <input type="text" name="propDis" value="<%= aProp.getDiscount()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total:
                        </td>
                        <td>
                            <input type="text" name="propTol" value="<%= total%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Status:
                        </td>
                        <td>
                            <input type="text" name="propStatus" value="<%= status%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Owner:
                        </td>
                        <td>
                            <input type="text" size="30" name="propName" value="<%= aProp.getOwner_username()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <%
                            if (aProp.getSales_type().equals("Rent") && status.equals("Available") && balance >= total) {
                        %>

                        <td class="txt">
                            I Would Like To Rent For:
                        </td>
                        <td>
                            <select name="ndays">
                                <% for (int i = 1; i <= possibleD; i++) {%>
                                <option value="<%= i%>"><%= i + "" + (aProp.getPriceDayMonthYear().equals("Day") ? "Day" : aProp.getPriceDayMonthYear().equals("Month") ? "Month" : "Year")%></option>
                                <% }%>
                            </select>
                        </td>

                        <%
                            }
                        %>
                    </tr>
                </table>     

                <%
                    if (status.equals("Available") && balance >= total) {
                %>
                <input type="submit" value="ACQUIRE PROPERTY" <%=  (balance < total ? "disabled" : " ")%> class="btn"/>
                <%
                    }
                %>
                <input type="submit" value="VIEW REVIEW & RATING" formaction="reviewRatingProperty" class="btn"/>
            </form>
        </div>
        <br/>

        <%
            }
        %>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
