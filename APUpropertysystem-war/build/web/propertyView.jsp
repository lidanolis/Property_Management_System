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
            function handleCBChange(comboBoxId) {
                var selectedValue = document.getElementById("combobox" + comboBoxId).value;
                var hiddenComboBox = document.getElementById("hcombobox" + comboBoxId);

                if (selectedValue === "Rent") {
                    hiddenComboBox.style.display = "block";
                } else {
                    hiddenComboBox.style.display = "none";
                }
            }
        </script>

        <%
            HttpSession hts = request.getSession(false);
            List<systemProperty> allProp = (List<systemProperty>) hts.getAttribute("properties");
            String name = (String) hts.getAttribute("name");
            String role = (String) hts.getAttribute("role");

            String cpropName = (String) hts.getAttribute("propName");
            String cpropType = (String) hts.getAttribute("propType");
            String cpropDur = (String) hts.getAttribute("propDur");
            String cpriceRange = (String) hts.getAttribute("priceRange");
            String cpropPrice = (String) hts.getAttribute("propPrice");
            String cpropStatus = (String) hts.getAttribute("propStatus");
            String returnPath = "propertySearch.jsp";
            System.out.println("name::" + name);
        %>
        <br/>
        <div class="mar">
            <form action="propertySearch.jsp" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="submit" value="BACK" class="btn"/>
            </form>
        </div>

        <br>
        <%
            int i = 0;
            for (systemProperty aProp : allProp) {
        %>

        <div class="mar">
            <form action="updatePropertyLogic" type="POST">
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
                            *Property Id: <%= " " + aProp.getId()%>
                        </td>
                        <td>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Name:
                        </td>
                        <td>
                            <input type="text" size="30" name="propName" value="<%= aProp.getName()%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Description:
                        </td>
                        <td>
                            <input type="text" size="30" name="propDesc" value="<%= aProp.getDescription()%>"/>
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
                            <select id="<%= "combobox" + i%>" name="propType" onchange="handleCBChange(<%= i%>)">
                                <option value="Buy" selected>Buy</option>
                                <option value="Rent">Rent</option>
                            </select>
                        </td>
                        <td>
                            <select id="<%= "hcombobox" + i%>" name="propDur" style="display:none;">
                                <option value="Day">Per Day</option>
                                <option value="Month">Per Month</option>
                                <option value="Year">Per Year</option>
                            </select>
                        </td>

                        <%
                        } else if (aProp.getSales_type().equals("Rent")) {
                        %>

                        <td>
                            <select id="<%= "combobox" + i%>" name="propType" onchange="handleCBChange(<%= i%>)">
                                <option value="Buy">Buy</option>
                                <option value="Rent" selected>Rent</option>
                            </select>
                        </td>
                        <td>
                            <select id="<%= "hcombobox" + i%>" name="propDur" style="display:block;">
                                <option value="Day" <%= aProp.getPriceDayMonthYear().equals("Day") ? "selected" : null%>>Per Day</option>
                                <option value="Month" <%= aProp.getPriceDayMonthYear().equals("Month") ? "selected" : null%>>Per Month</option>
                                <option value="Year" <%= aProp.getPriceDayMonthYear().equals("Year") ? "selected" : null%>>Per Year</option>
                            </select>
                        </td>

                        <%
                            }
                        %>

                    </tr>
                    <tr>
                        <td  class="txt">
                            Property Price:
                        </td>
                        <td>
                            <input type="text" size="30" name="propPrice" value="<%= aProp.getPrice()%>" />
                        </td>
                    </tr>
                    <tr>
                        <td  class="txt">
                            Discount:
                        </td>
                        <td>
                            <input type="text" name="propDis" value="<%= aProp.getDiscount()%>" />
                        </td>
                    </tr>
                    <tr>
                        <td  class="txt">
                            Status:
                        </td>
                        <td>
                            <select name="propStatus">
                                <option value="Available" <%= aProp.getStatus().equals("Available") ? "selected" : null%>>Available</option>
                                <option value="NAvailable" <%= aProp.getStatus().equals("NAvailable") ? "selected" : null%>>Not Available</option>
                                <option value="Rented/Sold" <%= aProp.getStatus().equals("Rented/Sold") ? "selected" : null%>>Rented/Sold</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Property By <%= " " + aProp.getOwner_username()%> 
                        </td>
                    </tr>
                </table>
                <input type="submit" value="UPDATE PROPERTY"  class="btn"/>
                <input type="submit" value="REMOVE PROPERTY" formaction="deletePropertyLogic"  class="btn"/>
                <input type="submit" value="VIEW REVIEW & RATING" formaction="reviewRatingProperty" class="btn"/>
            </form>
        </div>
        <br/><br/>

        <%
                i++;
            }
        %>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
