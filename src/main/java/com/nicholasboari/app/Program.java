package com.nicholasboari.app;

import java.sql.Connection;
import java.util.Date;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.model.dao.DaoFactory;
import com.nicholasboari.model.dao.SellerDao;
import com.nicholasboari.model.entities.Department;
import com.nicholasboari.model.entities.Seller;

public class Program {
  public static void main(String[] args) {

    Connection conn = DbConnection.getConnection();
    Department obj = new Department(1, "Books");
    Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
    SellerDao sellerDao = DaoFactory.creatSellerDao();
    System.out.println(seller);
  }
}
