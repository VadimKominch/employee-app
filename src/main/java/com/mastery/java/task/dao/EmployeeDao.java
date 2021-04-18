package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Component
public class EmployeeDao {
    private JdbcTemplate template;

    public EmployeeDao(DataSource source) {
        this.template = new JdbcTemplate(source);
    }

    private RowMapper<Employee> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> new Employee(
            resultSet.getLong("employee_id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("job_title"),
            Gender.getGender(resultSet.getString("gender")),
            resultSet.getDate("date_of_birth"),
            resultSet.getInt("department_id"));

    public List<Employee> getAll() {
        List<Employee> employees = template.query("select * from employee ", ROW_MAPPER);
        return employees;
    }


    public Employee getById(Long id) {

        return template.queryForObject("select * from employee where employee_id = ?", (resultSet, rowNum) ->
                new Employee(
                        resultSet.getLong("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("job_title"),
                        Gender.getGender(resultSet.getString("gender")),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getInt("department_id")), new Object[]{id});
    }


    public void save(Employee entity) {
        String sql = "insert into employee(first_name,last_name,job_title,gender,date_of_birth,department_id) values (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            PreparedStatement pst =
                    con.prepareStatement(sql, new String[]{"employee_id"});
                    pst.setString(1,entity.getFirstName());
                    pst.setString(2,entity.getLastName());
                    pst.setString(3,entity.getJobTitle());
                    pst.setString(4,entity.getGender().getValue());
                    pst.setDate(5, new java.sql.Date(entity.getDateOfBirth().getTime()));
                    pst.setInt(6,entity.getDepartment());
            return pst;
        },keyHolder);
    }


    public void delete(Long id) {
        template.update("delete from employee where employee_id = ?",id);
    }


    public Employee update(Long id, Employee newObj) {
        template.update("update employee set first_name = ?,last_name = ?,job_title=?,gender = ?,date_of_birth = ?,department_id = ? where employee_id= ?", newObj.getFirstName(), newObj.getLastName(), newObj.getJobTitle(), newObj.getGender().name(), newObj.getDateOfBirth(),newObj.getDepartment(), id);
        return newObj;
    }
}
