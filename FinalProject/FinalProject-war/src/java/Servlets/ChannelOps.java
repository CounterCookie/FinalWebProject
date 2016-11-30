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
import newBean.ChannelSLSB;

/**
 *
 * @author 695923
 */
public class ChannelOps extends HttpServlet {

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
        HttpSession session = request.getSession();
        String channel = request.getParameter("channel");
        String user = request.getParameter("user");
        String channelID = request.getParameter("channelID");
        String newChannel = request.getParameter("newChannel");
        String message = "";
        ChannelSLSB csb = new ChannelSLSB();
        if (channel != null && user != null) {

            csb.unfollow(Integer.parseInt(channel), user);
            message = "Unfollowed channel";
            response.sendRedirect("main.jsp?message=" + message);
        }

        if (channelID != null) {
            csb.follow(Integer.parseInt(channelID), user);
            message = "followed channel";
            response.sendRedirect("main.jsp?message=" + message);
        }
        if (newChannel != null && !newChannel.equals("")) {
            csb.channelAdd(newChannel, (String) session.getAttribute("user1"));
            message = "Channel added";
            response.sendRedirect("main.jsp?message=" + message);
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
