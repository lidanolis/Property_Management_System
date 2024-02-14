/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemProperty;
import model.systemPropertyFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class propertyAcquireLogic extends HttpServlet {

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession htps = request.getSession(false);

        String name = (String) htps.getAttribute("name");
        String role = (String) htps.getAttribute("role");
                    System.out.println("name::" + name);

        systemCustomer sysC = systemCustomerFacade.find(name);
        Long propId = Long.valueOf(request.getParameter("propID"));
        systemProperty oProp = systemPropertyFacade.find(propId);

        try (PrintWriter out = response.getWriter()) {
            float total = 0;
            if (oProp.getSales_type().equals("Buy")) {
                total = oProp.getPrice() - oProp.getDiscount();
            }else{
                total = (oProp.getPrice() - oProp.getDiscount()) * Float.valueOf(request.getParameter("ndays"));
            }

            //Long propertyId, String customerId, String ownerId, LocalDate DOC, LocalDate DOT, String customerReview, int customerRating, String ownerReview, int ownerRating, String adminApprovalId, float total, String approveStatus
            systemTransaction newTrasac = new systemTransaction(propId, sysC.getId(), oProp.getOwner_username(), LocalDate.now(), null, 0, null, 0, null, total, "Pending");
            systemTransactionFacade.create(newTrasac);

            sysC.setBalance(sysC.getBalance() - total);
            sysC.getpTransaction().add(newTrasac);
            systemCustomerFacade.edit(sysC);
            oProp.setStatus("Rented/Sold");
            oProp.getpTransaction().add(newTrasac);
            systemPropertyFacade.edit(oProp);

            HttpSession hts = request.getSession();
            htps.setAttribute("account", sysC);
            request.getRequestDispatcher("customerHome.jsp").include(request, response);
            out.println("<br/><br/><h6>Property Acquisition Pending Owner Approval</h6>");

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
