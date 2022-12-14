package pl.sda.j133.hibernate.warsztat.komendy;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

import java.util.List;

public class KomendaListaPojazd implements Komenda {
    private DataAccessObject<Pojazd> dataAccessObject;

    public KomendaListaPojazd() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    public String getKomenda() {
        return "lista pojazdow";
    }

    public void obsluga() {
        List<Pojazd> pojazdy = dataAccessObject.findAll(Pojazd.class);
        pojazdy.forEach(System.out::println);
    }
//        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
//            TypedQuery<Pojazd> zapytanie = session.createQuery("FROM Pojazd", Pojazd.class);
//            List<Pojazd> listaWszystkichPojazdow = zapytanie.getResultList();
//
//            listaWszystkichPojazdow.forEach(System.out::println);
//
//        } catch (Exception e) {
//            System.err.println("Błąd bazy" + e);
}


