<%@page import="model.systemCustomer"%>
<%@page import="model.systemOwner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ACCOUNT</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>
        <%
            HttpSession hts = request.getSession(false);
            String role = (String) hts.getAttribute("role");
            systemCustomer sysC = null;
            systemOwner sysO = null;
            String returnPath = null;

            if (role.equals("Customer")) {
                sysC = (systemCustomer) hts.getAttribute("account");
                returnPath = "customerHome.jsp";
            } else if (role.equals("Owner")) {
                sysO = (systemOwner) hts.getAttribute("account");
                returnPath = "ownerHome.jsp";
            }

            String name = sysC != null ? sysC.getId() : sysO.getId();
            Float balance = sysC != null ? sysC.getBalance() : sysO.getBalance();
            System.out.println("name::" + name);
        %>

        <div class="mar">
            <form action="accountTransactionLogic" type="POST">
                <input type="hidden" name="name" value="<%= name%>">
                <input type="hidden" name="role" value="<%= role%>">
                <input type="hidden" name="returnPath" value="<%= returnPath%>"/>
                <table class="tab">
                    <tr>
                        <td class="txt">
                            Account Balance:
                        </td>
                        <td>
                            <input type="text" size="30" name="balance" value="<%= balance%>" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Deposit/Withdraw Amount:
                        </td>
                        <td>
                            <input type="text" size="30" name="amount"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="txt">
                            Transact Type:
                        </td>
                        <td>
                            <select name="tranType">
                                <option value="Deposit" selected>DEPOSIT</option>
                                <option value="Withdraw">WITHDRAW</option>
                            </select>
                        </td>
                    </tr>
                </table>
                <input type="submit" value="BACK" formaction="returnLogic" class="btn"/>
                <input type="submit" value="TRANSACT" class="btn"/>
            </form>
        </div>

    </body>
</html>
