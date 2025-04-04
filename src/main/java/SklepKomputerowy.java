
public class SklepKomputerowy {
    private Produkt[] produkty = new Produkt[100];
    private Klient[] klienci = new Klient[100];
    private Zamowienie[] zamowienia = new Zamowienie[100];
    private int liczbaProduktow = 0;
    private int liczbaKlientow = 0;
    private int liczbaZamowien = 0;
    private int nastepneIdZamowienia = 1;

    public void dodajProdukt(Produkt produkt) {
        if (liczbaProduktow < produkty.length) {
            produkty[liczbaProduktow++] = produkt;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę produktów.");
        }
    }

    public void dodajKlienta(Klient klient) {
        if (liczbaKlientow < klienci.length) {
            klienci[liczbaKlientow++] = klient;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę klientów.");
        }
    }

    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produktyZam, int[] ilosci) {
        if (liczbaZamowien < zamowienia.length) {
            for (int i = 0; i < produktyZam.length; i++) {
                if (produktyZam[i].getIloscWMagazynie() < ilosci[i]) {
                    System.out.println("Błąd: Niewystarczająca ilość produktu '" + produktyZam[i].getNazwa() + "' na magazynie.");
                    return null;
                }
            }

            String dataZamowienia = "2025-04-04";
            Zamowienie zamowienie = new Zamowienie(nastepneIdZamowienia++, klient, produktyZam, ilosci, dataZamowienia);
            zamowienia[liczbaZamowien++] = zamowienie;
            aktualizujStanMagazynowy(zamowienie);
            return zamowienie;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę zamówień.");
            return null;
        }
    }

    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] prod = zamowienie.produkty;
        int[] ilosci = zamowienie.ilosci;
        for (int i = 0; i < prod.length; i++) {
            for(int j = 0; j < liczbaProduktow; j++){
                if(produkty[j].getId() == prod[i].getId()){
                    produkty[j].setIloscWMagazynie(produkty[j].getIloscWMagazynie() - ilosci[i]);
                    break;
                }
            }
        }
    }

    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        boolean znaleziono = false;
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
                znaleziono = true;
                break;
            }
        }
        if (!znaleziono) {
            System.out.println("Błąd: Nie znaleziono zamówienia o ID: " + idZamowienia);
        }
    }

    public void wyswietlProduktyWKategorii(String kategoria) {
        System.out.println("Produkty w kategorii: " + kategoria);
        boolean znaleziono = false;
        for (int i = 0; i < liczbaProduktow; i++) {
            if (produkty[i].getKategoria().equalsIgnoreCase(kategoria)) {
                produkty[i].wyswietlInformacje();
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("Brak produktów w tej kategorii.");
        }
    }

    public void wyswietlZamowieniaKlienta(int idKlienta) {
        System.out.println("\nZamówienia klienta (ID: " + idKlienta + "):");
        boolean znaleziono = false;
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].klient.getId() == idKlienta) {
                zamowienia[i].wyswietlSzczegoly();
                System.out.println("---");
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("Brak zamówień dla tego klienta.");
        }
    }

    public void wyswietlWszystkieProdukty() {
        System.out.println("\nAktualny stan magazynowy:");
        for (int i = 0; i < liczbaProduktow; i++) {
            produkty[i].wyswietlInformacje();
        }
        System.out.println("---");
    }

    public void wyswietlWszystkichKlientow() {
        System.out.println("\nLista klientów:");
        for (int i = 0; i < liczbaKlientow; i++) {
            klienci[i].wyswietlInformacje();
        }
        System.out.println("---");
    }
}
