/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author 695923
 */
public class AdminPageHelper {
    public String displayTable() {
        String resulttable = "";
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            //Using Statement
            String sql = "call usergetalldata()";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                resulttable += "<tr>";
                resulttable += "<td>" + rs.getString(1) + "</td>";
                if(rs.getInt(3)==1){
                    resulttable += "<td><a href='adminToggle?status="+rs.getInt(3)+"&&userAdmin="+rs.getString(1)+"'>Admin</a></td>";
                }
                else{
                    resulttable += "<td><a href='adminToggle?status="+rs.getInt(3)+"'>Normal</a></td>";
                }
                
                if(rs.getInt(4)==1){
                    resulttable += "<td><a href='UserOps2?status="+rs.getInt(4)+"'>Yes</a></td>";
                } else{
                    resulttable += "<td><a href='UserOps2?status="+rs.getInt(4)+"'>No</a></td>";
                }
                resulttable+="<td><a href='UserOps2?user="+rs.getString(1)+"'>Reset</a></td>";
                resulttable+="<td><a href='UserOps2?delete="+rs.getString(1)+"'>Delete</a></td>";
                
                 resulttable += "</tr>";
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
}
