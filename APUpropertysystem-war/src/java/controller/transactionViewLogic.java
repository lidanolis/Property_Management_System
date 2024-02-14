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
import model.systemProperty;
import model.systemPropertyFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class transactionViewLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            List<systemTransaction> allT = new ArrayList<>();
            List<systemTransaction> fallT = new ArrayList<>();
            List<systemProperty> fallP = new ArrayList<>();

            System.out.println("name::" + name);
            System.out.println("role::" + role);

            allT = systemTransactionFacade.findAll();
            systemCustomer sysC = null;
            systemOwner sysO = null;
            if (role.equals("Customer")) {
                sysC = systemCustomerFacade.find(name);
            } else {
                sysO = systemOwnerFacade.find(name);
            }

            if (allT.isEmpty()) {
                if (role.equals("Customer")) {
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysC);
                    request.getRequestDispatcher("customerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>No Transaction Made</h6>");
                } else {
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysO);
                    request.getRequestDispatcher("ownerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>No Acquisition Made</h6>");
                }
            }

            if (role.equals("Customer")) {
                for (systemTransaction aT : allT) {
                    if (aT.getCustomerId().equals(name)) {
                        systemProperty aProp = systemPropertyFacade.find(aT.getPropertyId());
                        fallT.add(aT);
                        fallP.add(aProp);
                    }
                }
            } else {
                for (systemTransaction aT : allT) {
                    if (aT.getOwnerId().equals(name)) {
                        systemProperty aProp = systemPropertyFacade.find(aT.getPropertyId());
                        fallT.add(aT);
                        fallP.add(aProp);
                    }
                }
            }

            if (fallT.isEmpty() || fallP.isEmpty()) {

                if (role.equals("Customer")) {
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysC);
                    request.getRequestDispatcher("customerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>No Transaction Made</h6>");
                } else {
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysO);
                    request.getRequestDispatcher("ownerHome.jsp").include(request, response);
                    out.println("<br/><br/><h6>No Acquisition Made</h6>");
                }
            } else {
                HttpSession hts = request.getSession();
                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                hts.setAttribute("allT", fallT);
                hts.setAttribute("allP", fallP);
                request.getRequestDispatcher("transactionView.jsp").forward(request, response);
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
