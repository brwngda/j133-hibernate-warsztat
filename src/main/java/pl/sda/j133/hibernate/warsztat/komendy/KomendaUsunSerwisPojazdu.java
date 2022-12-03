package pl.sda.j133.hibernate.warsztat.komendy;

import pl.sda.j133.hibernate.warsztat.DataAccessObject;
import pl.sda.j133.hibernate.warsztat.model.Pojazd;
import pl.sda.j133.hibernate.warsztat.model.SerwisPojazdu;

public class KomendaUsunSerwisPojazdu implements Komenda {
    private DataAccessObject<SerwisPojazdu> dataAccessObject;

    public KomendaUsunSerwisPojazdu() {
        this.dataAccessObject = new DataAccessObject<>();
    }

    @Override
    public String getKomenda() {
        return "usun czynnosc serwisowa";
    }

    @Override
    public void obsluga() {
        System.out.println("Podaj id usuwanej czynnosci serwisowej:");
        String idString = Komenda.scanner.nextLine();
        Long id = Long.parseLong(idString);

        if (dataAccessObject.delete(SerwisPojazdu.class, id)) {
            System.out.println("Usunięto czynnosc serwisowa");
        } else {
            System.err.println("Nie udało się usunąć czynnosci serwisowej");
        }
    }

//        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            Pojazd pojazd = session.get(Pojazd.class, id);
//
//            if (pojazd != null) {
//                session.remove(pojazd);
//                System.out.println("Usunięto rekord o identyfikatorze: " + id);
//            } else {
//                System.err.println("Rekord nie istnieje");
//            }
//                transaction.commit();
//            } catch(Exception ioe){
//                System.err.println("Błąd bazy" + ioe);

}
