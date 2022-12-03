package pl.sda.j133.hibernate.warsztat;

//Data Acess Object - instancja klasy, ktora umozliwia manipulowanie danymi w bazie.
//Posiada metody CRUD dla wybranego modelu.

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DataAccessObject<T> {

    public void insert(T obiektDoWstawieniaDoBazy) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(obiektDoWstawieniaDoBazy);

            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd dodawania do bazy danych" + e);
        }
    }

    public List<T> findAll(Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<T> zapytanie = session.createQuery("FROM " + tClass.getName(), tClass);

            list.addAll(zapytanie.getResultList());
        } catch (Exception e) {
            System.err.println("Błąd bazy" + e);
        }
        return list;
    }
}
