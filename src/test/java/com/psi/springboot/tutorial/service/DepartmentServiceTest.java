package com.psi.springboot.tutorial.service;

import com.psi.springboot.tutorial.entity.Department;
import com.psi.springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {

        Department department =
                Department.builder()
                        .departmentId(1L)
                        .departmentName("IT")
                        .departmentAddress("ASD - FE")
                        .departmentCode("IT-06")
                        .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get data based on valid department name.")
//    @Disabled
    public void whenValidDepartmentName_thenDepartmentFound() {
        String departmentName = "IT";
        Department found =
                departmentService.fetchDepartmentByNameIgnoreCase(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

}