package com.example.retoconjuntogestorpedidoshibernate.domain;

import java.util.ArrayList;

public interface DAO<T> {
    public ArrayList<T> getAll();
    public T get(Integer id);
    public T save(T data);
    public void update(T data);
    public void delete(T data);
}
