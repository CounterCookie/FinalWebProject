/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author 695923
 */
@Stateless
public class UserSLSB implements UserSLSBLocal {

    public boolean validateUser(String user, String pass) {
        boolean status = false;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call userValidate(?,?)");
            cs.setString(1, user);
            cs.setString(2, pass);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                return rs.getBoolean(1);
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean userAdmin(String user) {
        boolean status = false;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call userGetType(?)");
            cs.setString(1, user);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    rs.close();
                    conn.close();
                    return true;
                }
            }
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public void addUser(String user, String pass, int admin, int lock) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call userAdd(?,?,?,?)");
            cs.setString(1, user);
            cs.setString(2, pass);
            cs.setInt(3, admin);
            cs.setInt(4, lock);
            ResultSet rs = cs.executeQuery();
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adminStatus(String user, boolean type) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();
            System.out.println(type);
            CallableStatement cs = conn.prepareCall("call userSetType(?,?)");
            cs.setString(1, user);
            cs.setBoolean(2, type);
            ResultSet rs = cs.executeQuery();
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lockStatus(String user, boolean type) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();
            System.out.println(type);
            CallableStatement cs = conn.prepareCall("call userSetLocked(?,?)");
            cs.setString(1, user);
            cs.setBoolean(2, type);
            ResultSet rs = cs.executeQuery();
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetPassword(String user) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();
            CallableStatement cs = conn.prepareCall("call userResetPassword(?)");
            cs.setString(1, user);
            ResultSet rs = cs.executeQuery();
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(String user) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();
            CallableStatement cs = conn.prepareCall("call userDelete(?)");
            cs.setString(1, user);
            ResultSet rs = cs.executeQuery();
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean userLocked(String user) {
        boolean status = false;
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call userGetLocked(?)");
            cs.setString(1, user);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    rs.close();
                    conn.close();
                    return true;
                }
            }
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public String showSuccess() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.registered').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.registered').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.registered').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.registered').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }

    public String showFail() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.failRegister').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.failRegister').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.failRegister').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.failRegister').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }

    public boolean checkExist(String user) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call userExists(?)");
            cs.setString(1, user);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                if (rs.getInt(1) == 1) {
                    rs.close();
                    conn.close();
                    return true;
                }
            }
            rs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String showLogout() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.logout').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.logout').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.logout').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.logout').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }

    public String showLock() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.locked').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.locked').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.locked').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.locked').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }

    public String showInvalid() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.invalidUser').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.invalidUser').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.invalidUser').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.invalidUser').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }
    //loginFields

    public String showLogin() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.loginFields').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.loginFields').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.loginFields').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.loginFields').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }
    
    public String loginError() {
        String alert = "";
        alert += "  <script>      "
                + " $('.notification.loginError').removeClass('bounceOutRight notification-show animated bounceInRight');\n"
                + "                        // show notification\n"
                + "                        $('.notification.loginError').addClass('notification-show animated bounceInRight');\n"
                + "\n"
                + "                        $('.notification.loginError').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {\n"
                + "                            setTimeout(function () {\n"
                + "                                $('.notification.loginError').addClass('animated bounceOutRight');\n"
                + "                            }, 2000);\n"
                + "                        });\n"
                + "</script>";
        return alert;
    }
}
