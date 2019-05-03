package com.kna.empwebapp.model;

/**
 * Created by dawright on 03/05/2019
 **/

public class Employee {
    private int EmployeeId, DepartmentId, StartYear;
    private String EmployeeName, EmpAddress, Email,Telephone, Qualification, Experience;

    //Contrsuctor methods
    public Employee() {

    }

    public Employee(int employeeId, int departmentId, int startYear, String employeeName, String empAddress, String email, String telephone, String qualification, String experience) {
        EmployeeId = employeeId;
        DepartmentId = departmentId;
        StartYear = startYear;
        EmployeeName = employeeName;
        EmpAddress = empAddress;
        Email = email;
        Telephone = telephone;
        Qualification = qualification;
        Experience = experience;
    }

    //Getter and Setter methods
    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public int getStartYear() {
        return StartYear;
    }

    public void setStartYear(int startYear) {
        StartYear = startYear;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getEmpAddress() {
        return EmpAddress;
    }

    public void setEmpAddress(String empAddress) {
        EmpAddress = empAddress;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }
}//class
