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
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class paymentViewLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String role = request.getParameter("role");
            String errorInput = "Invalid Input";
            System.out.println("name::" + name);
            try {
                List<systemTransaction> Filteredctr = new ArrayList<>();
                List<systemTransaction> ctr = new ArrayList<>();
                ctr = systemTransactionFacade.findAll();

                if (ctr.isEmpty()) {
                    errorInput = "No Transaction Has Been Made";
                    throw new Exception();
                }

                if (role.equals("Owner")) {
                    for (systemTransaction c : ctr) {
                        if (c.getApproveStatus().equals("Pending")) {
                            Filteredctr.add(c);
                        }
                    }

                    if (Filteredctr.isEmpty()) {
                        errorInput = "No Pending Transaction";
                        throw new Exception();
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("transactionList", Filteredctr);
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);
                    request.getRequestDispatcher("paymentApprove.jsp").forward(request, response);

                } else {
                    errorInput = "Invalid Role";
                    throw new Exception();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                systemOwner sysO = systemOwnerFacade.find(name);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysO);
                request.getRequestDispatcher("ownerHome.jsp").include(request, response);
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
