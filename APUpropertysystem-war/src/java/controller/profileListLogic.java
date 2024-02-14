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
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwner;
import model.systemOwnerFacade;

public class profileListLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemCustomerFacade systemCustomerFacade;

    @EJB
    private systemAdminFacade systemAdminFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String errorInput = "invalid input";
            String name = request.getParameter("name");
            String role = request.getParameter("role");
            System.out.println("name::" + name);

            try {

                String lname = request.getParameter("lname");
                String lrole = request.getParameter("lrole");
                String lgender = request.getParameter("lgender");
                String lage = request.getParameter("lage");

                List<systemAdmin> sysA = systemAdminFacade.findAll();
                List<systemOwner> sysO = systemOwnerFacade.findAll();
                List<systemCustomer> sysC = systemCustomerFacade.findAll();

                List<systemAdmin> fsysA = new ArrayList<>();
                List<systemOwner> fsysO = new ArrayList<>();
                List<systemCustomer> fsysC = new ArrayList<>();

                if (!(lage.equals(""))) {
                    try {
                        int iage = Integer.valueOf(lage);
                    } catch (Exception EX) {
                        errorInput = "Invalid Input";
                        throw new Exception();
                    }
                }

                if (!(lrole.equals("All"))) {

                    if (lrole.equals("Admin")) {

                        if (!(sysA.isEmpty())) {
                            for (systemAdmin a : sysA) {
                                if (!(lname.equals(""))) {
                                    if (!(a.getId().equals(lname))) {
                                        continue;
                                    }
                                }
                                if (!(lgender.equals("All"))) {
                                    if (!(String.valueOf(a.getGender()).equals(lgender))) {
                                        continue;
                                    }
                                }
                                if (!(lage.equals(""))) {
                                    int iage = Integer.valueOf(lage);
                                    if (!(a.getAge() == iage)) {
                                        continue;
                                    }
                                }
                                fsysA.add(a);
                            }

                            if (fsysA.isEmpty()) {
                                errorInput = "No Admin Found";
                                throw new Exception();
                            }

                            HttpSession hts = request.getSession();
                            hts.setAttribute("name", name);
                            hts.setAttribute("role", role);
                            hts.setAttribute("fsysC", fsysC);
                            hts.setAttribute("fsysA", fsysA);
                            hts.setAttribute("fsysO", fsysO);
                            hts.setAttribute("lrole", lrole);

                            request.getRequestDispatcher("profileList.jsp").forward(request, response);

                        } else {
                            errorInput = "No Admin Registered";
                            throw new Exception();
                        }
                    } else if (lrole.equals("Owner")) {
                        if (!(sysO.isEmpty())) {
                            for (systemOwner o : sysO) {
                                if (!(lname.equals(""))) {
                                    if (!(o.getId().equals(lname))) {
                                        continue;
                                    }
                                }
                                if (!(lgender.equals("All"))) {
                                    if (!(String.valueOf(o.getGender()).equals(lgender))) {
                                        continue;
                                    }
                                }
                                if (!(lage.equals(""))) {
                                    int iage = Integer.valueOf(lage);
                                    if (!(o.getAge() == iage)) {
                                        continue;
                                    }
                                }
                                if (o.getStatus().equals("Pending")) {
                                    continue;
                                }
                                fsysO.add(o);
                            }

                            if (fsysO.isEmpty()) {
                                errorInput = "No Owner Found";
                                throw new Exception();
                            }

                            HttpSession hts = request.getSession();
                            hts.setAttribute("name", name);
                            hts.setAttribute("role", role);
                            hts.setAttribute("fsysC", fsysC);
                            hts.setAttribute("fsysA", fsysA);
                            hts.setAttribute("fsysO", fsysO);
                            hts.setAttribute("lrole", lrole);
                            request.getRequestDispatcher("profileList.jsp").forward(request, response);
                        } else {
                            errorInput = "No Owner Registered";
                            throw new Exception();
                        }
                    } else if (lrole.equals("Customer")) {
                        if (!(sysC.isEmpty())) {
                            for (systemCustomer c : sysC) {
                                if (!(lname.equals(""))) {
                                    if (!(c.getId().equals(lname))) {
                                        continue;
                                    }
                                }
                                if (!(lgender.equals("All"))) {
                                    if (!(String.valueOf(c.getGender()).equals(lgender))) {
                                        continue;
                                    }
                                }
                                if (!(lage.equals(""))) {
                                    int iage = Integer.valueOf(lage);
                                    if (!(c.getAge() == iage)) {
                                        continue;
                                    }
                                }
                                fsysC.add(c);
                            }

                            if (fsysC.isEmpty()) {
                                errorInput = "No Customer Found";
                                throw new Exception();
                            }

                            HttpSession hts = request.getSession();
                            hts.setAttribute("name", name);
                            hts.setAttribute("role", role);
                            hts.setAttribute("fsysC", fsysC);
                            hts.setAttribute("fsysA", fsysA);
                            hts.setAttribute("fsysO", fsysO);
                            hts.setAttribute("lrole", lrole);
                            request.getRequestDispatcher("profileList.jsp").forward(request, response);
                        } else {
                            errorInput = "No Customer Registered";
                            throw new Exception();
                        }
                    }
                } else {
                    if (!(sysA.isEmpty())) {
                        for (systemAdmin a : sysA) {
                            if (!(lname.equals(""))) {
                                if (!(a.getId().equals(lname))) {
                                    continue;
                                }
                            }
                            if (!(lgender.equals("All"))) {
                                if (!(String.valueOf(a.getGender()).equals(lgender))) {
                                    continue;
                                }
                            }
                            if (!(lage.equals(""))) {
                                if (!(String.valueOf(a.getAge()).equals(lage))) {
                                    continue;
                                }
                            }
                            fsysA.add(a);
                        }
                    }

                    if (!(sysO.isEmpty())) {
                        for (systemOwner o : sysO) {
                            if (!(lname.equals(""))) {
                                if (!(o.getId().equals(lname))) {
                                    continue;
                                }
                            }
                            if (!(lgender.equals("All"))) {
                                if (!(String.valueOf(o.getGender()).equals(lgender))) {
                                    continue;
                                }
                            }
                            if (!(lage.equals(""))) {
                                if (!(String.valueOf(o.getAge()).equals(lage))) {
                                    continue;
                                }
                            }
                            if (o.getStatus().equals("Pending")) {
                                continue;
                            }
                            fsysO.add(o);
                        }
                    }

                    if (!(sysC.isEmpty())) {
                        for (systemCustomer c : sysC) {
                            if (!(lname.equals(""))) {
                                if (!(c.getId().equals(lname))) {
                                    continue;
                                }
                            }
                            if (!(lgender.equals("All"))) {
                                if (!(String.valueOf(c.getGender()).equals(lgender))) {
                                    continue;
                                }
                            }
                            if (!(lage.equals(""))) {
                                if (!(String.valueOf(c.getAge()).equals(lage))) {
                                    continue;
                                }
                            }
                            fsysC.add(c);
                        }
                    }

                    if (fsysA.isEmpty() && fsysO.isEmpty() && fsysC.isEmpty()) {
                        errorInput = "No Account Found";
                        throw new Exception();
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", name);
                    hts.setAttribute("role", role);

                    hts.setAttribute("fsysC", fsysC);
                    hts.setAttribute("fsysA", fsysA);
                    hts.setAttribute("fsysO", fsysO);

                    hts.setAttribute("lrole", lrole);

                    request.getRequestDispatcher("profileList.jsp").forward(request, response);
                }

            } catch (Exception EX) {
                String returnPath = "profileSearch.jsp?name=" + name + "&amp;role=Admin";
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
