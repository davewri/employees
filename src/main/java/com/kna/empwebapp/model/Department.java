package com.kna.empwebapp.model;

/**
 * Created by dawright on 03/05/2019
 **/
public class Department {
    private int DepartmentId;
    private String DepartmentName, DepartmentDescription;

    // Constructor Methods
    public Department() {

    }

    public Department(int departmentId, String departmentName, String departmentDescription) {
        DepartmentId = departmentId;
        DepartmentName = departmentName;
        DepartmentDescription = departmentDescription;
    }

    //Getter and Setter methods
    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getDepartmentDescription() {
        return DepartmentDescription;
    }

    public void setDepartmentDescription(String departmentDescription) {
        DepartmentDescription = departmentDescription;
    }
}//class
