/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author Rasheed
 */
public class Session {
   
    private static int userId;
    private static String username;


    public static void createSession(int id, String uName) {
        
        userId = id;
        username = uName;
       
    }

    public static String getUname() {
        return username;
    }

    public static int getUserId() {
        return userId;
    }

    public static void clearSession() {
        userId = 0;
        username = null;
        
    }
}

