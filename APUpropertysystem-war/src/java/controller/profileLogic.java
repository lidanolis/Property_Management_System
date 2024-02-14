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

public class profileLogic extends HttpServlet {

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
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            System.out.println("name::" + name);
            System.out.println("role::" + role);

            systemCustomer sysC = null;
            systemOwner sysO = null;
            systemAdmin sysA = null;

            if (role.equals("Admin")) {
                sysA = systemAdminFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysA);
                hts.setAttribute("role", "Admin");
                request.getRequestDispatcher("profile.jsp").forward(request, response);

            } else if (role.equals("Owner")) {
                sysO = systemOwnerFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysO);
                hts.setAttribute("role", "Owner");
                request.getRequestDispatcher("profile.jsp").forward(request, response);

            } else if (role.equals("Customer")) {
                sysC = systemCustomerFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysC);
                hts.setAttribute("role", "Customer");
                request.getRequestDispatcher("profile.jsp").forward(request, response);

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
