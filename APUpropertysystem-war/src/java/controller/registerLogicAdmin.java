package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
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
import javax.servlet.http.HttpSession;
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemCustomer;
import model.systemCustomerFacade;
import model.systemOwner;
import model.systemOwnerFacade;

public class registerLogicAdmin extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {

            String role = null;
            String name = null;
            String password = null;
            String address = null;
            String contact = null;
            String gmail = null;
            String aname = request.getParameter("aname");

            role = request.getParameter("role");
            System.out.println("frole:" + role);
            name = request.getParameter("name");
            password = request.getParameter("password");
            address = request.getParameter("address");
            contact = request.getParameter("contact");
            gmail = request.getParameter("gmail");
            System.out.println("name::" + name);
            String returnPath = "registerForAdmin.jsp?name=" + aname + "&amp;role=Admin";

            try {
                if (name.length() == 0 || password.length() == 0 || address.length() == 0 || contact.length() == 0 || gmail.length() == 0) {
                    errorInput = "Please Input All Credentials";
                    throw new Exception();
                }

                if (!(gmail.contains("@gmail.com"))) {
                    errorInput = "Invalid Gmail";
                    throw new Exception();
                }

                int age = Integer.valueOf(request.getParameter("age"));

                char gender = request.getParameter("gender").charAt(0);

                if (role.equals("Owner")) {
                    systemOwner sysO = systemOwnerFacade.find(name);

                    if (sysO == null) {
                        sendMail(gmail, "You Have Registered An Onwer Account. This Account Has Been Approved! Stay Updated With The Account Through This Mail.");
                        //String id, char gender, String contact, String address, int age, float balance, String password, LocalDate DOC, String status, String gmail
                        systemOwnerFacade.create(new systemOwner(name, gender, contact, address, age, 0, password, LocalDate.now(), "Approved", gmail));
                        systemAdmin sysA = systemAdminFacade.find(aname);
                        systemOwner towner = systemOwnerFacade.find(name);
                        towner.setSystemAdmin(sysA);
                        systemOwnerFacade.edit(towner);

                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Owner Account Registered</h6>");
                    } else {
                        if (sysO.getStatus().equals("Pending")) {
                            errorInput = "This username has already been used to submit for creating an account, please select another";
                        } else {
                            errorInput = "Username is already in use, please select another";
                        }
                        throw new Exception();
                    }
                } else if (role.equals("Customer")) {
                    systemCustomer sysC = systemCustomerFacade.find(name);
                    if (sysC == null) {
                        sendMail(gmail, "You Have Registered For A Customer Account. Stay Updated With The Account Through This Mail.");
                        //String id, char gender, String contact, String address, int age, float balance, String password, LocalDate DOC, String gmail
                        systemCustomerFacade.create(new systemCustomer(name, gender, contact, address, age, 0, password, LocalDate.now(),gmail));
                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Successfully Registered The Customer</h6>");
                    } else {
                        errorInput = "Username is already in use, please select another";
                        throw new Exception();
                    }
                } else if (role.equals("Admin")) {
                    systemAdmin sysA = systemAdminFacade.find(name);
                    if (sysA == null) {
                        sendMail(gmail, "You Have Registered For An Admin Account. Stay Updated With The Account Through This Mail.");
                        //String id, char gender, String contact, String address, int age, String password, LocalDate DOC, String gmail
                        systemAdminFacade.create(new systemAdmin(name, gender, contact, address, age, password, LocalDate.now(),gmail));
                        request.getRequestDispatcher(returnPath).include(request, response);
                        out.println("<br/><br/><h6>Successfully Registered The Admin</h6>");
                    } else {
                        errorInput = "Username is already in use, please select another";
                        throw new Exception();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
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
