package com.kna.empwebapp.dao;

import com.kna.empwebapp.model.Department;
import java.util.List;

/**
 * Created by dawright on 03/05/2019
 **/
public interface DepartmentDao {
    // Return a list containing all the product objects
    public List<Department> findAll();

    // Return a product with matching id
    public Department findById(int id);

}//class
