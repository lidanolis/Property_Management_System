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
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;

public class deletePropertyLogic extends HttpServlet {

    @EJB
    private systemAdminFacade systemAdminFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String errorInput = "Invalid Input";

        String name = request.getParameter("name");
        String role = request.getParameter("role");
                    System.out.println("name::" + name);
        Long propID = Long.valueOf(request.getParameter("propID"));

        String cpropName = request.getParameter("cpropName");
        String cpropType = request.getParameter("cpropType");
        String cpropDur = request.getParameter("cpropDur");
        String cpriceRange = request.getParameter("cpriceRange");
        String cpropPrice = request.getParameter("cpropPrice");
        String cpropStatus = request.getParameter("cpropStatus");

        try (PrintWriter out = response.getWriter()) {
            systemProperty tProp = systemPropertyFacade.find(propID);
            systemOwner sysO = systemOwnerFacade.find(tProp.getOwner_username());
            systemOwnerFacade.removePropertyFromOwner(sysO.getId(), propID);
            systemPropertyFacade.removeProperty(propID);

            HttpSession htps = request.getSession();

            if (role.equals("Owner")) {
                htps.setAttribute("account", sysO);
                request.getRequestDispatcher("ownerHome.jsp").include(request, response);

                out.println("<br/><br/><h6>Property Removed</h6>");
            } else {
                systemAdmin sysA = systemAdminFacade.find(name);
                htps.setAttribute("account", sysA);
                request.getRequestDispatcher("adminHome.jsp").include(request, response);

                out.println("<br/><br/><h6>Property Removed</h6>");
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
