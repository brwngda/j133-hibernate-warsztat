package pl.sda.j133.hibernate.warsztat.komendy;

import org.hibernate.Session;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

public class KomendaZnajdzMechanik implements Komenda {
    private DataAccessObject<Mechanik> dataAccessObject;

    public KomendaZnajdzMechanik() {
        this.dataAccessObject = new DataAccessObject<>();
    }
@Override
    public String getKomenda() {
        return "znajdz mechanik";
    }
@Override
    public void obsluga() {
        System.out.println("Podaj id szukanego mechanika:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Mechanik mechanik = session.get(Mechanik.class, id);
            if (mechanik != null) {
                System.out.println(mechanik);
            } else {
                System.err.println("Nie znaleziono pojazdu!");
            }
        } catch (Exception ioe) {
            System.err.println("Błąd bazy: " + ioe);
        }
    }
}