<%@page import="model.systemAdmin"%>
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
            systemAdmin sysA = (systemAdmin) hts.getAttribute("account");
            String name = sysA.getId();
            String profileLogicPath = "profileLogic?name=" + name + "&amp;role=Admin";
            String profileSearchLogicPath = "profileSearch.jsp?name=" + name + "&amp;role=Admin";

            String propertySearchLogicPath = "propertySearch.jsp?name=" + name + "&amp;role=Admin";
            String AdminRegistrationPath = "registerForAdmin.jsp?name=" + name;
            String ownerApprovalPath = "ownerAccountLogic?name=" + name + "&amp;role=Admin";

            String rrReportLogicPath = "reportLogic?name=" + name + "&amp;type=r";
            String rrOwnerCustomerReportLogicPath = "reportLogic?name=" + name + "&amp;type=o";
            String genderReportLogicPath = "reportLogic?name=" + name + "&amp;type=g";
            String ageReportLogicPath = "reportLogic?name=" + name + "&amp;type=a";
            String userReportLogicPath = "reportLogic?name=" + name + "&amp;type=u";
            String saleReportLogicPath = "reportLogic?name=" + name + "&amp;type=s";
            String propertyReportLogicPath = "reportLogic?name=" + name + "&amp;type=p";
            System.out.println("name::" + name);
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
                            <input type="hidden" name="role" value="Admin">
                            <input type="submit" value="VIEW PROPERTY" class="btnnavnolist"/>
                        </form>
                    </div>
                </li> 
                <li> ACCOUNT
                    <ul>
                        <li>
                            <div>
                                <form action="registerForAdmin.jsp" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="submit" value="REGISTER NEW USER" class="btnnav"/>
                                </form>
                            </div>
                        </li>  
                        <li>
                            <div>
                                <form action="ownerAccountLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="submit" value="OWNER ACCOUNT APPROVAL" class="btnnav"/>
                                </form>
                            </div>
                        </li>  
                    </ul>
                </li> 
                <li> REPORT
                    <ul>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="r">
                                    <input type="submit" value="PROPERTY RATING & REVIEW ANALYSIS REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="o">
                                    <input type="submit" value="USER RATING & REVIEW ANALYSIS REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="g">
                                    <input type="submit" value="GENDER REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="a">
                                    <input type="submit" value="AGE REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="u">
                                    <input type="submit" value="USER REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="s">
                                    <input type="submit" value="SALE REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                        <li>
                            <div>
                                <form action="reportLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="hidden" name="type" value="p">
                                    <input type="submit" value="PROPERTY REPORT" class="btnnav"/>
                                </form>
                            </div>
                        </li>
                    </ul>
                </li> 
                <li> PROFILE
                    <ul>
                        <li>
                            <div>
                                <form action="profileSearch.jsp" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="submit" value="SEARCH PROFILE" class="btnnav"/>
                                </form>
                            </div>
                        </li>  
                        <li>
                            <div>
                                <form action="profileLogic" type="POST">
                                    <input type="hidden" name="name" value="<%=name%>">
                                    <input type="hidden" name="role" value="Admin">
                                    <input type="submit" value="PROFILE" class="btnnav"/>
                                </form>
                            </div>
                        </li>  
                    </ul>
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
                        Approve/Reject Owner Account Creations:
                    </b><br/>
                </div>
                <div class="card centre">
                    <form action="ownerAccountLogic" type="POST">
                        <input type="hidden" name="name" value="<%=name%>">
                        <input type="hidden" name="role" value="Admin">
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
                        View All Owner Property:
                    </b><br/>
                </div>
                <div class="card centre">
                    <form action="propertySearch.jsp" type="POST">
                        <input type="hidden" name="name" value="<%=name%>">
                        <input type="hidden" name="role" value="Admin">
                        <input type="submit" value="GO" class="btn"/>
                    </form>
                </div>

                <br/><br/>
            </div>
            <br/>
        </div>
        <br/><br/>
        <div class="bgclr shadow" style="background-color: rgba(239, 57, 13, 0.8)">
            <br/>
            <div class="rowdisplay">
                <div class="card shadow" style="text-align:center;background-color: rgba(112, 29, 10, 0.8)">
                    <br/><br/><br/><br/><br/><br/>
                    <b style="color:white; font-style:italic; font-size: 30px">
                        View All Profile:
                    </b><br/>
                </div>
                <div class="card centre">
                    <form action="profileSearch.jsp" type="POST">
                        <input type="hidden" name="name" value="<%=name%>">
                        <input type="hidden" name="role" value="Admin">
                        <input type="submit" value="GO" class="btn"/>
                    </form>
                </div>

                <br/><br/>
            </div>
            <br/>
        </div>

    </body>
</html>
