package com.nicholasboari.model.dao;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.model.dao.impl.DepartmentDaoJDBC;
import com.nicholasboari.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
  public static SellerDao createSellerDao() {
    return new SellerDaoJDBC(DbConnection.getConnection());
  }

  public static DepartmentDao createDepartmentDao() {
    return new DepartmentDaoJDBC(DbConnection.getConnection());
  }

}
