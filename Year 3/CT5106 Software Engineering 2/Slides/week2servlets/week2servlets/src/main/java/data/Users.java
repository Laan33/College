/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author O_Molloy
 */
public class Users
{
    private final List<User> users;

    public Users()
    {
        users = new ArrayList<User>();
        users.add(new User("jm@ebay.uk", "J. Murphy", "jm12345"));
        users.add(new User("arit@disc.com", "Ari", "pr98$%6"));
        users.add(new User("s19@peqx.ie", "Max", "apriori19"));
    }

    public List<User> getUsers()
    {
        return users;
    }
}
