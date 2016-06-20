/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coperacha.ws;

import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FJ
 */
@javax.jws.WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class WebService {
    
    @WebMethod
    public boolean canAccess(String user, String pass){
        
        boolean output = false;
        String query = "SELECT mail_usuario, pass_usuario, estado_usuario FROM public.usuario "+
                        "WHERE mail_usuario = '" + user + "' AND pass_usuario = '" + Utilities.getMD5(pass) + "'";
        
        Connection connection = Utilities.Connection();
        
        try(Statement con = connection.createStatement()) {
            
            ResultSet rs = con.executeQuery(query);
            
            while(rs.next()){
            
                if(rs.getInt("estado_usuario") == 1)
                    output = true;
                
            }
            connection.close();
        } catch (Exception e) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, Utilities.getMD5(pass), e);
        }
        
        return output;
    }
    
    @WebMethod
    public String hola(String name){
        String output;
        
        Connection connection = Utilities.Connection();
        
        if(connection != null){
            output = name + " lo hiciste";
        }else{
            output = name + " fallo";
        }
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(WebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }

}
