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
public class TwitSLSB implements TwitSLSBLocal {

    @Override
    public void addTwit(int channel, String twit){
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc/twitsdbPool");
            Connection conn = ds.getConnection();

            CallableStatement cs = conn.prepareCall("call twitAdd(?,?)");
            cs.setInt(1, channel);
            cs.setString(2, twit);
            cs.executeQuery();
            cs.close();
            conn.close();
        } catch (NamingException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserSLSB.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
}
