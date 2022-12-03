package pl.sda.j133.hibernate.warsztat.komendy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

public class KomendaDodajMechanik implements Komenda {
    private DataAccessObject<Mechanik> dataAccessObject;

    public KomendaDodajMechanik() {
        this.dataAccessObject = new DataAccessObject<>();
    }


    @Override
    public String getKomenda() {
        return "dodaj mechanik";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj imie");
        String imie = Komenda.scanner.nextLine();

        System.out.println("Podaj kwalifikacje");
        String kwalifikacje = Komenda.scanner.nextLine();

        System.out.println("Podaj specjalizacje");
        String specjalizacja = Komenda.scanner.nextLine();

        Mechanik mechanik = Mechanik.builder()
                .imie(imie)
                .kwalifikacje(kwalifikacje)
                .specjalizacja(specjalizacja)
                .build();

        dataAccessObject.insert(mechanik);
//        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            session.persist(mechanik);
//
//            transaction.commit();
//        } catch (Exception e) {
//            System.err.println("Błąd dodawania do bazy danych" + e);


        }
    }

