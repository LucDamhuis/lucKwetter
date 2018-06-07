/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.KweetDAO;
import DAO.UserDAO;
import domain.Kweet;
import domain.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Luc
 */
@Stateless
public class KweetService {
    
    @Inject
    KweetDAO kweetDAO;
    
    @Inject
    UserDAO userDAO;
    
    public KweetService()
    {
        
    }
    public List<Kweet> getAll(){
        return  kweetDAO.getAll();
    }
    public Kweet create(Kweet k) {
        User u = userDAO.getWithId(k.getOwner().getId()); 
        if(u!=null){
            k = kweetDAO.create(k);
            u.addKweet(k);
            userDAO.update(u);
            return k;
        } 
        return null;
    }
    
    public Kweet getById(long id){
        return kweetDAO.getWithId(id);
    }
    
    public List<Kweet> getKweetsByUId(long id){
        return kweetDAO.getKweetsByUId(id);
    }
    
    public List<Kweet> getByText(String arg){
        return kweetDAO.getByText(arg);
    }

    public void delete(long id, User loggedinUser) {
        Kweet k = kweetDAO.getWithId(id);
        if(k!=null){
            if(k.getOwner().getId().equals(loggedinUser.getId())){
                kweetDAO.delete(k);
            }
        }
    }
    
    
}
