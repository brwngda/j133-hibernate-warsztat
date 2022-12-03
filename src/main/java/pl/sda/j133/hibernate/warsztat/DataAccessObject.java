package pl.sda.j133.hibernate.warsztat;

//Data Acess Object - instancja klasy, ktora umozliwia manipulowanie danymi w bazie.
//Posiada metody CRUD dla wybranego modelu.

import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
