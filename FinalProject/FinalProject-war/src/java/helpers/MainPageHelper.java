/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author 695923
 */
public class MainPageHelper {

    public String displayTable(String user) {
        String resulttable = "";
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            //Using Statement
            String sql = "call channelGetFollowing('" + user + "')";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ResultSet rs1 = null;
            Date date = null;
            Time time = null;
            while (rs.next()) {
                int channelid = rs.getInt(1);
                resulttable += "<tr>";
                resulttable += "<td>" + rs.getString(2) + "</td>";
                resulttable += "<td>" + rs.getString(3) + "</td>";
                String sql1 = "call channelLastTwitDateTime('" + rs.getInt(1) + "')";
                rs1 = st.executeQuery(sql1);
                if (rs1 != null && rs1.next()) {
                    date = rs1.getDate(1);
                    time = rs1.getTime(1);
                }
                resulttable += "<td>" + date + " " + time + "</td>";
                resulttable += "<td><a href='ChannelOps?channel=" + channelid + "&&user=" + user + "'>Unfollow</a></td>";
                resulttable += "</tr>";
                rs1.close();
            }
            st.close();
            rs.close();

            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resulttable;
    }

    public String displayFind(String channelName, String user, String user1) {
        String resulttable = "<table border='1'>";
        try {
            resulttable += "<th>Channel Name</th><th>Username</th><th>Follow</th>";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            //Using Statement
            String sql = "call channelFind('" + channelName + "','" + user + "')";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resulttable += "<tr>";
                resulttable += "<td>" + rs.getString(2) + "</td>";
                resulttable += "<td>" + rs.getString(3) + "</td>";
                resulttable += "<td><a href='ChannelOps?channelID="+rs.getInt(1)+"&&user="+user1+"'>follow</a></td>";
                resulttable += "</tr>";
            }
            resulttable += "</table>";
            st.close();
            rs.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainPageHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(resulttable);

        return resulttable;
    }
    public String displayOwnedChanell(String user){
        
        return null;
    }
}
