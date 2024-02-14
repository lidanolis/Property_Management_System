<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USER REPORT</title>
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
            String cuser = (String) hts.getAttribute("cuser");
            String ouser = (String) hts.getAttribute("ouser");
            String auser = (String) hts.getAttribute("auser");
            String total = (String) hts.getAttribute("total");

            String cuserm = (String) hts.getAttribute("cuserm");
            String ouserm = (String) hts.getAttribute("ouserm");
            String auserm = (String) hts.getAttribute("auserm");

            String cusery = (String) hts.getAttribute("cusery");
            String ousery = (String) hts.getAttribute("ousery");
            String ausery = (String) hts.getAttribute("ausery");
        %>
        <div class="mar">
            <h1 style="text-align: center">USER REPORT</h1>
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
                            User
                        </td>
                        <td class="txt">
                            Count(Per Person)
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Customer
                        </td>
                        <td>
                            <input type="text" size="30" name="cuser" value="<%=cuser%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Owner
                        </td>
                        <td>
                            <input type="text" size="30" name="cuser" value="<%=ouser%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Admin
                        </td>
                        <td>
                            <input type="text" size="30" name="cuser" value="<%=auser%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total
                        </td>
                        <td>
                            <input type="text" size="30" name="total" value="<%=total%>" readonly/>
                        </td>
                    </tr>
                </table>
                <br/>

                <table class="tab">
                    <tr class="seperator">
                        <td class="txt">
                            New User (This Month)
                        </td>
                        <td class="txt">
                            Count(Per Person)
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Customer
                        </td>
                        <td>
                            <input type="text" size="30" name="cuserm" value="<%=cuserm%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Owner
                        </td>
                        <td>
                            <input type="text" size="30" name="ouserm" value="<%=ouserm%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Admin
                        </td>
                        <td>
                            <input type="text" size="30" name="auserm" value="<%=auserm%>" readonly/>
                        </td>
                    </tr>
                </table>
                <br/>

                <table class="tab ">
                    <tr class="seperator">
                        <td class="txt">
                            New User (This Year)
                        </td>
                        <td class="txt">
                            Count(Per Person)
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Customer
                        </td>
                        <td>
                            <input type="text" size="30" name="cuserm" value="<%=cusery%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Owner
                        </td>
                        <td>
                            <input type="text" size="30" name="ouserm" value="<%=ousery%>" readonly/>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Admin
                        </td>
                        <td>
                            <input type="text" size="30" name="auserm" value="<%=ausery%>" readonly/>
                        </td>
                    </tr>
                </table>
                <br/>

                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
            </form>
        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
