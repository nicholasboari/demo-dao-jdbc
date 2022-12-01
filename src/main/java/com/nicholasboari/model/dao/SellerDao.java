package com.nicholasboari.model.dao;

import java.util.List;

import com.nicholasboari.model.entities.Department;
import com.nicholasboari.model.entities.Seller;

public interface SellerDao {
  void insert(Seller obj);

  void update(Seller obj);

  void deleteById(Integer id);

  Seller findById(Integer id);

  List<Seller> findByDepartment(Department department);

  List<Seller> findAll();
}
