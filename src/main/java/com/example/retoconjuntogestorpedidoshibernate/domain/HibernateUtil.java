package com.example.retoconjuntogestorpedidoshibernate.domain;

import lombok.extern.java.Log;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Log
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();

            log.info("SessionFactory creado");
        } catch (Exception e){
            e.printStackTrace();
           // log.severe("Error al crear Sessionfactory()");
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
