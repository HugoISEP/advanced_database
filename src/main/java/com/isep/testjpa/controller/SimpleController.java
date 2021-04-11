package com.isep.testjpa.controller;

import com.isep.testjpa.model.Emp;
import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.service.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmpMapper empMapper;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@RequestParam(value = "name", required = false) String name) {
        return "Hello " + name;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value="/employee/{id}")
    public Emp getEmployeeById(@RequestParam("id") Long id) throws Exception {
        return empRepository.findById(id).orElseThrow(() -> new Exception("Not found"));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value="/employee/{id}")
    public void deleteEmployeeById(@RequestParam("id") Long id) {
        empRepository.deleteById(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value="/employee")
    public Emp deleteEmployeeById(@RequestBody Emp updatedEmp) throws Exception {
        Emp emp = empRepository.findById(updatedEmp.getEmpno()).orElseThrow(() -> new Exception("Not found"));
        empMapper.updatedEmp(updatedEmp, emp);
        return empRepository.save(emp);
    }

}

