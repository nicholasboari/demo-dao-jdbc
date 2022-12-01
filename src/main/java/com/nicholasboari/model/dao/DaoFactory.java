package com.nicholasboari.model.dao;

import com.nicholasboari.db.DbConnection;
import com.nicholasboari.model.dao.impl.DepartmenteDaoJDBC;
import com.nicholasboari.model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
  public static SellerDao creatSellerDao() {
    return new SellerDaoJDBC(DbConnection.getConnection());
  }

  public static DepartmentDao creatDepartmentDao() {
    return new DepartmenteDaoJDBC();
  }

}
