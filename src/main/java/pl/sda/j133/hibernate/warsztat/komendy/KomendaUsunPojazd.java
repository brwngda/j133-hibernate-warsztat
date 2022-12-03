package pl.sda.j133.hibernate.warsztat.komendy;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

import java.util.Optional;

public class KomendaUsunPojazd implements Komenda {
    private DataAccessObject<Pojazd> dataAccessObject;

    public KomendaUsunPojazd() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun pojazd";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanego pojazdu:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Pojazd pojazd = session.get(Pojazd.class, id);

            if (pojazd != null) {
                session.remove(pojazd);
                System.out.println("Usunięto rekord o identyfikatorze: " + id);
            } else {
                System.err.println("Rekord nie istnieje");
            }
                transaction.commit();
            } catch(Exception ioe){
                System.err.println("Błąd bazy" + ioe);

            }
        }
    }