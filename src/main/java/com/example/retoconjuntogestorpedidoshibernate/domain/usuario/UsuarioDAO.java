package com.example.retoconjuntogestorpedidoshibernate.domain.usuario;

import com.example.retoconjuntogestorpedidoshibernate.domain.DAO;
import com.example.retoconjuntogestorpedidoshibernate.domain.HibernateUtil;
import com.example.retoconjuntogestorpedidoshibernate.domain.pedido.Pedido;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UsuarioDAO implements DAO<Usuario> {
    @Override
    public ArrayList<Usuario> getAll() {
        var salida = new ArrayList<Usuario>(0);
        try(Session sesion = HibernateUtil.getSessionFactory().openSession()){
            Query<Usuario> query = sesion.createQuery("from Usuario", Usuario.class);
            salida = (ArrayList<Usuario>) query.getResultList();
        }
        return salida;
    }

    //En el PedidosUsuarioController estás llamando a este método.
    @Override
    public Usuario get(Integer id) {
        var salida = new Usuario();
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            salida = session.get(Usuario.class, id);
        }
        return salida;
    }

    @Override
    public Usuario save(Usuario data) {
        return null;
    }

    @Override
    public void update(Usuario data) {

    }

    @Override
    public void delete(Usuario data) {

    }
    public Usuario validateUser(String username, String password){
        //Desde un lambda no se puede escribir desde una variable externa.
        Usuario result = null;

        //Si la sesión está dentro de un try con recursos se cierra sola.
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //Se hacen consultas a la entidad (clase User) no a la tabla.
            Query<Usuario> query = session.createQuery("from Usuario where email=:u and contrasenha=:p", Usuario.class);

            //Se refieren a los que entran por el método.
            query.setParameter("u", username);
            query.setParameter("p", password);
            //

            try {
                result = query.getSingleResult();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

            // Versión lambda
            /*
             * HibernateUtil.getSessionFactory().inSession( session -> {
             *
             * });
             * */
        }
        return result;
    }
}
