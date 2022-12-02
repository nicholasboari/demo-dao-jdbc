package com.nicholasboari.app;

import java.util.Date;
import java.util.List;

import com.nicholasboari.model.dao.DaoFactory;
import com.nicholasboari.model.dao.SellerDao;
import com.nicholasboari.model.entities.Department;
import com.nicholasboari.model.entities.Seller;

public class Program {
  public static void main(String[] args) {

    SellerDao sellerDao = DaoFactory.creatSellerDao();

    System.out.println("=== TEST 1: seller findById ===");
    Seller seller = sellerDao.findById(5);
    System.out.println(seller);

    System.out.println("\n=== TEST 2: seller findByDepartment ===");
    Department department = new Department(2, null);
    List<Seller> sellers = sellerDao.findByDepartment(department);
    for (Seller s : sellers) {
      System.out.println(s);
    }

    System.out.println("\n=== TEST 3: seller findAll ===");
    sellers = sellerDao.findAll();
    for (Seller s : sellers) {
      System.out.println(s);
    }

    System.out.println("\n=== TEST 5: seller insert ===");
    Seller newSeller = new Seller(null, "Matheus Boari", "matheus@boari", new Date(), 8000.0, department);
    System.out.println(newSeller.getId());
    sellerDao.insert(newSeller);
    System.out.println("Inserted! New id = " + newSeller.getId());
  }
}
