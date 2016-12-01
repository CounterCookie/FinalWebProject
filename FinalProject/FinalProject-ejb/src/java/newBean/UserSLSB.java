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
            if(rs.next()){
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
    
    public void addUser(String user, String pass, int admin, int lock){
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
    public void adminStatus(String user, boolean type){
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
    
    public void lockStatus(String user, boolean type){
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
    
    public void resetPassword(String user){
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
    
    
    public void deleteUser(String user){
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
}
