package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;

public class propertyCreateLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String errorInput = "Invalid Input";
        String name = request.getParameter("name");
        String role = request.getParameter("role");
            System.out.println("name::" + name);
        try (PrintWriter out = response.getWriter()) {
            try {

                String propName = request.getParameter("propName");
                String propDesc = request.getParameter("propDesc");
                float propPrice = Float.valueOf(request.getParameter("propPrice"));

                if (propPrice <= 0) {
                    throw new Exception();
                }

                String checkDis = request.getParameter("propDis");
                float propDis = 0;
                if (checkDis.length() != 0) {
                    propDis = Float.valueOf(request.getParameter("propDis"));
                    if (propDis < 0) {
                        errorInput = "Invalid Discount Value";
                        throw new Exception();
                    }
                }
                if (propDis >= propPrice) {
                    errorInput = "Discount Cannot Exceed Base Price";
                    throw new Exception();
                }
                String propType = request.getParameter("propType");

                String propDur;
                if (propType.equals("Rent")) {
                    propDur = request.getParameter("propDur");
                } else {
                    propDur = "Buy";
                }

                if (propName.length() == 0) {
                    throw new Exception();
                } else {
                    systemOwner sysO = systemOwnerFacade.find(name);
                    //String name, String owner_username, String status, String description, String sales_type, float price, String DOC, float discount, String priceDayMonthYear
                    systemProperty newprop = new systemProperty(propName, name, "Available", propDesc, propType, propPrice, LocalDate.now(), propDis, propDur);
                    systemPropertyFacade.create(newprop);

                    sysO.getProperties().add(newprop);
                    systemOwnerFacade.edit(sysO);

                    String createPropPath = "propertyCreate.jsp?name=" + name + "&amp;role=" + role;
                    request.getRequestDispatcher(createPropPath).include(request, response);
                    out.println("<br/><br/><h6>Successfully Added New Property</h6>");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                String returnPath = "propertyCreate.jsp?name=" + name + "&amp;role=" + role;
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
