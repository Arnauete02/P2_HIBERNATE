package manager;

import model.Entrada;
import model.Vid;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import model.Campo;
import model.Bodega;
import org.hibernate.query.Query;
import utils.Enum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Hibernate {
    private Session session;
    private Transaction transaction;

    public void initSession(){
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        // Get the session object.
        session = sessionFactory.openSession();
    }

    public void endSession(){
        session.close();
    }

    public ArrayList<Entrada> getAllEntrada(){
        ArrayList<Entrada> entradas = new ArrayList<>();

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("select e from Entrada e");

            List<Entrada> entradaList = (List<Entrada>) query.list();

            entradas.addAll(entradaList);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }

        return entradas;
    }

    public int insertBodega(Bodega bodega) {
        try {
            transaction = session.beginTransaction();

            int id = (Integer) session.save(bodega);

            transaction.commit();

            return id;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }

        return 0;
    }

    public void updateBodega(Bodega bodega) {
        try {
            transaction = session.beginTransaction();

            session.save(bodega);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }

    public int oneToOneCampoBodega(Campo campo, Bodega bodega){
        campo.setBodega(bodega);

        try {
            transaction = session.beginTransaction();

            session.save(bodega);
            int id = (Integer) session.save(campo);

            transaction.commit();

            return id;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }

        return 0;
    }

    public void updateCampo(Campo campo) {
        try {
            transaction = session.beginTransaction();

            session.save(campo);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }
    }

    public int insertVid(Enum.TipoVid tipoVid, int cantidad){
        Vid vid = new Vid();
        vid.setTipo(tipoVid);
        vid.setCantidad(cantidad);

        try {
            transaction = session.beginTransaction();

            int id = (Integer) session.save(vid);

            transaction.commit();

            return id;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }

        return 0;
    }

    public Vid getVid(int id) {
        try {
            transaction = session.beginTransaction();

            Vid vid = session.get(Vid.class, id);

            transaction.commit();

            return vid;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();

            e.printStackTrace();
        }

        return null;
    }
}
