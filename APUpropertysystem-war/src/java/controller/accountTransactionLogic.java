package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class accountTransactionLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");
        String role = request.getParameter("role");
                    System.out.println("name::" + name);
        String errorInput = "Invalid Input";

        try (PrintWriter out = response.getWriter()) {

            systemOwner sysO = null;
            systemCustomer sysC = null;

            if (role.equals("Owner")) {
                sysO = systemOwnerFacade.find(name);
            } else if (role.equals("Customer")) {
                sysC = systemCustomerFacade.find(name);
            }

            try {

                if (role.equals("Owner")) {

                    float amount = Float.valueOf(request.getParameter("amount"));
                    String type = request.getParameter("tranType");

                    if (type.equals("Deposit")) {
                        sysO.setBalance(sysO.getBalance() + amount);

                    } else if (type.equals("Withdraw")) {
                        if (amount > sysO.getBalance()) {
                            errorInput = "Account does not have sufficient amount";
                            throw new Exception();
                        } else {
                            sysO.setBalance(sysO.getBalance() - amount);
                        }

                    }
                    systemOwnerFacade.edit(sysO);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysO);
                    hts.setAttribute("role", role);
                    request.getRequestDispatcher("accountView.jsp").include(request, response);
                    out.println("<br/><br/><h6>Successfully Updated</h6>");

                } else if (role.equals("Customer")) {

                    float amount = Float.valueOf(request.getParameter("amount"));
                    String type = request.getParameter("tranType");

                    if (type.equals("Deposit")) {
                        sysC.setBalance(sysC.getBalance() + amount);
                    } else if (type.equals("Withdraw")) {
                        if (amount > sysC.getBalance()) {
                            errorInput = "Account does not have sufficient amount";
                            throw new Exception();
                        } else {
                            sysC.setBalance(sysC.getBalance() - amount);
                        }

                    }
                    systemCustomerFacade.edit(sysC);
                    HttpSession hts = request.getSession();
                    hts.setAttribute("account", sysC);
                    hts.setAttribute("role", role);
                    request.getRequestDispatcher("accountView.jsp").include(request, response);
                    out.println("<br/><br/><h6>Successfully Updated</h6>");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                HttpSession hts = request.getSession();

                if (role.equals("Owner")) {
                    hts.setAttribute("Account", sysO);
                } else if (role.equals("Customer")) {
                    hts.setAttribute("Account", sysC);
                }
                hts.setAttribute("role", role);
                request.getRequestDispatcher("accountView.jsp").include(request, response);
                    out.println("<br/><br/><h6>"+errorInput+"</h6>");
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
