/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {
    
    public static LoginService loginService = null;
    
    private LoginService(){}
    
    public static LoginService getInstance()
    {
        if(loginService==null)
        {
            return new LoginService();
        }
        else
        {
            return loginService;
        }
    }
    
    public boolean doLogin(User user)
    {
        boolean success = false;
        
        String sql = "Select * from users where emailAddress=? and password=?";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress() );
            ps.setString(2, user.getPassword());
            
            System.out.println("LoginService :: "+ps);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return success;
    }

    public boolean doSignUp(User user) {
        boolean result=false;
        String sql ="INSERT INTO users(emailAddress,password,firstName,lastName,countryCode,stateCode,districtCode)" + "VALUES(? ,? ,? ,?,?,?,?)";
 

        try {
            Connection con = JDBCConnectionManager.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCountryCode() );
            preparedStatement.setString(6, user.getStateCode() );
            preparedStatement.setString(7, user.getDistrictCode() );
            System.out.println(preparedStatement);
            int res=preparedStatement.executeUpdate();
            
            if(res==1)
            {
                result=true;
                System.out.println("in dosignup");
            }



            con.close();

 


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return result;
        
    }
    public boolean cheackDuplicate(User user)
    {
        boolean success = false;
        
        String sql = "Select * from users where emailAddress=?";
        
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress() );
            
            //System.out.println("LoginService :: "+ps);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                success = true;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return success;
    }
//    public boolean doSignUpAll(ArrayList userList) {
//        boolean result = true;
//        for(int i=0;i<userList.size();i++)
//        {
//            if(!doSignUp((User)userList.get(i)))
//            {
//                result=false;
//            }
//        }
//        return result;
//    }
}

