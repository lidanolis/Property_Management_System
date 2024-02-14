package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemProperty;
import model.systemPropertyFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class transactionRejectLogic extends HttpServlet {

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String role = request.getParameter("role");
            Long id = Long.valueOf(request.getParameter("id"));
            System.out.println("name::" + name);
            float total = Float.valueOf(request.getParameter("ttotal"));
            String customerId = request.getParameter("tcustomer");

            systemTransaction ctr = systemTransactionFacade.find(id);
            systemProperty aProp = systemPropertyFacade.find(ctr.getPropertyId());
            
            ctr.setApprovalId(name);
            ctr.setApproveStatus("Rejected");
            systemTransactionFacade.edit(ctr);
            
            aProp.setStatus("Available");
            systemPropertyFacade.edit(aProp);

            systemCustomer sysC = systemCustomerFacade.find(customerId);
            sysC.setBalance(sysC.getBalance() + total);
            systemCustomerFacade.edit(sysC);

            String paymentViewLogicPath = "paymentViewLogic?name=" + name + "&amp;role=Owner";
            request.getRequestDispatcher(paymentViewLogicPath).forward(request, response);

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
