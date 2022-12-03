package pl.sda.j133.hibernate.warsztat.komendy;

import org.hibernate.Session;
import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.HibernateUtil;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

import java.util.Optional;

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

        Optional<Pojazd> pojazdOptional = dataAccessObject.find(Pojazd.class, id);
        if (pojazdOptional.isPresent()) {
            System.out.println(pojazdOptional.get());
        } else {
            System.err.println("Nie znaleziono pojazdu");
        }
    }
}