package com.example.retoconjuntogestorpedidoshibernate.domain.item;

import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.DAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class ItemDAO implements DAO<Item> {
    @Override
    public ArrayList<Item> getAll() {
        var salida = new ArrayList<Item>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Item> query = sesion.createQuery("from Item", Item.class);
            salida = (ArrayList<Item>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Item get(Integer id) {
        return null;
    }

    @Override
    public Item save(Item data) {
        return null;
    }

    @Override
    public void update(Item data) {

    }

    @Override
    public void delete(Item data) {

    }
}
