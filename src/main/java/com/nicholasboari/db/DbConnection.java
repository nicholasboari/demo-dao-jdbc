package com.nicholasboari.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  public static void closeStatement(Statement st) {
    if (st != null) {
      try {
        st.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }

  public static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        throw new DbException(e.getMessage());
      }
    }
  }
}
