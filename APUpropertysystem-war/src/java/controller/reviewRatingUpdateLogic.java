package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.systemTransaction;
import model.systemTransactionFacade;

public class reviewRatingUpdateLogic extends HttpServlet {

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String role = request.getParameter("role");
                        System.out.println("name::" + name);
            String errorInput = "Fail to Update, Invalid Input";
            try {

                Long id = Long.valueOf(request.getParameter("id"));

                int rating = Integer.valueOf(request.getParameter("trating"));
                String review = request.getParameter("treview");

                if (rating == 0) {
                    errorInput = "Fail to Update, Rating Cannot Be Empty";
                    throw new Exception();
                }

                systemTransaction ctr = systemTransactionFacade.find(id);
                if (role.equals("Customer")) {
                    ctr.setCustomerReview(review);
                    ctr.setCustomerRating(rating);
                } else if (role.equals("Owner")) {
                    ctr.setOwnerReview(review);
                    ctr.setOwnerRating(rating);
                }

                systemTransactionFacade.edit(ctr);
                HttpSession hts = request.getSession();
                errorInput = "successfully Updated";
                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                hts.setAttribute("msg", errorInput);
                request.getRequestDispatcher("reviewRatingViewLogicReturn").forward(request, response);

            } catch (Exception Ex) {
                HttpSession hts = request.getSession();
                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                hts.setAttribute("msg", errorInput);
                request.getRequestDispatcher("reviewRatingViewLogicReturn").forward(request, response);
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
