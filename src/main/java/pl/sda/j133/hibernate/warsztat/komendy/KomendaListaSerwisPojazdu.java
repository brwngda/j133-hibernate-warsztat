package pl.sda.j133.hibernate.warsztat.komendy;

import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.model.SerwisPojazdu;

import java.util.List;

public class KomendaListaSerwisPojazdu implements Komenda {
    private DataAccessObject<SerwisPojazdu> dataAccessObject;

    public KomendaListaSerwisPojazdu() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    public String getKomenda() {
        return "lista czynnosci serwisowych";
    }

    public void obsluga() {
        List<SerwisPojazdu> czynnosciSerwisowe = dataAccessObject.findAll(SerwisPojazdu.class);
        czynnosciSerwisowe.forEach(System.out::println);
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


