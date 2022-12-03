package pl.sda.j133.hibernate.warsztat.komendy;

import org.hibernate.Session;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

public class KomendaZnajdzPojazd implements Komenda {
    private DataAccessObject<Pojazd> dataAccessObject;

    public KomendaZnajdzPojazd() {
        this.dataAccessObject = new DataAccessObject<>();
    }
@Override
    public String getKomenda() {
        return "znajdz pojazd";
    }
@Override
    public void obsluga() {
        System.out.println("Podaj id szukanego pojazdu:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Pojazd pojazd = session.get(Pojazd.class, id);
            if (pojazd != null) {
                System.out.println(pojazd);
            } else {
                System.err.println("Nie znaleziono pojazdu!");
            }
        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}