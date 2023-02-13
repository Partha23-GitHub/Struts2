/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.CountryService;
import com.exavalu.services.DepartmentService;
import com.exavalu.services.DistrictService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.RoleService;
import com.exavalu.services.StateService;
import com.exavalu.utils.JDBCUtility;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author anich
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    /**
     * @param args the command line arguments
     */
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String countryCode;
    private String stateCode;
    private String districtCode;
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public ApplicationMap getMap() {
        return map;
    }

    public void setMap(ApplicationMap map) {
        this.map = map;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailAddess
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddess the emailAddess to set
     */
    public void setEmailAddress(String emailAddess) {
        this.emailAddress = emailAddess;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);

        if (success) {
            System.out.println("returning Success from doLogin method");
            ArrayList emp = EmployeeService.getInstance().getAllEmployees();
            ArrayList deptList = DepartmentService.getInstance().getAllDepartment();
            ArrayList roleList = RoleService.getInstance().getAllRole();
            ArrayList countries=CountryService.getInstance().getAllCountry();
            result = "SUCCESS";
            sessionMap.put("Loggedin", this);
            sessionMap.put("EmpList", emp);
            sessionMap.put("DeptList", deptList);
            sessionMap.put("RoleList", roleList);
            sessionMap.put("Countries", countries);
        }

        return result;
    }

    public String doSignUp() throws Exception {
        String result = "FAILURE";
        boolean success = LoginService.getInstance().doSignUp(this);

        if (success) {
            result = "SUCCESS";
        }
        return result;

    }

    public String doLogout() throws Exception {
        String result = "FAILURE";
        sessionMap.clear();
        if (sessionMap.isEmpty()) {
            result = "SUCCESS";
        }
        return result;

    }
    
    public String doPreSignUp() throws Exception {
        String result = "SUCCESS";
        ArrayList countryList = CountryService.getInstance().getAllCountry();
        sessionMap.put("CountryList", countryList);
        sessionMap.put("emp", this);
        if (this.countryCode != null) {
            System.out.println(this.countryCode);
            ArrayList stateList = StateService.getInstance().getAllState(this.countryCode);
            sessionMap.put("ProvinceList", stateList);
            result="STATELIST";
        }
        if (this.stateCode != null) {
            System.out.println(this.stateCode);
            ArrayList distList = DistrictService.getInstance().getAllDistrict(this.stateCode);
            sessionMap.put("DistrictList", distList);
            result="DISTRICTLIST";
        }
        if(LoginService.getInstance().cheackDuplicate(this)) {
                sessionMap.put("FailSignUp", "Email All Ready Exsists");
                result="FAILURE";
                return result;
            }
        if (this.firstName != null && this.firstName.length()>0 && this.lastName != null && this.lastName.length()>0 && this.emailAddress != null && this.emailAddress.length()>0 && this.password!= null && this.password.length()>0 &&this.countryCode.length()>0&&this.stateCode.length()>0 && this.districtCode.length()>0) {
            System.out.println("in if");
            boolean success = LoginService.getInstance().doSignUp(this);

            if (success) {
                result = "DONE";
                sessionMap.put("SuccessSignUp", "Successfully Registered");

            } 
            //System.out.println(sessionMap);
            return result;
        }
        

        //System.out.println(sessionMap);
        return result;

    }
    
    public String apiCall() throws Exception {
        String result = "SUCCESS";
        JDBCUtility jdbcUtility = JDBCUtility.getInstanceOfJDBCUtility();
        String url = jdbcUtility.getPropertyValue("apiUrl");
        
        HttpRequest request=HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client=HttpClient.newBuilder().build();
        HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + response.statusCode());
        System.out.println("Data:"+response.body());
        
         
        return result;

    }

}
