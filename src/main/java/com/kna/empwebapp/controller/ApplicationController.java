package com.kna.empwebapp.controller;

import com.kna.empwebapp.dao.DepartmentDao;
import com.kna.empwebapp.dao.EmployeeDao;
import com.kna.empwebapp.model.Department;
import com.kna.empwebapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by dawright on 03/05/2019
 **/

@Controller
public class ApplicationController {

    @Autowired
    private DepartmentDao departmentData;

    @Autowired
    private EmployeeDao employeeData;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@ResponseBody // Send a direct response without a view template
    public String index(@RequestParam(name = "name", required = false, defaultValue = "Enda") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    //@ResponseBody // Send a direct response without a view template
    public String about() {
        //Load and return the about view
        return "about";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String product(@RequestParam(name = "dep", required = false, defaultValue = "0") String dep, Model model){
        List<Employee> employees;

        // Initialise id (default value used to get all employees)
        int id = 0;

        // The parameter may be not be valid - which could crash the application
        // This trys to parse the string converting it to an it
        // If successfull id will be assigned the dep value
        // Otherwise - catch any exception
        // If it fails (i.e an exception occurs) id value will not be changed (from 0).
        try {
            id = Integer.parseInt(dep);
        }
        catch(NumberFormatException e) {
            System.out.println("Bad input for dep id: " + e);
        }

        // If id is 0 then get all products otherwise get products for cat id
        if (id == 0) {
            // Get the product list from the ProductDao instance
            employees = employeeData.findAll();
        } else {
            employees = employeeData.findByDepartment(id);
        }


        // Get the product list from the ProductDao instance
        List<Department> departments = departmentData.findAll();

        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);

        // Return the view
        return "employee";
    }






}//class
