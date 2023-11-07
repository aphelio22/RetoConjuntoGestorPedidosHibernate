package com.example.retoconjuntogestorpedidoshibernate.domain.pedido;

import com.example.retoconjuntogestorpedidoshibernate.domain.DAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import com.example.retoconjuntogestorpedidoshibernate.domain.item.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

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
        return null;
    }

    @Override
    public Pedido save(Pedido data) {
        return null;
    }

    @Override
    public void update(Pedido data) {

    }

    @Override
    public void delete(Pedido data) {

    }
}
