package com.codingshuttle.dataMapping.contollers;

import com.codingshuttle.dataMapping.entities.DepartmentEntity;
import com.codingshuttle.dataMapping.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{departmentId}")
    public DepartmentEntity getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }

    @PostMapping
    public DepartmentEntity createNewDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.createNewDepartment(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
    public DepartmentEntity assignManagerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId) {
        return departmentService.assignManagerToDepartment(departmentId, employeeId);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public DepartmentEntity getAssignManagerToDepartment(@PathVariable Long employeeId) {
        return departmentService.getAssignManagerToDepartment(employeeId);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public DepartmentEntity assignWorkerToDepartment(@PathVariable Long departmentId,
                                                      @PathVariable Long employeeId) {
        return departmentService.assignWorkerToDepartment(departmentId, employeeId);
    }

    @PutMapping(path = "/{departmentId}/freelancers/{employeeId}")
    public DepartmentEntity assignFreeLancerToDepartment(@PathVariable Long departmentId,
                                                     @PathVariable Long employeeId) {
        return departmentService.assignFreeLancerToDepartment(departmentId, employeeId);
    }
}
