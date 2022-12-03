package pl.sda.j133.hibernate.warsztat.komendy;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;

import java.util.List;

public class KomendaListaMechanik implements Komenda {
    private DataAccessObject<Mechanik> dataAccessObject;

    public KomendaListaMechanik() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    public String getKomenda() {
        return "lista mechanikow";
    }

    @Override
    public void obsluga() {
List<Mechanik> mechanicy = dataAccessObject.findAll(Mechanik.class);
mechanicy.forEach(System.out::println);
    }

//        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
//            TypedQuery<Mechanik> zapytanie = session.createQuery("FROM Mechanik", Mechanik.class);
//            List<Mechanik> listaWszystkichMechanikow = zapytanie.getResultList();
//
//            listaWszystkichMechanikow.forEach(System.out::println);
//
//        } catch (Exception e) {
//            System.err.println("Błąd bazy" + e);
//        }
//    }
}
