package com.nicholasboari.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
  public static Connection getConnection() {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projetoJDBC", "postgres",
          "190403");
      if (conn != null) {
        System.out.println("Banco de dados conectado!");
        return conn;
      }
      System.out.println("Conexão com o banco falhou!");

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
}
