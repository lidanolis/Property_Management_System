package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;

public class updatePropertyLogic extends HttpServlet {

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
        Long propID = Long.valueOf(request.getParameter("propID"));
            System.out.println("name::" + name);
        String cpropName = request.getParameter("cpropName");
        String cpropType = request.getParameter("cpropType");
        String cpropDur = request.getParameter("cpropDur");
        String cpriceRange = request.getParameter("cpriceRange");
        String cpropPrice = request.getParameter("cpropPrice");
        String cpropStatus = request.getParameter("cpropStatus");

        try (PrintWriter out = response.getWriter()) {
            try {
                systemProperty tProp = systemPropertyFacade.find(propID);
                systemOwner sysO = systemOwnerFacade.find(tProp.getOwner_username());

                String propName = request.getParameter("propName");
                String propDesc = request.getParameter("propDesc");
                float propPrice = Float.valueOf(request.getParameter("propPrice"));
                float propDis = Float.valueOf(request.getParameter("propDis"));
                String propType = request.getParameter("propType");
                String propDur;

                if (propPrice <= propDis) {
                    throw new Exception();
                }

                if (propType.equals("Rent")) {
                    propDur = request.getParameter("propDur");
                } else {
                    propDur = "Buy";
                }
                String propStatus = request.getParameter("propStatus");

                if (propName.length() == 0) {
                    throw new Exception();
                } else {
                    for (systemProperty editP : sysO.getProperties()) {
                        if (propID == editP.getId()) //String name, String owner_username, String status, String description, String sales_type, float price, LocalDate DOC, float discount
                        {
                            editP.setName(propName);
                            editP.setDescription(propDesc);
                            editP.setPrice(propPrice);
                            editP.setDiscount(propDis);
                            editP.setSales_type(propType);
                            editP.setPriceDayMonthYear(propDur);
                            editP.setStatus(propStatus);
                            systemPropertyFacade.edit(editP);
                            errorInput = "Successfully Updated";
                            String returnpath = "propertySearch.jsp?name=" + name + "&amp;role=" + role;
                            request.getRequestDispatcher(returnpath).include(request, response);
                            out.println("<br/><br/><h6>" + errorInput + "</h6>");
                        }
                    }
                }
            } catch (Exception ex) {
                String returnpath = "propertySearch.jsp?name=" + name + "&amp;role=" + role;
                request.getRequestDispatcher(returnpath).include(request, response);
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
