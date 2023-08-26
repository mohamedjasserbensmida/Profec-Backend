package com.example.projectperfectus.Service;

import com.example.projectperfectus.Entite.Employee;
import com.example.projectperfectus.Repositorie.EmployeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImp implements EmployeeService{

    @Autowired
    EmployeRepo employeRepo;

    @Override
    public void createEmployee(Employee e) {
      employeRepo.save(e);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeRepo.findAll();
    }

    @Override
    public Employee getOneEmployee(long id) {
        return employeRepo.findById(id).orElse(null);
    }

    @Override
    public Map<String, Object> findAllEmployees(int page, int size) {
        try {
            List<Employee> employeeList =  new ArrayList<>();
            Pageable paging =  PageRequest.of(page, size);
            Page<Employee> pageEmployee;
            pageEmployee = this.employeRepo.findAll(paging);
            employeeList =  pageEmployee.getContent();

            Map<String, Object> employees = new HashMap<>();

            employees.put("employees", employeeList);
            employees.put("pageCurrent", pageEmployee.getNumber());
            employees.put("totalItems", pageEmployee.getTotalElements());
            employees.put("totalPage", pageEmployee.getTotalPages());

            return  employees;

        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        this.employeRepo.deleteById(id);

    }

    @Override
    public Employee update(Employee e, long id) {
        Employee employee =  this.getOneEmployee(id);

        employee.setEmployeePhone(e.getEmployeePhone());
        employee.setEmployeeLastName(e.getEmployeeLastName());
        employee.setDepartement(e.getDepartement());
        employee.setEmployeeFirstName(e.getEmployeeFirstName());

        return this.employeRepo.save(employee);
    }
}
