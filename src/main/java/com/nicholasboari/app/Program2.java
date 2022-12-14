package com.nicholasboari.app;

import java.util.List;
import java.util.Scanner;

import com.nicholasboari.model.dao.DaoFactory;
import com.nicholasboari.model.dao.DepartmentDao;
import com.nicholasboari.model.entities.Department;

public class Program2 {
  public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    System.out.println("=== TEST 1: department findById ===");
    Department department = departmentDao.findById(1);
    System.out.println(department);

    System.out.println("=== TEST 2: department findAll ===");
    List<Department> departments = departmentDao.findAll();
    for (Department d : departments) {
      System.out.println(d);
    }

    System.out.println("=== TEST 3: department insert ===");
    Department newDepartment = new Department(null, "TI");
    departmentDao.insert(newDepartment);
    System.out.println("Inserted! New id = " + newDepartment.getId());

    System.out.println("=== TEST 4: department update ===");
    department = departmentDao.findById(5);
    department.setName("TI2.0");
    departmentDao.update(department);
    System.out.println("Updated!");

    System.out.println("=== TEST 5: department delete ===");
    System.out.println("Enter id for delete test: ");
    int id = scan.nextInt();
    departmentDao.deleteById(id);
    System.out.println("Deleted!");

    scan.close();
  }
}
