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

public class loginLogic extends HttpServlet {

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
        String password = request.getParameter("password");
                    System.out.println("name::" + name);
        String role = request.getParameter("role");

        try (PrintWriter out = response.getWriter()) {

            try {

                if (name.length() == 0 || password.length() == 0) {
                    errorInput = "Please Input All Credentials";
                    throw new Exception();
                } else {
                    if (role.equals("Owner")) {
                        systemOwner sysO = systemOwnerFacade.find(name);

                        if (sysO != null) {

                            if (sysO.getStatus().equals("Pending")) {
                                errorInput = "Account Registration is Still Pending";
                                throw new Exception();
                            }

                            if (sysO.getPassword().equals(password)) {
                                HttpSession hts = request.getSession();
                                hts.setAttribute("account", sysO);
                                request.getRequestDispatcher("ownerHome.jsp").forward(request, response);
                            } else {
                                errorInput = "Invalid Password";
                                throw new Exception();
                            }

                        } else {
                            errorInput = "Invalid Username";
                            throw new Exception();
                        }
                    } else if (role.equals("Customer")) {
                        systemCustomer sysC = systemCustomerFacade.find(name);

                        if (sysC != null) {
                            if (sysC.getPassword().equals(password)) {
                                HttpSession hts = request.getSession();
                                hts.setAttribute("account", sysC);
                                request.getRequestDispatcher("customerHome.jsp").forward(request, response);
                            } else {
                                errorInput = "Invalid Password";
                                throw new Exception();
                            }

                        } else {
                            errorInput = "Invalid Username";
                            throw new Exception();
                        }
                    } else if (role.equals("Admin")) {
                        systemAdmin sysA = systemAdminFacade.find(name);

                        if (sysA != null) {
                            if (sysA.getPassword().equals(password)) {
                                HttpSession hts = request.getSession();
                                hts.setAttribute("account", sysA);
                                request.getRequestDispatcher("adminHome.jsp").forward(request, response);
                            } else {
                                errorInput = "Invalid Password";
                                throw new Exception();
                            }
                        } else {
                            errorInput = "Invalid Username";
                            throw new Exception();
                        }
                    } else {
                        errorInput = "Invalid Role";
                        throw new Exception();
                    }
                }

            } catch (Exception ex) {
                request.getRequestDispatcher("login.jsp").include(request, response);
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
