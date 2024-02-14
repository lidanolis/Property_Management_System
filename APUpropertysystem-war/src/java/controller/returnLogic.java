package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class returnLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemAdminFacade systemAdminFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = null;
            String role = null;
            String returnPath = null;

            name = request.getParameter("name");
            role = request.getParameter("role");
            System.out.println("return:name::" + name);
            System.out.println("role::" + role);
            returnPath = request.getParameter("returnPath");
            System.out.println("trole:" + role);
            systemCustomer sysC = null;
            systemOwner sysO = null;
            systemAdmin sysA = null;
            HttpSession hts = request.getSession();

            if (role.equals("Admin")) {
                sysA = systemAdminFacade.find(name);
                hts.setAttribute("account", sysA);

            } else if (role.equals("Owner")) {
                sysO = systemOwnerFacade.find(name);
                hts.setAttribute("account", sysO);

            } else if (role.equals("Customer")) {
                sysC = systemCustomerFacade.find(name);
                hts.setAttribute("account", sysC);
                System.out.println("returned:name::" + name);
                System.out.println("role::" + role);
            }
            request.getRequestDispatcher(returnPath).forward(request, response);
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
