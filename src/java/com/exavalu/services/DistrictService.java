/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.District;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author anich
 */
public class DistrictService {

    public static DistrictService districtService = null;

    private DistrictService() {
    }

    public static DistrictService getInstance() {
        if (districtService == null) {
            return new DistrictService();
        } else {
            return districtService;
        }
    }

    public ArrayList getAllDistrict(String code) {
        ArrayList districtList = new ArrayList<>();

        String sql = "Select * from district where stateCode=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code);

            System.out.println("DistrictService :: " + ps);
            System.out.println(code);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                District district = new District();
                district.setStateCode(rs.getString("stateCode"));

                district.setDistrictCode(rs.getString("districtCode"));
                district.setDistrict(rs.getString("district"));
                districtList.add(district);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return districtList;
    }

}
