package com.thanhtoan.first.idiot.controller;

import java.util.List;

import com.thanhtoan.first.idiot.service.EmployeeService;
import com.thanhtoan.first.idiot.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

@Controller
public class EmployeeRestController {
    // quick and dirty: inject employee Service
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeDAO) {
        employeeService = theEmployeeDAO;
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index.html";
    }

    @GetMapping("/hobbies.html")
    public String viewHobbiePage() {
        return "hobbies.html";
    }

    @GetMapping("/contact-me.html")
    public String viewContactPage() {
        return "contact-me.html";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new Employee());
        return "signup_form.html";
    }
    // // Login form  
    // @RequestMapping("/login.html")  
    // public String login() {  
    //     return "login.html";  
    // }  

    // // Login form with error  
    // @RequestMapping("/login-error.html")  
    // public String loginError(Model model) {  
    //     model.addAttribute("loginError", true);  
    //     return "login.html";  
    // }  

    // expose "/employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //expose /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        
        if (theEmployee == null) {
            throw new RuntimeException("Employee Id not found - " + employeeId);
        }
        return theEmployee;
    }

    //add mapping to add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        //aslo just incase they pass id in JSON... set id to 0

        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee ID not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted ID " + employeeId;
    }
}
