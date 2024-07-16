package org.digitinary.taskManagement.entities;

import org.digitinary.taskManagement.Exceptions.InvalidEmailException;
import org.digitinary.taskManagement.Exceptions.InvalidIdException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * user class that supports add, remove, update users in a shared(static) collection of users
 */
public class User {


    /**
     * using map as the dataStruct to make remove constant, and to preserve uniqueness based on the id
     */
    public static Map<String, User> usersList = new HashMap<>();//users repo
    private final String id;//for security reasons we dont allow changing the id
    private String name;
    private String email;

    /**
     * initializing the User with id and name only
     * @param id
     * @param name
     */
    public User(String id, String name) throws InvalidIdException{
        boolean isValidId = validateId(id);
        if(!isValidId) throw new InvalidIdException("Invalid Id, id already exists");
        this.id = id;
        this.name = name;
    }

    /**
     * initializing the User with id and name and email(validated against (^.*@.*$))
     * id for the new user must not exist in the usersList repo, even if the created class wont be inserted in the repo
     * @param id
     * @param name
     * @param email
     */
    public User(String id, String name, String email) throws InvalidEmailException, InvalidIdException {
        boolean isValidEmail = validateEmail(email);
        boolean isValidId = validateId(id);
        if(!isValidEmail) throw new InvalidEmailException("invalid email, please follow this format \"^.*@.*$\" ");
        if(!isValidId) throw new InvalidIdException("Invalid Id, id already exists");
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public boolean validateId(String id) {
        return !usersList.containsKey(id);
    }

    public boolean validateEmail(String email){
        String regex = "^.*@.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) throws Exception {
        boolean isValidEmail = validateEmail(email);
        if(!isValidEmail) throw new Exception("invalid email");
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    /**
     * called on a user obj, and it adds the user itself to the static usersList
     * @return true if added , false if already exists
     */
    public boolean addUser() {
        if(usersList.containsKey(this.id)) return false;
        usersList.put(this.getId(), this);
        return true;
    }

    /**
     * called on a user obj, and it removes the user itself from the static usersList
     * @return true if removed , false if doesnt even exist
     */
    public boolean removeUser(){
        if(!usersList.containsKey(this.id)) return false;
        usersList.remove(this.getId());//O(1)
        return true;
    }


    /**
     * updating the existing user(id) to another object
     * updating the repository that saves the objects(users)
     * @param name
     * @param email
     * @throws Exception
     * @return true if updated , false if doesnt even exist
     */
    public boolean updateUser(String name, String email) throws Exception {
        if(!usersList.containsKey(this.getId())) return false;
        //to avoid getting an invalidIdException, we remove curr obj before updating
        usersList.remove(this.getId());
        User updatedUser = new User(this.getId(), name, email);
        usersList.put(this.getId(), updatedUser);
        return true;
    }










}
