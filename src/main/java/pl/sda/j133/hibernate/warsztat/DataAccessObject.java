package pl.sda.j133.hibernate.warsztat;

//Data Acess Object - instancja klasy, ktora umozliwia manipulowanie danymi w bazie.
//Posiada metody CRUD dla wybranego modelu.

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.warsztat.komendy.Komenda;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<T> find(Class<T> tClass, Long id) {

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            T encja = session.get(tClass, id);

            return Optional.ofNullable(encja);
        } catch (Exception e) {
            System.err.println("Błąd bazy" + e);
        }
        return Optional.empty();
    }

    public boolean delete(Class<T> tClass, Long id) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            T encja = session.get(tClass, id);
            if (encja == null) {
                return false; // nie ma encji z takim id
            }
            session.remove(encja);
            transaction.commit();
            return true; // znalezlismy encje i ją usuneliśmy, zrobiliśmy commit
        } catch (Exception ioe) {
            System.err.println("Błąd bazy" + ioe);
        }
        return false; // wystąpił błąd, nie usuneliśmy rekordu
    }

    public void update(Class<T> tClass, Long id, T encjaAktualizująca) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // kolejne linie weryfikują to, że rekord istnieje i że będziemy mogli go aktualizować w jednej transakcji
            T encja = session.get(tClass, id);
            if (encja == null) {
                System.err.println("Nie znaleziono rekordu!");
                return;
            }

            session.merge(encjaAktualizująca);

            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd" + e);
        }
    }

    public boolean exists(Class<T> tClass, Long id) {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            T encja = session.get(tClass, id);
            if (encja != null) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("Błąd" + e);
        }
        return false;
    }
}