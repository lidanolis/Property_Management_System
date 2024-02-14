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
import javax.servlet.http.HttpSession;
import model.systemAdmin;
import model.systemAdminFacade;
import model.systemOwner;
import model.systemOwnerFacade;

public class ownerRejectLogic extends HttpServlet {

    @EJB
    private systemOwnerFacade systemOwnerFacade;

    @EJB
    private systemAdminFacade systemAdminFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String errorInput = "invalid input";
            String name = request.getParameter("name");
            String arole = request.getParameter("arole");
            String aname = request.getParameter("aname");
            System.out.println("name::" + aname);

            try {
                systemOwner towner = systemOwnerFacade.find(name);
                sendMail(towner.getGmail(), "Sadly, Your Owner Account Has Been Rejected.");
                systemOwnerFacade.removeOwner(name);
                List<systemOwner> sysO = systemOwnerFacade.findAll();
                List<systemOwner> fsysO = new ArrayList<>();

                if (!(sysO.isEmpty())) {
                    for (systemOwner o : sysO) {

                        if ((o.getStatus().equals("Pending"))) {
                            fsysO.add(o);
                        }
                    }

                    if (fsysO.isEmpty()) {
                        errorInput = "No Pending Registration";
                        throw new Exception();
                    }

                    HttpSession hts = request.getSession();
                    hts.setAttribute("name", aname);
                    hts.setAttribute("role", arole);
                    hts.setAttribute("fsysO", fsysO);
                    request.getRequestDispatcher("ownerApprovalList.jsp").forward(request, response);

                } else {
                    errorInput = "No Owner Registered";
                    throw new Exception();
                }

            } catch (Exception EX) {
                systemAdmin sysA = systemAdminFacade.find(aname);
                HttpSession hts = request.getSession();
                hts.setAttribute("account", sysA);
                request.getRequestDispatcher("adminHome.jsp").include(request, response);
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
