<%@page import="model.systemCustomer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOMEPAGE</title>
    </head>
    <link rel="stylesheet" href="webDesign.css" type="text/css"/>
    <body>

        <%
            HttpSession hts = request.getSession(false);
            systemCustomer sysC = (systemCustomer) hts.getAttribute("account");
            String name = sysC.getId();
            String profileLogicPath = "profileLogic?name=" + name + "&amp;role=Customer";
            String propertySearchLogicPath = "propertySearch.jsp?name=" + name + "&amp;role=Customer";
            String reviewRatingViewPath = "reviewRatingViewLogic?name=" + name + "&amp;role=Customer";
            String accountViewPath = "accountViewLogic?name=" + name + "&amp;role=Customer";
            String transactionViewPath = "transactionViewLogic?name=" + name + "&amp;role=Customer";
            System.out.println("name::" + name);
            System.out.println("role::Customer");
        %>
        <div class="nav mar">
            <ul>
                <li>
                    <div>
                        <form action="login.jsp" type="POST">
                            <input type="submit" value="LOGOUT" class="btnnavnolist"/>
                        </form>
                    </div>
                </li>
                <li>
                    <div>
                        <form action="propertySearch.jsp" type="POST">
                            <input type="hidden" name="name" value="<%=name%>">
                            <input type="hidden" name="role" value="Customer">
                            <input type="submit" value="VIEW PROPERTY" class="btnnavnolist"/>
                        </form>
                    </div>
                </li> 
                <li>
                    <div>
                        <form action="accountViewLogic" type="POST">
                            <input type="hidden" name="name" value="<%=name%>">
                            <input type="hidden" name="role" value="Customer">
                            <input type="submit" value="ACCOUNT" class="btnnavnolist"/>
                        </form>
                    </div>
                </li> 
                <li> TRANSACTION
                    <ul>
                        <li>
                            <div>
                                <form action="reviewRatingViewLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Customer">
                                    <input type="submit" value="REVIEW & RATING" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="transactionViewLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Customer">
                                    <input type="submit" value="TRANSACTION RECORD" class="btnnav"/>
                                </form>
                            </div>
                        </li>

                    </ul>
                </li>
                <li>
                    <div>
                        <form action="profileLogic" type="POST">
                            <input type="hidden" name="name" value="<%=name%>">
                            <input type="hidden" name="role" value="Customer">
                            <input type="submit" value="PROFILE" class="btnnavnolist"/>
                        </form>
                    </div>
                </li>  
            </ul>
        </div>
        <br/><br/><br/><br/><br/><br/>
        <div class="bgclr shadow" style="background-color: rgba(36, 178, 165, 0.8)">
            <br/>
            <div class="rowdisplay">

                <div class="card shadow" style="text-align:center;background-color: rgba(39, 90, 85, 0.8)">
                    <br/><br/><br/><br/><br/><br/>
                    <b style="color:white; font-style:italic; font-size: 30px">
                        Search For All Available Properties To Buy or Rent:
                    </b><br/>
                </div>
                <div class="card centre">
                    <form action="propertySearch.jsp" type="POST">
                        <input type="hidden" name="name" value="<%=name%>">
                        <input type="hidden" name="role" value="Customer">
                        <input type="submit" value="GO" class="btn"/>
                    </form>
                </div>

                <br/><br/>

            </div>
            <br/>
        </div>
        <br/><br/>
        <div class="bgclr shadow" style="background-color: rgba(239, 255, 78, 0.8)">
            <br/>
            <div class="rowdisplay">
                <div class="card shadow" style="text-align:center;background-color: rgba(107, 116, 16, 0.8)">
                    <br/><br/><br/><br/><br/><br/>
                    <b style="color:white; font-style:italic; font-size: 30px">
                        Rate And Review Your Acquisitions:
                    </b><br/>
                </div>
                <div class="card centre">
                    <form action="reviewRatingViewLogic" type="POST">
                        <input type="hidden" name="name" value="<%=name%>">
                        <input type="hidden" name="role" value="Customer">
                        <input type="submit" value="GO" class="btn"/>
                    </form>
                </div>

                <br/><br/>

            </div>
            <br/>
        </div>

    </body>
</html>
