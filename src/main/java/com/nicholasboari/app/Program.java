package com.nicholasboari.app;

import com.nicholasboari.model.dao.DaoFactory;
import com.nicholasboari.model.dao.SellerDao;
import com.nicholasboari.model.entities.Seller;

public class Program {
  public static void main(String[] args) {

    SellerDao sellerDao = DaoFactory.creatSellerDao();

    Seller seller = sellerDao.findById(5);

    System.out.println(seller);
  }
}
