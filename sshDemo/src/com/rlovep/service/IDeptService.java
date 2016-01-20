package com.rlovep.service;

import java.util.List;

import com.rlovep.entity.Dept;


public interface IDeptService {
     public void save(Dept dept);
     public void update(Dept dept);
     public Dept findById(int id);
     public void delte(int id);
     public List<Dept>getAll();
}
