package com.kna.empwebapp.dao;

import com.kna.empwebapp.model.Employee;
import java.util.List;

/**
 * Created by dawright on 03/05/2019
 **/
public interface EmployeeDao {
    // Return a list containing all the employee object
    public List<Employee> findAll();

    // Return a product with matching id
    public Employee findById(int id);

    // return a list of products in a category
    public List<Employee> findByDepartment(int id);

    //Updating an existing product - return the number of rows affected
    public int update(final Employee employee);

    public Employee create(final Employee employee);

    // Delete product with matching id
    public int delete(int id);

    // return a list of products matching search term
    public List<Employee> findBySearchText(String searchText);

}//class
