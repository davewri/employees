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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // The newEmployeepage will be accessed using /updateEmployee from the browser
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.GET)
    public String updateEmployee(@RequestParam(name = "id", required = true) String pId, Model model) {
        Employee employee;
        // Initialise id (default value used to get all employees)
        int id = 0;
        // The parameter may be not be valid - which could crash the application // This trys to parse the string converting it to an it
        // If successfull id will be assigned the dep value
        // Otherwise - catch any exception
        // If it fails (i.e an exception occurs) id value will not be changed (from 0).
        try {
            id = Integer.parseInt(pId);
        }
        catch(NumberFormatException e) {
            System.out.println("Bad input for id: " + e); }
        // If id is 0 then get all employees otherwise get employees for dep id
        if (id == 0) {
            // employee id=0 does not exist - return to employee list
            return "redirect:/employee";
        } else {
            // Otherwise find the employee matching the id employee = employeeData.findById(id);
            employee = employeeData.findById(id);
        }
        // add product to the model
        model.addAttribute("employee", employee);
        // Get a list of departments and add to the model
        List<Department> departments = departmentData.findAll();
        model.addAttribute("departments", departments);
        // Return the updateEmployee view

        return "updateEmployee";
    }

    // Handle form submit via HTTP POST
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST) // Form data will be supplied as a filled in Employee object
    public String editEmployee(Employee employee) {
        // Use the Dao to update the employee
        // To do: check for errors and return to form if any found
        // https://www.journaldev.com/2668/spring-validation-example-mvc-validator
        int rows = employeeData.update(employee);
        // output result in server side console
        System.out.println(rows + " rows were updated");
        // Redirect back to the employees list
        return "redirect:/employee";
    }

    // The newEmployee page will be accessed using /newEmployee from the browser
    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public String newEmployee(Model model) {

        // add empty Product to the model
        model.addAttribute("employee", new Employee());

        // Get a list of departments and add to the model
        List<Department> departments = departmentData.findAll();
        model.addAttribute("departments", departments);

        // Return the view
        return "newEmployee";
    }

    // Handle form submit via HTTP POST
    @RequestMapping(value = "/newEmployee", method = RequestMethod.POST)
    // Form data will be supplied as a filled in Employee object
    public String createEmployee(Employee employee, RedirectAttributes redirAttrs) {

        // Use the Dao to create the new product
        // To do: check for errors and return to form if any found
        // https://www.journaldev.com/2668/spring-validation-example-mvc-validator
        Employee newEmployee = employeeData.create(employee);

        if (newEmployee != null) {
            redirAttrs.addFlashAttribute("message", "New employee added - id: " + newEmployee.getEmployeeId());
        }
        else {
            redirAttrs.addFlashAttribute("error", "error: employee not added");
        }
        // Redirect back to the employees list
        // To do: Open a page showing the new employee in its own page
        return "redirect:/employee";
    }

    // Delete Employee
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
    public String deleteEmployee(@RequestParam(name = "id", required = true) String pId, RedirectAttributes redirAttrs) {

        // Initialise id (default value used to get all employees)
        int id = 0;

        // The parameter may be not be valid - which could crash the application
        // This trys to parse the string converting it to an it
        // If successfull id will be assigned the cat value
        // Otherwise - catch any exception
        // If it fails (i.e an exception occurs) id value will not be changed (from 0).
        try {
            id = Integer.parseInt(pId);
        }
        catch(NumberFormatException e) {
            System.out.println("Bad input for id: " + e);
        }
        // If id value is greater than 0 then delete - otherwise error
        if (id != 0) {
            int rows = employeeData.delete(id);

            // Verify that something was deleted (rows affected > 1)
            if (rows >= 1) {
                // Set a flash message confirming the delete
                redirAttrs.addFlashAttribute("message", rows + " rows deleted");
            }
            else  {
                // Nothing deleted - set error flash message
                redirAttrs.addFlashAttribute("error", "Error: Employee delete failed");
            }
        }
        else {
            // can't delete id = 0 - show error
            redirAttrs.addFlashAttribute("error", "Nothing to delete");
        }

        // Return to products page
        return "redirect:/employee";
    }

    // This method displays the employee page
    @RequestMapping(value = "/searchEmployees", method = RequestMethod.GET)
    // Uses a Model instance - which will be passed to a view
    // cat parameter is for department id
    public String searchEmployee(@RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) {

        // If search is blank then redirect to the employees page
        if (search == "") {
            return "redirect:/employees";
        }

        // Do the search and get the results
        List<Employee> employee = employeeData.findBySearchText(search);

        // Get all depatments
        List<Department> departments = departmentData.findAll();

        model.addAttribute("employees", employee);
        model.addAttribute("departments", departments);

        // Return the view
        return "employee";
    }


}//class
