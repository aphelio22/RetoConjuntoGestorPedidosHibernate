package com.example.retoconjuntogestorpedidoshibernate.domain.pedido;

import com.example.retoconjuntogestorpedidoshibernate.domain.DAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import com.example.retoconjuntogestorpedidoshibernate.domain.usuario.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class PedidoDAO implements DAO<Pedido> {
    @Override
    public ArrayList<Pedido> getAll() {
        var salida = new ArrayList<Pedido>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Pedido> query = sesion.createQuery("from Pedido", Pedido.class);
            salida = (ArrayList<Pedido>) query.getResultList();
        }
        return salida;
    }

    @Override
    public Pedido get(Integer id) {
        var salida = new Pedido();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Pedido.class, id);
        }
        return salida;
    }

    @Override
    public Pedido save(Pedido data) {
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
    public void update(Pedido data) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Actualizar el pedido en la base de datos
            session.update(data);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public void delete(Pedido data) {
        HibernateUtil.getSessionFactory().inTransaction(session -> {
            Pedido pedido = session.get(Pedido.class, data.getId());
            session.remove(pedido);
        });
    }
}
