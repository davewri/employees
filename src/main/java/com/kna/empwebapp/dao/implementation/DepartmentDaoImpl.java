package com.kna.empwebapp.dao.implementation;

import com.kna.empwebapp.dao.DepartmentDao;
import com.kna.empwebapp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dawright on 03/05/2019
 **/

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    // SQL for selecting Departments
    private final String SELECT_SQL = "SELECT * FROM dbo.Department";
    private final String SELECT_SQL_BY_ID = "SELECT * FROM dbo.Department WHERE DepartmentId = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Implement findAll() which retrieves all Categorys from the DB
    // CategoryMapper() converts rows from the result into Category objects
    public List<Department> findAll() {

        // Use jdbcTemplate to execute query
        // Then use ProductMapper to crate a category object for each row in the query result
        return jdbcTemplate.query(SELECT_SQL, new DepartmentMapper());
    }

    // Return a single Category matching id
    public Department findById(int id) {
        return jdbcTemplate.queryForObject(SELECT_SQL_BY_ID, new Object[]{id}, new DepartmentMapper());
    }
}//class

// This class converts resultset rows (from the sql execution) into Java objects
class DepartmentMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department c = new Department();
        c.setDepartmentId(rs.getInt("DepartmentId"));
        c.setDepartmentName(rs.getString("DepartmentName"));
        c.setDepartmentDescription(rs.getString("DepartmentDescription"));
        return c;
    }
}