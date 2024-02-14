<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GENDER REPORT</title>
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
            String cmale = (String) hts.getAttribute("cmale");
            String cfemale = (String) hts.getAttribute("cfemale");

            String omale = (String) hts.getAttribute("omale");
            String ofemale = (String) hts.getAttribute("ofemale");

            String amale = (String) hts.getAttribute("amale");
            String afemale = (String) hts.getAttribute("afemale");

            String tmale = (String) hts.getAttribute("tmale");
            String tfemale = (String) hts.getAttribute("tfemale");
        %>
        <div class="mar">
            <h1 style="text-align: center">GENDER REPORT</h1>
        </div>
        <br/>
        <div class="mar">
            <form action="" type="POST">
                <input type="hidden" name="name" value="<%=name%>">
                <input type="hidden" name="role" value="<%=role%>">
                <input type="hidden" name="returnPath" value="<%=returnPath%>"/>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Total Male Customer(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="cmale" value="<%=cmale%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Female Customer(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="cfemale" value="<%=cfemale%>" readonly/>
                        </td>
                    </tr>

                    <tr class="seperator">
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Total Male Owner(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="omale" value="<%=omale%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Female Owner(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="ofemale" value="<%=ofemale%>" readonly/>
                        </td>
                    </tr>

                    <tr class="seperator">
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Total Male Admin(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="amale" value="<%=amale%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Female Admin(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="afemale" value="<%=afemale%>" readonly/>
                        </td>
                    </tr>

                    <tr class="seperator">
                        <td>
                        </td>
                        <td>
                        </td>
                    </tr>

                    <tr>
                        <td class="txt">
                            Total Male APU Property System User(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="tmale" value="<%=tmale%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Total Female APU Property System User(s):
                        </td>
                        <td>
                            <input type="text" size="30" name="tfemale" value="<%=tfemale%>" readonly/>
                        </td>
                    </tr>
                </table>
                <br/>
                <input type="submit" value="BACK" formaction="returnLogic"  class="btn"/>
            </form>
        </div>
        <br/><br/><br/><br/>
        <div style="position:relative;"><img class="shadow topbutton" src="top.png" alt="" style="position:absolute;z-index:3;bottom:10px; right:20px" onclick="totop()"/></div>

    </body>
</html>
