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
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;

public class propertyViewLogic extends HttpServlet {

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String errorInput = "Invalid Input";
        String name = null;
        String role = null;
        String propName = null;
        String propType = null;
        String propDur = null;
        String priceRange = null;
        String propPrice = null;
        String propStatus = null;

        name = request.getParameter("name");
        role = request.getParameter("role");
        propName = request.getParameter("propName");
        propType = request.getParameter("propType");
        propDur = request.getParameter("propDur");
        priceRange = request.getParameter("priceRange");
        propPrice = request.getParameter("propPrice");
        propStatus = request.getParameter("propStatus");
            System.out.println("name::" + name);
        String propertySearchLogicPath = "propertySearch.jsp?name=" + name + "&amp;role=" + role;

        try (PrintWriter out = response.getWriter()) {
            List<systemProperty> allProp = systemPropertyFacade.findAll();
            List<systemProperty> filterProp = new ArrayList<>();

            try {
                if (allProp.isEmpty()) {
                    errorInput = "No Property Has been Added to The System";
                    throw new Exception();
                }

                if (!(priceRange.equals("All"))) {
                    if (propPrice.isEmpty()) {
                        throw new Exception();
                    } else {
                        float cpropPrice = Float.valueOf(propPrice);
                    }
                }

                for (systemProperty aProp : allProp) {

                    if (role.equals("Owner")) {
                        if (!(aProp.getOwner_username().equals(name))) {
                            continue;
                        }
                    }
                    if (!(propName.isEmpty())) {
                        if (!(aProp.getName().equals(propName))) {
                            continue;
                        }
                    }

                    if (!(propType.equals("All"))) {
                        if (!(aProp.getSales_type().equals(propType))) {
                            continue;
                        } else {
                            if (propType.equals("Rent") && !(propDur.equals("All"))) {
                                if (!(aProp.getPriceDayMonthYear().equals(propDur))) {
                                    continue;
                                }
                            }
                        }
                    }

                    if (!(priceRange.equals("All"))) {
                        float cpropPrice = Float.valueOf(propPrice);
                        if (priceRange.equals("Lower")) {
                            if (!((aProp.getPrice() - aProp.getDiscount()) <= cpropPrice)) {
                                continue;
                            }
                        } else if (priceRange.equals("Higher")) {
                            if (!((aProp.getPrice() - aProp.getDiscount()) >= cpropPrice)) {
                                continue;
                            }
                        } else if (priceRange.equals("Equal")) {
                            if (!((aProp.getPrice() - aProp.getDiscount()) == cpropPrice)) {
                                continue;
                            }
                        }

                    }
                    
                    if (!(propStatus.equals("All"))) {
                        if (!(aProp.getStatus().equals(propStatus))) {
                            continue;
                        }
                    }

                    filterProp.add(aProp);
                }

                if (filterProp.isEmpty()) {
                    errorInput = "no valid match";
                    throw new Exception();

                } else {

                    HttpSession hts = request.getSession();
                    hts.setAttribute("properties", filterProp);
                    hts.setAttribute("role", role);
                    hts.setAttribute("name", name);

                    hts.setAttribute("propName", propName);
                    hts.setAttribute("propType", propType);
                    hts.setAttribute("propDur", propDur);
                    hts.setAttribute("priceRange", priceRange);
                    hts.setAttribute("propPrice", propPrice);
                    hts.setAttribute("propStatus", propStatus);

                    if (role.equals("Customer")) {
                        systemCustomer sysC = systemCustomerFacade.find(name);
                        hts.setAttribute("balance", sysC.getBalance());
                        request.getRequestDispatcher("propertyViewAcquire.jsp").forward(request, response);

                    } else {
                        request.getRequestDispatcher("propertyView.jsp").forward(request, response);
                    }

                }
            } catch (Exception EX) {
                request.getRequestDispatcher(propertySearchLogicPath).include(request, response);
                out.println("<br/><br/><h6>" + errorInput +"</h6>");
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
