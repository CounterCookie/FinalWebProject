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
public class ChannelSLSB implements ChannelSLSBLocal {

    public void unfollow(int channel, String user) {

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call followDelete(?,?)");
            cs.setInt(1, channel);
            cs.setString(2, user);
            cs.executeQuery();
            cs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void follow(int channel, String user) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call followAdd(?,?)");
            cs.setInt(1, channel);
            cs.setString(2, user);
            cs.executeQuery();
            cs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void channelAdd(String channelName, String user) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call channelAdd(?,?)");
            cs.setString(1, channelName);
            cs.setString(2, user);
            cs.executeQuery();
            cs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void channelDelete(int channelid) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call channelDelete(?)");
            cs.setInt(1, channelid);
            cs.executeQuery();
            cs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ChannelSLSB.class.getName()).log(Level.SEVERE, null, ex);
}
    }

}
