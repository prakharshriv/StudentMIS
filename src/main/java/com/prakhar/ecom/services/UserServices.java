package com.prakhar.ecom.services;

import com.prakhar.ecom.exceptions.UserNotFound;
import com.prakhar.ecom.models.User;
import com.prakhar.ecom.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    UserRepository db;
    private static final Logger logger= LoggerFactory.getLogger(UserServices.class);

    //add user
    public User addUser(User user) {
        try {
            user.setId(user.idGenerator());
            return db.save(user);
        } catch (Exception e) {
            //logger.error("cause==>"+e.getCause()+"/n message==>"+e.getMessage());
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    //list users

    public List<User> getAllUsers(){
        List<User> users=new ArrayList<>();

        try {
        users=db.findAll();

        } catch (Exception e) {
            logger.error(e.getMessage(),e);

        }
        return users;
    }
    //get user by id
    public User getUserById(String id) {
        try {
            return db.getById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    // update user
    public User updateUserById(User user)throws UserNotFound {
        try {
            if(user.getId()==null)
                throw new Exception("error! no id provided");
            if(getUserById(user.getId())==null)
                throw new UserNotFound();
            return db.save(user);

        }catch (UserNotFound unf){
            logger.error(unf.getMessage(),unf);
            throw unf;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);

            return null;
        }
    }
    // delete user
    public Boolean deleteUserById(String id)throws UserNotFound {
        try {
            if(id==null)
                throw new Exception("error! no id provided");
            if(getUserById(id)==null)
                throw new UserNotFound();
            db.deleteById(id);
            return true;
        }catch (UserNotFound unf){
            throw unf;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    // login user
    //logout user
    // register as admin

}
