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

public class profileListUpdateLogic extends HttpServlet {

    @EJB
    private systemAdminFacade systemAdminFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String errorInput = "Invalid Input";
        String aname = request.getParameter("aname");

        String name = request.getParameter("name");
        String role = request.getParameter("role");
            System.out.println("name::" + name);
        try (PrintWriter out = response.getWriter()) {
            try {
                String password = request.getParameter("password");
                int age = Integer.valueOf(request.getParameter("age"));
                String address = request.getParameter("address");
                String contact = request.getParameter("contact");
                char gender = request.getParameter("gender").charAt(0);

                if (password.length() == 0 || address.length() == 0 || contact.length() == 0) {
                    throw new Exception();
                } else {

                    if (role.equals("Owner")) {
                        systemOwner sysO = systemOwnerFacade.find(name);
                        sysO.setPassword(password);
                        sysO.setAge(age);
                        sysO.setAddress(address);
                        sysO.setContact(contact);
                        sysO.setGender(gender);
                        systemOwnerFacade.edit(sysO);

                        String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=Admin";
                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");

                    } else if (role.equals("Customer")) {
                        systemCustomer sysC = systemCustomerFacade.find(name);
                        sysC.setPassword(password);
                        sysC.setAge(age);
                        sysC.setAddress(address);
                        sysC.setContact(contact);
                        sysC.setGender(gender);
                        systemCustomerFacade.edit(sysC);

                        String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=Admin";
                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");

                    } else if (role.equals("Admin")) {
                        systemAdmin sysA = systemAdminFacade.find(name);
                        sysA.setPassword(password);
                        sysA.setAge(age);
                        sysA.setAddress(address);
                        sysA.setContact(contact);
                        sysA.setGender(gender);
                        systemAdminFacade.edit(sysA);

                        String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=Admin";
                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");
                    }
                }

            } catch (Exception ex) {
                String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=Admin";
                request.getRequestDispatcher(returnPath).include(request, response);
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
