/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import newBean.UserSLSB;

/**
 *
 * @author 695923
 */
public class LoginOps extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String logout = request.getParameter("logout");
        String message = "";
        UserSLSB usb = new UserSLSB();
        HttpSession session = request.getSession();
        UserSLSB validate = new UserSLSB();
        if (user != null && pass != null && !user.equals("") && !pass.equals("")) {
            session.setAttribute("user", user);
            if (validate.validateUser(user, pass)) {
                if (validate.userLocked(user)) {
                    session.invalidate();
                    message = "Account has been locked";
                    response.sendRedirect("index.jsp?message=" + message);
                } else if (validate.userAdmin(user)) {
                    session.setAttribute("admin", true);
                    response.sendRedirect("admin.jsp");
                } else {
                    response.sendRedirect("main.jsp?message=" + message);
                }

            } else {
                request.setAttribute("alert", usb.showInvalid());
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } else if (logout != null) {
            session.invalidate();
            request.setAttribute("alert", usb.showLogout());
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            request.setAttribute("alert", usb.showLogin());
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
