/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Api;
import com.exavalu.utils.JDBCConnectionManager;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;



/**
 *
 * @author user
 */
public class ApiService {
    private static HttpURLConnection connection;
    public static ApiService apiService = null;

    public static ApiService getInstance() {
        if (apiService == null) {
            return new ApiService();
        } else {
            return apiService;
        }
    }

    public boolean postData(Api api) throws Exception {
       boolean result = false;
        try{
           Connection con = JDBCConnectionManager.getConnection();
            String sql = "insert into apidb(id,userId,title,completed) values (?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, api.getId());
            preparedStatement.setInt(2, api.getUserId());
            preparedStatement.setString(3, api.getTitle());
            preparedStatement.setString(4, api.isCompleted());
            //System.out.println(preparedStatement);
            
            int row = preparedStatement.executeUpdate();

            if (row>0) {
                result = true;
            }

        } catch (SQLException ex) {
            Logger log=Logger.getLogger(LoginService.class.getName());
               log.error(this.getClass().getName()+" code: "+ ex.getErrorCode() +" message:"+ ex.getMessage());
        }
        return result;

       }
   
}
