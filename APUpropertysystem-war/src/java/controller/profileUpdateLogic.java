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

public class profileUpdateLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemAdminFacade systemAdminFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String errorInput = "Invalid Input";
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

                        HttpSession hts = request.getSession();
                        hts.setAttribute("account", sysO);
                        hts.setAttribute("role", "Owner");
                        request.getRequestDispatcher("profile.jsp").include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");
                    } else if (role.equals("Customer")) {
                        systemCustomer sysC = systemCustomerFacade.find(name);
                        sysC.setPassword(password);
                        sysC.setAge(age);
                        sysC.setAddress(address);
                        sysC.setContact(contact);
                        sysC.setGender(gender);
                        systemCustomerFacade.edit(sysC);

                        HttpSession hts = request.getSession();
                        hts.setAttribute("account", sysC);
                        hts.setAttribute("role", "Customer");
                        request.getRequestDispatcher("profile.jsp").include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");
                    } else if (role.equals("Admin")) {
                        systemAdmin sysA = systemAdminFacade.find(name);
                        sysA.setPassword(password);
                        sysA.setAge(age);
                        sysA.setAddress(address);
                        sysA.setContact(contact);
                        sysA.setGender(gender);
                        systemAdminFacade.edit(sysA);

                        HttpSession hts = request.getSession();
                        hts.setAttribute("account", sysA);
                        hts.setAttribute("role", "Admin");
                        request.getRequestDispatcher("profile.jsp").include(request, response);
                        out.println("<br/><br/><h6>Successfully Updated</h6>");
                    }
                }

            } catch (Exception ex) {
                systemCustomer sysC = null;
                systemOwner sysO = null;
                systemAdmin sysA = null;

                if (role.equals("Admin")) {
                    sysA = systemAdminFacade.find(name);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysA);
                    hts.setAttribute("role", "Admin");
                    request.getRequestDispatcher("profile.jsp").include(request, response);
                    out.println("<br/><br/><h6>" + errorInput+"</h6>");

                } else if (role.equals("Owner")) {
                    sysO = systemOwnerFacade.find(name);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysO);
                    hts.setAttribute("role", "Owner");
                    request.getRequestDispatcher("profile.jsp").include(request, response);
                    out.println("<br/><br/><h6>" + errorInput+"</h6>");

                } else if (role.equals("Customer")) {
                    sysC = systemCustomerFacade.find(name);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysC);
                    hts.setAttribute("role", "Customer");
                    request.getRequestDispatcher("profile.jsp").include(request, response);
                    out.println("<br/><br/><h6>" + errorInput+"</h6>");

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
