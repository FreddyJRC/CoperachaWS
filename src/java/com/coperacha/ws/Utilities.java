/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coperacha.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author FJ
 */
public class Utilities {
    public static Connection Connection(){

        Connection connection = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/db.coperacha","postgres","admin");

        } catch (ClassNotFoundException ex) {
            System.out.println("no se econtro la clase " + ex.toString());
        } catch (SQLException ex) {
            System.out.println("no se pudo conectar " + ex.toString());
        }
        
        return connection;
    }
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
