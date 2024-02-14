<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCH PROPERTY</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <script>
            function handleCBChange() {
                var selectedValue = document.getElementById("cb").value;
                var hiddenComboBox = document.getElementById("hcb");

                if (selectedValue === "Rent") {
                    hiddenComboBox.style.display = "block";
                } else {
                    hiddenComboBox.style.display = "none";
                }
            }
            function handleTBChange() {
                var selectedValue = document.getElementById("pcb").value;
                var hiddenTextBox = document.getElementById("ptb");
                var hiddenLabel = document.getElementById("pl");

                if (selectedValue !== "All") {
                    hiddenTextBox.style.display = "block"; 
                    hiddenLabel.style.display = "block"; 
                } else {
                    hiddenTextBox.style.display = "none";
                    hiddenLabel.style.display = "none";  
                }
            }
        </script>
        <%
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            System.out.println("search:name::" + name);
            System.out.println("role::" + role);
            String returnPath = role.equals("Admin") ? "adminHome.jsp" : role.equals("Owner") ? "ownerHome.jsp" : "customerHome.jsp";
        %>
        <div class="mar">
            <form action="propertyViewLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Property Name:
                        </td>
                        <td>
                            <input type="text" size="30" name="propName"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Leave Empty If No Specific Criteria Is Required 
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Sales Type:
                        </td>
                        <td>
                            <select id="cb" name="propType" onchange="handleCBChange()">
                                <option value="All" selected>All</option>
                                <option value="Buy">Buy</option>
                                <option value="Rent">Rent</option>
                            </select>
                        </td>
                        <td>
                            <select id="hcb" name="propDur" style="display:none;">
                                <option value="All" selected>All</option>
                                <option value="Day">Per Day</option>
                                <option value="Month">Per Month</option>
                                <option value="Year">Per Year</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Price Range:
                        </td>
                        <td>
                            <select id="pcb" name="priceRange" onchange="handleTBChange()">
                                <option value="All" selected>All</option>
                                <option value="Lower">Lower Than</option>
                                <option value="Higher">Higher Than</option>
                                <option value="Equal">Equal To</option>
                            </select>
                        </td>
                        <td>
                            <label id="pl" style="display:none;"  class="txt">Property Price:</label>
                        </td>
                        <td>
                            <input id="ptb" type="text" size="30" name="propPrice" style="display:none;"/>
                        </td>
                    </tr>
                    <tr>
                        <td  class="txt">
                            Status:
                        </td>
                        <td>
                            <select name="propStatus">
                                <option value="All" selected>All</option>
                                <option value="Available">Available</option>
                                <option value="NAvailable">Not Available</option>
                                <option value="Rented/Sold">Rented/Sold</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
                <input type="submit" value="SEARCH PROPERTY" class="btn" />
            </form>
        </div>
    </body>
</html>
