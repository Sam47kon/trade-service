package com.tradeservice.testproject.services;

import java.util.List;

public interface CrudService<T> {

  T add(T obj);

//  T getById(Long id);

  T edit(T obj);

  void delete(Long id);

  List<T> getAll();
}
