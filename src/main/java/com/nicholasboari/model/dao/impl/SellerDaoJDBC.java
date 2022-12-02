package com.nicholasboari.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.db.DbException;
import com.nicholasboari.model.dao.SellerDao;
import com.nicholasboari.model.entities.Department;
import com.nicholasboari.model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

  private Connection conn = null;
  PreparedStatement st = null;
  ResultSet rs = null;

  public SellerDaoJDBC(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Seller findById(Integer id) {

    try {
      st = conn.prepareStatement(
          "SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id = ?");
      st.setInt(1, id);
      rs = st.executeQuery();

      if (rs.next()) {
        Department dep = instantiateDepartment(rs);
        Seller obj = instantiateSeller(rs, dep);
        return obj;
      }
      return null;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
      DbConnection.closeResultSet(rs);
    }

  }

  private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
    Seller obj = new Seller();
    obj.setId(rs.getInt("Id"));
    obj.setName(rs.getString("Name"));
    obj.setEmail(rs.getString("Email"));
    obj.setBaseSalary(rs.getDouble("BaseSalary"));
    obj.setBirthDate(rs.getDate("BirthDate"));
    obj.setDepartment(dep);
    return obj;
  }

  private Department instantiateDepartment(ResultSet rs) throws SQLException {
    Department dep = new Department();
    dep.setId(rs.getInt("DepartmentId"));
    dep.setName(rs.getString("DepName"));
    return dep;
  }

  @Override
  public List<Seller> findByDepartment(Department department) {

    try {
      st = conn.prepareStatement(
          "SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE department.Id = ? Order By Name");
      st.setInt(1, department.getId());
      rs = st.executeQuery();

      List<Seller> sellers = new ArrayList<>();
      Map<Integer, Department> map = new HashMap<>();

      while (rs.next()) {

        Department dep = map.get(rs.getInt("DepartmentId"));

        if (dep == null) {
          dep = instantiateDepartment(rs);
          map.put(rs.getInt("DepartmentId"), dep);
        }

        dep = instantiateDepartment(rs);
        Seller obj = instantiateSeller(rs, dep);
        sellers.add(obj);
      }
      return sellers;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
      DbConnection.closeResultSet(rs);
    }
  }

  @Override
  public List<Seller> findAll() {

    try {
      st = conn.prepareStatement(
          "SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id Order By Name");
      rs = st.executeQuery();

      List<Seller> sellers = new ArrayList<>();
      Map<Integer, Department> map = new HashMap<>();

      while (rs.next()) {

        Department dep = map.get(rs.getInt("DepartmentId"));

        if (dep == null) {
          dep = instantiateDepartment(rs);
          map.put(rs.getInt("DepartmentId"), dep);
        }

        dep = instantiateDepartment(rs);
        Seller obj = instantiateSeller(rs, dep);
        sellers.add(obj);
      }
      return sellers;
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
      DbConnection.closeResultSet(rs);
    }
  }

  @Override
  public void insert(Seller obj) {
    try {
      st = conn
          .prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)",
              Statement.RETURN_GENERATED_KEYS);
      st.setString(1, obj.getName());
      st.setString(2, obj.getEmail());
      st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
      st.setDouble(4, obj.getBaseSalary());
      st.setInt(5, obj.getDepartment().getId());

      int rowsAffected = st.executeUpdate();
      if (rowsAffected > 0) {
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
          int id = rs.getInt(1);
          obj.setId(id);
        }
        DbConnection.closeResultSet(rs);
      } else {
        throw new DbException("Unexpected error! No rows affected");
      }
    } catch (SQLException e) {
      throw new DbException(e.getMessage());
    } finally {
      DbConnection.closeStatement(st);
    }
  }

  @Override
  public void update(Seller obj) {

  }

  @Override
  public void deleteById(Integer id) {

  }

}
