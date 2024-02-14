package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class reviewRatingViewLogicReturn extends HttpServlet {

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession htps = request.getSession(false);
            String name = (String) htps.getAttribute("name");
            String role = (String) htps.getAttribute("role");
            String errorInput = (String) htps.getAttribute("msg");
            System.out.println("name::" + name);

            try {

                List<systemTransaction> Filteredctr = new ArrayList<>();
                List<systemTransaction> ctr = systemTransactionFacade.findAll();

                if (ctr.isEmpty()) {
                    errorInput = "No Rating and Review has been given";
                    throw new Exception();
                }

                if (role.equals("Owner")) {
                    for (systemTransaction c : ctr) {
                        if (c.getOwnerId().equals(name)) {
                            Filteredctr.add(c);
                        }
                    }
                } else if (role.equals("Customer")) {
                    for (systemTransaction c : ctr) {
                        if (c.getCustomerId().equals(name)) {
                            Filteredctr.add(c);
                        }
                    }
                }

                if (Filteredctr.isEmpty()) {
                    errorInput = "No Rating and Review has been given";
                    throw new Exception();
                }

                HttpSession hts = request.getSession();
                hts.setAttribute("transactionList", Filteredctr);
                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                request.getRequestDispatcher("reviewRating.jsp").include(request, response);
                out.println("<br/><br/><h6>" + errorInput + "</h6>");

            } catch (Exception ex) {
                ex.printStackTrace();
                if (role.equals("Owner")) {
                    systemOwner sysO = systemOwnerFacade.find(name);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysO);
                    request.getRequestDispatcher("ownerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>" + errorInput + "</h6>");
                } else {
                    systemCustomer sysC = systemCustomerFacade.find(name);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysC);
                    request.getRequestDispatcher("customerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>" + errorInput + "</h6>");
                }

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
