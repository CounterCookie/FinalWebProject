/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newBean;

import javax.ejb.Local;

/**
 *
 * @author 695923
 */
@Local
public interface UserSLSBLocal{
    boolean validateUser(String user, String pass);
    boolean userAdmin(String user);
    void addUser(String user, String pass, int admin, int lock);
    void adminStatus(String user, boolean type);
    void lockStatus(String user, boolean type);
    void resetPassword(String user);
    void deleteUser(String user);
}
