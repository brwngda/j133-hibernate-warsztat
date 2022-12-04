package pl.sda.j133.hibernate.warsztat.komendy;

import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.model.Mechanik;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;

public class KomendaAktualizujPojazd implements Komenda {
    private DataAccessObject<Pojazd> dataAccessObject;

    public KomendaAktualizujPojazd() {
        this.dataAccessObject = new DataAccessObject<>();
    }


    @Override
    public String getKomenda() {
        return "aktualizuj pojazd";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id pojazdu:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        // sprawdz czy rekord istnieje zanim podasz X doatkowych danych.
        if (!dataAccessObject.exists(Pojazd.class, id)) {
            System.err.println("Błąd, pojazd o takim id nie istnieje!");
            return;
        }

        System.out.println("Podaj marke pojazdu");
        String marka = Komenda.scanner.nextLine();

        System.out.println("Podaj model pojazdu");
        String model = Komenda.scanner.nextLine();

        System.out.println("Podaj numer rejestracyjny pojazdu");
        String nrRej = Komenda.scanner.nextLine();

        System.out.println("Podaj vin pojazdu");
        String vin = Komenda.scanner.nextLine();

        Pojazd pojazd = Pojazd.builder()
                .marka(marka)
                .model(model)
                .nrRej(nrRej)
                .vin(vin)
                .id(id)
                .build();

        dataAccessObject.update(Pojazd.class, id, pojazd);

    }
}


