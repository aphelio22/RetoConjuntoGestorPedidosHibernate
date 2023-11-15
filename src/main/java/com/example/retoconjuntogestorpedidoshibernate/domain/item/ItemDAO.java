package com.example.retoconjuntogestorpedidoshibernate.domain.item;

import com.example.retoconjuntogestorpedidoshibernate.Sesion;
import com.example.retoconjuntogestorpedidoshibernate.domain.DAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                // Comenzar la transacción
                transaction = session.beginTransaction();

                // Guardar el nuevo pedido en la base de datos
                session.save(data);

                // Commit de la transacción
                transaction.commit();
            } catch (Exception e) {
                // Manejar cualquier excepción que pueda ocurrir durante la transacción
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return data;
        }
    }

    @Override
    public void update(Item data) {

    }

    @Override
    public void delete(Item data) {

    }
}
