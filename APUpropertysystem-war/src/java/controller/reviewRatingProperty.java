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
import model.systemCustomerFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class reviewRatingProperty extends HttpServlet {

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String role = request.getParameter("role");
            Long propID = Long.valueOf(request.getParameter("propID"));
            String cpropName = request.getParameter("cpropName");
            String cpropType = request.getParameter("cpropType");
            String cpropDur = request.getParameter("cpropDur");
            String cpriceRange = request.getParameter("cpriceRange");
            String cpropPrice = request.getParameter("cpropPrice");
            String cpropStatus = request.getParameter("cpropStatus");
            System.out.println("name::" + name);
            List<systemTransaction> Filteredctr = new ArrayList<>();

            try {
                List<systemTransaction> ctr = systemTransactionFacade.findAll();

                if (ctr.isEmpty()) {
                    System.out.println("No Transaction");
                    throw new Exception();
                }

                for (systemTransaction c : ctr) {
                    if (String.valueOf(c.getPropertyId()).equals(String.valueOf(propID))) {
                        Filteredctr.add(c);
                    }
                }

                if (Filteredctr.isEmpty()) {
                    System.out.println("No Transaction For Property");
                    throw new Exception();
                }
                System.out.println("Have Transaction");
                HttpSession hts = request.getSession();
                hts.setAttribute("transactionList", Filteredctr);

                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                hts.setAttribute("propName", cpropName);
                hts.setAttribute("propType", cpropType);
                hts.setAttribute("propDur", cpropDur);
                hts.setAttribute("priceRange", cpriceRange);
                hts.setAttribute("propPrice", cpropPrice);
                hts.setAttribute("propStatus", cpropStatus);
                request.getRequestDispatcher("reviewRatingProperty.jsp").forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
                HttpSession hts = request.getSession();
                hts.setAttribute("transactionList", Filteredctr);

                hts.setAttribute("name", name);
                hts.setAttribute("role", role);
                hts.setAttribute("propName", cpropName);
                hts.setAttribute("propType", cpropType);
                hts.setAttribute("propDur", cpropDur);
                hts.setAttribute("priceRange", cpriceRange);
                hts.setAttribute("propPrice", cpropPrice);
                hts.setAttribute("propStatus", cpropStatus);
                request.getRequestDispatcher("reviewRatingProperty.jsp").forward(request, response);

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
