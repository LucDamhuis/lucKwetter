/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.UserDAO;
import domain.Role;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Luc
 */
@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public UserService() {

    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User getByUId(long id) {
        return userDAO.getWithId(id);
    }

    public User getByUsername(String username) {
        return userDAO.getByUsername(username);
    }

    public User getByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    public User create(User u) {
        if (userDAO.getByEmail(u.getEmail()) == null && userDAO.getByUsername(u.getUsername()) == null) {
            return userDAO.create(u);
        }
        return null;
    }

    public void update(User u) {
        User user = userDAO.getWithId(u.getId());
        userDAO.update(user);
    }

    public void delete(long id) {
        User u = userDAO.getWithId(id);
        userDAO.delete(u.getId());
    }

    public void updateUname(String uname, User user) {
        if (userDAO.getByUsername(uname) == null && !uname.isEmpty()) {
            user.setUsername(uname);
            userDAO.update(user);
        }
    }

    public void updateRole(Role role, User user) {
        user.setRole(role);
        userDAO.update(user);
    }

    public void addFollowee(long followUId, long ownId) { 
        if (followUId != ownId) {
            User followee = userDAO.getWithId(followUId);
            User user = userDAO.getWithId(ownId);
            if(user!=null&&followee!=null){
                user.addFollowingUser(followee);
                userDAO.update(user);
            }
        }
    }
    public void remmoveFollowee(long followUId, long ownId) {
        if(followUId!=ownId){
            User followee = userDAO.getWithId(followUId);
            User user = userDAO.getWithId(ownId);
            if(user!=null&&followee!=null){
                user.removeFollowingUser(followee);
                userDAO.update(user);
            }
        }
    }

}
