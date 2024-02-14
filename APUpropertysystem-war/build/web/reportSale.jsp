<%@page import="model.systemProperty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SALES REPORT</title>
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
            System.out.println("name::" + name);
            String approve = (String) hts.getAttribute("approve");
            String reject = (String) hts.getAttribute("reject");
            String total = (String) hts.getAttribute("total");
            String totals = (String) hts.getAttribute("totals");

            systemProperty top = null;
            String topPrice = null;

            String prop = (String) hts.getAttribute("prop");

            if (prop.equals("found")) {
                top = (systemProperty) hts.getAttribute("top");
                topPrice = (String) hts.getAttribute("topPrice");
            }

        %>
        <div class="mar">
            <h1 style="text-align: center">SALES REPORT</h1>
        </div>
        <br/>
        <div class="mar">
            <form action="" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <table class="tab">
                    <tr class="seperator">
                        <td class="txt">
                            Sales Type
                        </td>
                        <td class="txt">
                            Count(Per Sale)
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Approved
                        </td>
                        <td>
                            <input type="text" size="30" name="approve" value="<%=approve%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Rejected
                        </td>
                        <td>
                            <input type="text" size="30" name="reject" value="<%=reject%>" readonly/>
                        </td>
                    </tr>

                </table>

                <br/>
                <% if (prop.equals("found")) {%>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Total Amount on Approved Sales
                        </td>
                        <td>
                            <input type="text" size="30" name="totals" value="<%=totals%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Top Sale
                        </td>
                        <td>
                            <input type="text" size="30" name="topPrice" value="<%=topPrice%>" readonly/>
                        </td>
                    </tr>
                </table>

                <br/>


                <table class="tab">
                    <tr>
                        <td class="txt">
                            Top Transaction
                        </td>
                        <td>
                            <input type="text" size="30" name="tId" value="<%= top.getId()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transaction Property
                        </td>
                        <td>
                            <input type="text" size="30" name="tname" value="<%= top.getName()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transaction Property Owner
                        </td>
                        <td>
                            <input type="text" size="30" name="toname" value="<%= top.getOwner_username()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transaction Property Description
                        </td>
                        <td>
                            <input type="text" size="30" name="tDesc" value="<%= top.getDescription()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transaction Property Owner
                        </td>
                        <td>
                            <input type="text" size="30" name="ttype" value="<%= top.getSales_type()%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transaction Date
                        </td>
                        <td>
                            <input type="text" size="30" name="tDOC" value="<%= top.getDOC()%>" readonly/>
                        </td>
                    </tr>
                </table>

                <% }%>

                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
            </form>
        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
