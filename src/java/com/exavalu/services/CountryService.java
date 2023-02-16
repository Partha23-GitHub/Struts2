/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author anich
 */
public class CountryService {

    public static CountryService countryService = null;

    private CountryService() {
    }

    public static CountryService getInstance() {
        if (countryService == null) {
            return new CountryService();
        } else {
            return countryService;
        }
    }

    public ArrayList getAllCountry() {
        ArrayList countryList = new ArrayList<>();

        String sql = "Select * from country";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            //System.out.println("CountryService :: " + ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));

                country.setCountryName(rs.getString("countryName"));

                countryList.add(country);
            }

        } catch (SQLException ex) {
            Logger log=Logger.getLogger(LoginService.class.getName());
               log.error(this.getClass().getName()+" code: "+ ex.getErrorCode() +" message:"+ ex.getMessage());
        }

        return countryList;
    }

}

