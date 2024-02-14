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
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemOwner;
import model.systemOwnerFacade;

public class ownerAccountLogic extends HttpServlet {

    @EJB
    private systemAdminFacade systemAdminFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String errorInput = "invalid input";
            String name = request.getParameter("name");
            String role = request.getParameter("role");
                        System.out.println("name::" + name);

            try {

                List<systemOwner> sysO = systemOwnerFacade.findAll();
                List<systemOwner> fsysO = new ArrayList<>();

                if (!(sysO.isEmpty())) {
                    for (systemOwner o : sysO) {

                        if ((o.getStatus().equals("Pending"))) {
                            fsysO.add(o);
                        }
                    }

                    if (fsysO.isEmpty()) {
                        errorInput = "No Pending Request";
                        throw new Exception();
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);
                    hts.setAttribute("fsysO", fsysO);
                    request.getRequestDispatcher("ownerApprovalList.jsp").forward(request, response);
                    
                } else {
                    errorInput = "No Owner Registered";
                    throw new Exception();
                }

            } catch (Exception EX) {
                systemAdmin sysA = systemAdminFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysA);
                request.getRequestDispatcher("adminHome.jsp").include(request, response);
                out.println("<br/><br/><h6>" + errorInput + "</h6>");
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
