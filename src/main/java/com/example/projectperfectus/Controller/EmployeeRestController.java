package com.example.projectperfectus.Controller;

import com.example.projectperfectus.Entite.Departement;
import com.example.projectperfectus.Entite.Employee;
import com.example.projectperfectus.Models.EmployeeModel;
import com.example.projectperfectus.Service.DepartementService;
import com.example.projectperfectus.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartementService departementService;


    @PostMapping("/employees")
    void createEmployee(@RequestBody EmployeeModel e){
        Employee emp = new Employee();
        emp.setEmployeeFirstName(e.getEmployeeFirstName());
        emp.setEmployeeLastName((e.getEmployeeLastName()));
        emp.setEmployeePhone(e.getEmployeePhoneNumber());
        emp.setDepartement(this.departementService.findOneDepartment(e.getDepartmentId()));
        this.employeeService.createEmployee(emp);
    }

    @GetMapping("/employees")
    List<Employee> getEmployees(){
       return this.employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    Employee getOneEmployee(@PathVariable long id){
    return  this.employeeService.getOneEmployee(id);
    }


    @GetMapping("/employees/paging")
    public ResponseEntity<Map<String, Object>> findAllByPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        return new ResponseEntity<>(this.employeeService.findAllEmployees(page, size), HttpStatus.OK);
    }


    @DeleteMapping("/employees/delete/{id}")
    public void deleteEmployee(@PathVariable  long id) {
        this.employeeService.delete(id);
    }

    @PutMapping("/employees/update/{id}")
    public  Employee updateEmployee(@RequestBody EmployeeModel e, @PathVariable long id) {
        Employee employee = new Employee();
        employee.setEmployeeFirstName(e.getEmployeeFirstName());
        employee.setEmployeePhone(e.getEmployeePhoneNumber());
        employee.setEmployeeLastName(e.getEmployeeLastName());
        employee.setDepartement(this.departementService.findOneDepartment(e.getDepartmentId()));

        return this.employeeService.update(employee, id);
    }
}
