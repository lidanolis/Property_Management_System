package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwner;
import model.systemOwnerFacade;
import model.systemProperty;
import model.systemPropertyFacade;
import model.systemTransaction;
import model.systemTransactionFacade;

public class accountRemovalLogic extends HttpServlet {

    @EJB
    private systemTransactionFacade systemTransactionFacade;

    @EJB
    private systemPropertyFacade systemPropertyFacade;

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
        String aname = request.getParameter("aname");

        String name = request.getParameter("name");
        String role = request.getParameter("role");
        System.out.println("name::" + name);

        try (PrintWriter out = response.getWriter()) {
            try {
                if (role.equals("Owner")) {
                    systemOwner sysO = systemOwnerFacade.find(name);
                    List<systemProperty> allprop = new ArrayList<>();
                    allprop = systemPropertyFacade.findAll();

                    if (!(allprop.isEmpty())) {
                        for (systemProperty aprop : allprop) {
                            if (aprop.getOwner_username().equals(name)) {
                                systemOwnerFacade.removePropertyFromOwner(sysO.getId(), aprop.getId());
                                systemPropertyFacade.removeProperty(aprop.getId());
                            }
                        }

                    }

                    List<systemTransaction> allTran = new ArrayList<>();
                    allTran = systemTransactionFacade.findAll();

                    if (!(allTran.isEmpty())) {
                        for (systemTransaction aTran : allTran) {
                            if (aTran.getOwnerId().equals(name)) {
                                systemCustomerFacade.removeTransactionFromCustomer(aTran.getCustomerId(), aTran.getId());
                                systemTransactionFacade.removeTransaction(aTran.getId());
                            }
                        }

                    }
                    sendMail(sysO.getGmail(), "Your Owner Account Has Been Removed By The Admin.");
                    systemOwnerFacade.removeOwner(name);
                    String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=" + role;
                    errorInput = "Successfully Updated";
                    request.getRequestDispatcher(returnPath).include(request, response);
                    out.println("<br/><br/><h6>" + errorInput + "</h6>");

                } else if (role.equals("Customer")) {
                    systemCustomer sysC = systemCustomerFacade.find(name);
                    List<systemTransaction> allTran = new ArrayList<>();
                    allTran = systemTransactionFacade.findAll();

                    if (!(allTran.isEmpty())) {
                        for (systemTransaction aTran : allTran) {
                            if (aTran.getCustomerId().equals(name)) {
                                systemCustomerFacade.removeTransactionFromCustomer(sysC.getId(), aTran.getId());
                                systemPropertyFacade.removeTransactionFromProperty(aTran.getPropertyId(), aTran.getId());
                                systemTransactionFacade.removeTransaction(aTran.getId());
                            }
                        }

                    }
                    sendMail(sysC.getGmail(), "Your Customer Account Has Been Removed By The Admin.");
                    systemCustomerFacade.removeCustomer(name);
                    String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=" + role;
                    errorInput = "Successfully Updated";
                    request.getRequestDispatcher(returnPath).include(request, response);
                    out.println("<br/><br/><h6>" + errorInput + "</h6>");

                } else if (role.equals("Admin")) {
                    systemAdmin sysA = systemAdminFacade.find(name);
                    sendMail(sysA.getGmail(), "Your Admin Account Has Been Removed By The Admin.");
                    systemAdminFacade.removeAdmin(name);
                    String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=" + role;
                    errorInput = "Successfully Updated";
                    request.getRequestDispatcher(returnPath).include(request, response);
                    out.println("<br/><br/><h6>" + errorInput + "</h6>");
                }
            } catch (Exception ex) {
                String returnPath = "profileSearch.jsp?name=" + aname + "&amp;role=" + role;
                errorInput = "Successfully Updated";
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

    public static void sendMail(String recepient, String note) {
        System.out.println("send");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "csheamon1996@gmail.com";
        String password = "tkvkeckggtpprbzn";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        }
        );
        Message message = prepareMessage(session, myAccountEmail, recepient, note);
        try {
            Transport.send(message);
            System.out.println("successful");
        } catch (MessagingException ex) {
            System.out.println("fail");
            ex.printStackTrace();
        }

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String note) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("APU Property System");
            message.setText(note);
            return message;
        } catch (Exception ex) {
            System.out.println("failed here");
            ex.printStackTrace();
        }
        return null;

    }

}
