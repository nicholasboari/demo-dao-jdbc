package com.nicholasboari.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.db.DbException;
import com.nicholasboari.model.dao.DepartmentDao;
import com.nicholasboari.model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

  private Connection conn = null;
  PreparedStatement st = null;
  ResultSet rs = null;

  public DepartmentDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Department findById(Integer id) {
    try {
      st = conn.prepareStatement("SELECT department.* FROM department WHERE department.Id = ?");

      st.setInt(1, id);
      rs = st.executeQuery();

      if (rs.next()) {
        Department obj = instantiateDepartment(rs);
        return obj;
      } else {
        throw new DbException("Id not found!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
      DbConnection.closeResultSet(rs);
    }
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    Department department = new Department(rs.getInt("Id"), rs.getString("Name"));
    return department;
  }

  @Override
  public List<Department> findAll() {
    List<Department> departments = new ArrayList<>();
    try {
      st = conn.prepareStatement("SELECT * FROM department");
      rs = st.executeQuery();

      while (rs.next()) {
        Department obj = instantiateDepartment(rs);
        departments.add(obj);
      }

      return departments;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
      DbConnection.closeResultSet(rs);
    }
  }

  @Override
  public void insert(Department obj) {
    try {
      st = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

      st.setString(1, obj.getName());
      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt("Id");
          obj.setId(id);
        }
      } else {
        throw new DbException("Unexpected error! No rows affected!");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
    }
  }

  @Override
  public void update(Department obj) {
    try {
      st = conn
          .prepareStatement(
              "UPDATE department SET Name = (?) WHERE Id = (?)");
      st.setString(1, obj.getName());
      st.setInt(2, obj.getId());
      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
    }
  }

  @Override
  public void deleteById(Integer id) {
    try {
      st = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
      st.setInt(1, id);

      st.executeUpdate();
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
    }
  }
}
