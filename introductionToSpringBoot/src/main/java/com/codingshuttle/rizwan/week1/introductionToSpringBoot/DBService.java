package com.codingshuttle.rizwan.week1.introductionToSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

  //  @Autowired
  final  private DB db;

    public DBService(DB db) {
        this.db =db;
    }

    String getData() {
        return db.getData();
    }
}
