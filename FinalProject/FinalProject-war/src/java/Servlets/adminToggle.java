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
import newBean.UserSLSB;

/**
 *
 * @author 695923
 */
public class adminToggle extends HttpServlet {

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
        String status = request.getParameter("status");
        String userAdmin = request.getParameter("userAdmin");
        String statusLock = request.getParameter("statusLock");
        String userLocked = request.getParameter("userLock");
        String reset = request.getParameter("reset");
        String delete = request.getParameter("delete");
        String message = "";
        UserSLSB usb = new UserSLSB();
        if (status != null) {
            if (status.equals("1")) {
                message="admin status changed";
                usb.adminStatus(userAdmin, false);
                response.sendRedirect("admin.jsp?message="+message);
            }
            if (status.equals("0")) {
                message="admin status changed";
                usb.adminStatus(userAdmin, true);
                response.sendRedirect("admin.jsp?message="+message);
            }
        }
        if (statusLock != null) {
            if (statusLock.equals("1")) {
                message="lock status changed";
                usb.lockStatus(userLocked, false);
                response.sendRedirect("admin.jsp?message="+message);
            }
            if (statusLock.equals("0")) {
                message="lock status changed";
                usb.lockStatus(userLocked, true);
                response.sendRedirect("admin.jsp?message="+message);
            }
        }
        if (reset != null) {
            usb.resetPassword(reset);
            message = "password rest";
            response.sendRedirect("admin.jsp?message=" + message);
        }
        if (delete != null) {
            usb.deleteUser(delete);
            message = "User deleted";
            response.sendRedirect("admin.jsp?message=" + message);
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
