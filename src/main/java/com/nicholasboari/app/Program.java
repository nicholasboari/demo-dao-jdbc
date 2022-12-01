package com.nicholasboari.app;

import java.sql.Connection;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.model.entities.Department;

public class Program {
  public static void main(String[] args) {

    Connection conn = DbConnection.getConnection();
    Department obj = new Department(1, "Books");
    System.out.println(obj);
  }
}
