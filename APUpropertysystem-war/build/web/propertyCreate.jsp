<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE PROPERTY</title>
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
        </script>
        <%
            String name = request.getParameter("name");
            System.out.println("name::" + name);
            String role = "Owner";
        %>
        <div class="mar">
            <form action="propertyCreateLogic" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>"/>
                <input type="hidden" name="returnPath" value="ownerHome.jsp"/>

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
                        <td class="txt">
                            Property Description:
                        </td>
                        <td>
                            <input type="text" size="30" name="propDesc"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Sales Type:
                        </td>
                        <td>
                            <select id="cb" name="propType" onchange="handleCBChange()">
                                <option value="Buy">Buy</option>
                                <option value="Rent">Rent</option>
                            </select>
                        </td>
                        <td>
                            <select id="hcb" name="propDur" style="display:none;">
                                <option value="Day">Per Day</option>
                                <option value="Month">Per Month</option>
                                <option value="Year">Per Year</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Property Price:
                        </td>
                        <td>
                            <input type="text" size="30" name="propPrice" value="0"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Discount (optional):
                        </td>
                        <td>
                            <input type="text" name="propDis" value="0"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        </td>
                        <td class="txtItalic">
                            *Leave Empty/As 0 If You Don't Intend To Provide Any Discount 
                        </td>
                    </tr>
                </table>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
                <input type="submit" value="CREATE PROPERTY" class="btn"/>
            </form>
        </div>
    </body>
</html>
