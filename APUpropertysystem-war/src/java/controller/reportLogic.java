/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class reportLogic extends HttpServlet {

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemAdminFacade systemAdminFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String role = "Admin";
        System.out.println("name::" + name);
        System.out.println("type::" + type);
        try (PrintWriter out = response.getWriter()) {

            List<systemCustomer> sysC = new ArrayList<>();
            List<systemOwner> sysO = new ArrayList<>();
            List<systemAdmin> sysA = new ArrayList<>();

            sysC = systemCustomerFacade.findAll();
            sysO = systemOwnerFacade.findAll();
            sysA = systemAdminFacade.findAll();

            try {

                if (type.equals("g")) {
                    int cmale = 0;
                    int cfemale = 0;

                    int omale = 0;
                    int ofemale = 0;

                    int amale = 0;
                    int afemale = 0;

                    int totalmale = 0;
                    int totalfemale = 0;

                    if (!(sysC.isEmpty())) {
                        for (systemCustomer c : sysC) {
                            if (c.getGender() == 'M') {
                                cmale++;
                                totalmale++;
                            } else {
                                cfemale++;
                                totalfemale++;
                            }
                        }
                    }
                    if (!(sysO.isEmpty())) {
                        for (systemOwner o : sysO) {
                            if (o.getGender() == 'M') {
                                omale++;
                                totalmale++;
                            } else {
                                ofemale++;
                                totalfemale++;
                            }
                        }
                    }
                    if (!(sysA.isEmpty())) {
                        for (systemAdmin a : sysA) {
                            if (a.getGender() == 'M') {
                                amale++;
                                totalmale++;
                            } else {
                                afemale++;
                                totalfemale++;
                            }
                        }
                    }

                    if (sysC.isEmpty() && sysO.isEmpty() && sysA.isEmpty()) {
                        throw new Exception();
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("cmale", String.valueOf(cmale));
                    hts.setAttribute("cfemale", String.valueOf(cfemale));
                    hts.setAttribute("omale", String.valueOf(omale));
                    hts.setAttribute("ofemale", String.valueOf(ofemale));
                    hts.setAttribute("amale", String.valueOf(amale));
                    hts.setAttribute("afemale", String.valueOf(afemale));
                    hts.setAttribute("tmale", String.valueOf(totalmale));
                    hts.setAttribute("tfemale", String.valueOf(totalfemale));

                    try {
                        request.getRequestDispatcher("reportGender.jsp").forward(request, response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else if (type.equals("a")) {
                    List<Integer> cage = new ArrayList<>();
                    List<Integer> oage = new ArrayList<>();
                    List<Integer> aage = new ArrayList<>();

                    List<Integer> cagec = new ArrayList<>();
                    List<Integer> oagec = new ArrayList<>();
                    List<Integer> aagec = new ArrayList<>();

                    for (systemCustomer c : sysC) {
                        if (!(cage.contains(c.getAge()))) {
                            cage.add(c.getAge());
                        }
                    }
                    for (systemOwner o : sysO) {
                        if (!(oage.contains(o.getAge()))) {
                            oage.add(o.getAge());
                        }
                    }
                    for (systemAdmin a : sysA) {
                        if (!(aage.contains(a.getAge()))) {
                            aage.add(a.getAge());
                        }
                    }

                    for (int cc : cage) {
                        int i = 0;
                        for (systemCustomer c : sysC) {
                            if (c.getAge() == cc) {
                                i++;
                            }
                        }
                        cagec.add(i);
                    }
                    for (int cc : oage) {
                        int i = 0;
                        for (systemOwner o : sysO) {
                            if (o.getAge() == cc) {
                                i++;
                            }
                        }
                        oagec.add(i);
                    }
                    for (int cc : aage) {
                        int i = 0;
                        for (systemAdmin a : sysA) {
                            if (a.getAge() == cc) {
                                i++;
                            }

                        }
                        aagec.add(i);
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("cage", cage);
                    hts.setAttribute("oage", oage);
                    hts.setAttribute("aage", aage);

                    hts.setAttribute("cagec", cagec);
                    hts.setAttribute("oagec", oagec);
                    hts.setAttribute("aagec", aagec);

                    request.getRequestDispatcher("reportAge.jsp").forward(request, response);

                } else if (type.equals("u")) {

                    int cuser = 0;
                    int ouser = 0;
                    int auser = 0;

                    int cuserm = 0;
                    int ouserm = 0;
                    int auserm = 0;

                    int cusery = 0;
                    int ousery = 0;
                    int ausery = 0;

                    int total = 0;

                    for (systemCustomer c : sysC) {

                        LocalDate currentDate = LocalDate.now();
                        Month currentMonth = currentDate.getMonth();
                        Month userMonth = c.getDOC().getMonth();
                        int currentYear = currentDate.getYear();
                        int userYear = c.getDOC().getYear();

                        if (currentMonth == userMonth) {
                            cuserm++;
                        }
                        if (currentYear == userYear) {
                            cusery++;
                        }

                        cuser++;
                        total++;

                    }
                    for (systemOwner o : sysO) {

                        LocalDate currentDate = LocalDate.now();
                        Month currentMonth = currentDate.getMonth();
                        Month userMonth = o.getDOC().getMonth();
                        int currentYear = currentDate.getYear();
                        int userYear = o.getDOC().getYear();

                        if (currentMonth == userMonth) {
                            ouserm++;
                        }
                        if (currentYear == userYear) {
                            ousery++;
                        }

                        ouser++;
                        total++;
                    }
                    for (systemAdmin a : sysA) {

                        LocalDate currentDate = LocalDate.now();
                        Month currentMonth = currentDate.getMonth();
                        Month userMonth = a.getDOC().getMonth();
                        int currentYear = currentDate.getYear();
                        int userYear = a.getDOC().getYear();

                        if (currentMonth == userMonth) {
                            auserm++;
                        }
                        if (currentYear == userYear) {
                            ausery++;
                        }

                        auser++;
                        total++;
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("cuser", String.valueOf(cuser));
                    hts.setAttribute("ouser", String.valueOf(ouser));
                    hts.setAttribute("auser", String.valueOf(auser));
                    hts.setAttribute("total", String.valueOf(total));

                    hts.setAttribute("cuserm", String.valueOf(cuserm));
                    hts.setAttribute("ouserm", String.valueOf(ouserm));
                    hts.setAttribute("auserm", String.valueOf(auserm));

                    hts.setAttribute("cusery", String.valueOf(cusery));
                    hts.setAttribute("ousery", String.valueOf(ousery));
                    hts.setAttribute("ausery", String.valueOf(ausery));

                    request.getRequestDispatcher("reportUser.jsp").forward(request, response);

                } else if (type.equals("s")) {

                    int approve = 0;
                    int reject = 0;
                    int total = 0;
                    float totals = 0;
                    List<systemTransaction> allTran = new ArrayList<>();
                    allTran = systemTransactionFacade.findAll();

                    if (allTran.isEmpty()) {
                        throw new Exception();
                    }
                    float topPrice = 0;
                    systemProperty top = systemPropertyFacade.find(allTran.get(0).getId());

                    for (systemTransaction aTran : allTran) {

                        total++;
                        if (aTran.getApproveStatus().equals("Approved")) {
                            approve++;
                            totals = totals + aTran.getTotal();
                            if (aTran.getTotal() > topPrice) {
                                top = systemPropertyFacade.find(aTran.getPropertyId());
                                topPrice = top.getPrice() - top.getDiscount();
                            }

                        } else if (aTran.getApproveStatus().equals("Rejected")) {
                            reject++;
                        }
                    }

                    System.out.println(top.getId());

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("approve", String.valueOf(approve));
                    hts.setAttribute("reject", String.valueOf(reject));
                    hts.setAttribute("total", String.valueOf(total));
                    hts.setAttribute("totals", String.valueOf(totals));

                    hts.setAttribute("topPrice", String.valueOf(topPrice));

                    hts.setAttribute("prop", approve != 0 ? "found" : "none");
                    hts.setAttribute("top", top);

                    request.getRequestDispatcher("reportSale.jsp").forward(request, response);

                } else if (type.equals("p")) {
                    List<systemProperty> allP = new ArrayList<>();
                    allP = systemPropertyFacade.findAll();

                    if (allP == null) {
                        throw new Exception();
                    }
                    if (allP.isEmpty()) {
                        throw new Exception();
                    }

                    int total = 0;
                    int rent = 0;
                    int buy = 0;

                    List<Integer> tranc = new ArrayList<>();

                    for (systemProperty aProp : allP) {
                        total++;
                        if (aProp.getSales_type().equals("Rent")) {
                            rent++;
                        } else if (aProp.getSales_type().equals("Buy")) {
                            buy++;
                        }

                        tranc.add(aProp.getpTransaction().size());
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("total", String.valueOf(total));
                    hts.setAttribute("rent", String.valueOf(rent));
                    hts.setAttribute("buy", String.valueOf(buy));

                    hts.setAttribute("allP", allP);
                    hts.setAttribute("tranc", tranc);

                    request.getRequestDispatcher("reportProperty.jsp").forward(request, response);
                } else if (type.equals("r")) {
                    List<systemProperty> allP = new ArrayList<>();
                    List<systemTransaction> allT = new ArrayList<>();
                    allP = systemPropertyFacade.findAll();
                    allT = systemTransactionFacade.findAll();

                    if (allP.isEmpty() || allT.isEmpty()) {
                        throw new Exception();
                    }

                    List<Integer> tranc = new ArrayList<>();

                    for (systemProperty aProp : allP) {
                        int rating = 0;
                        int count = 0;
                        for (systemTransaction aTran : allT) {
                            if (aTran.getPropertyId() == aProp.getId() && aTran.getCustomerRating() != 0) {
                                rating = rating + aTran.getCustomerRating();
                                count++;
                            }
                        }
                        if (rating != 0) {
                            rating = rating / count;
                        }
                        tranc.add(rating);
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);
                    hts.setAttribute("allP", allP);
                    hts.setAttribute("tranc", tranc);
                    hts.setAttribute("allT", allT);

                    request.getRequestDispatcher("reportrrProperty.jsp").forward(request, response);
                } else if (type.equals("o")) {
                    List<systemOwner> allO = new ArrayList<>();
                    List<systemCustomer> allC = new ArrayList<>();
                    List<systemTransaction> allT = new ArrayList<>();

                    allT = systemTransactionFacade.findAll();
                    allO = systemOwnerFacade.findAll();
                    allC = systemCustomerFacade.findAll();

                    if (allT.isEmpty()) {
                        throw new Exception();
                    }

                    List<Integer> tranc = new ArrayList<>();
                    List<Integer> trano = new ArrayList<>();
                    List<systemCustomer> fallC = new ArrayList<>();
                    List<systemOwner> fallO = new ArrayList<>();

                    if (!(allO.isEmpty())) {
                        for (systemOwner aO : allO) {
                            int rating = 0;
                            int count = 0;
                            for (systemTransaction aTran : allT) {
                                if (aTran.getOwnerId().equals(aO.getId()) && aTran.getCustomerRating() != 0) {
                                    rating = rating + aTran.getCustomerRating();
                                    count++;
                                }
                            }
                            if (rating != 0) {
                                rating = rating / count;
                            }
                            trano.add(rating);
                            fallO.add(aO);
                        }
                    }

                    if (!(allC.isEmpty())) {
                        for (systemCustomer aC : allC) {
                            int rating = 0;
                            int count = 0;
                            for (systemTransaction aTran : allT) {
                                if (aTran.getCustomerId().equals(aC.getId()) && aTran.getOwnerRating() != 0) {
                                    rating = rating + aTran.getOwnerRating();
                                    count++;
                                }
                            }
                            if (rating != 0) {
                                rating = rating / count;
                            }
                            tranc.add(rating);
                            fallC.add(aC);
                        }
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);
                    hts.setAttribute("trano", trano);
                    hts.setAttribute("tranc", tranc);
                    hts.setAttribute("fallC", fallC);
                    hts.setAttribute("fallO", fallO);
                    hts.setAttribute("allT", allT);

                    request.getRequestDispatcher("reportrrUser.jsp").forward(request, response);
                }

            } catch (Exception EX) {
                EX.printStackTrace();
                systemAdmin account = systemAdminFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", account);
                request.getRequestDispatcher("adminHome.jsp").include(request, response);
                out.println("<br/><br/><h6>no report generated</h6>");
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
