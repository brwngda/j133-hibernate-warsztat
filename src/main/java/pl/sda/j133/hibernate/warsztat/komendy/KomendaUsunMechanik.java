package pl.sda.j133.hibernate.warsztat.komendy;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

public class KomendaUsunMechanik implements Komenda {
    private DataAccessObject<Pojazd> dataAccessObject;

    public KomendaUsunMechanik() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun mechanika";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanego mechanika:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Mechanik mechanik = session.get(Mechanik.class, id);

            if (mechanik != null) {
                session.remove(mechanik);
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