
public class Main {
    public static void main(String[] args) {
        SklepKomputerowy sklep = new SklepKomputerowy();

        Produkt produkt1 = new Produkt(1, "Laptop Dell XPS 13", "Laptop", 4999.99, 10);
        Produkt produkt2 = new Produkt(2, "Mysz Logitech MX Master 3", "Akcesoria", 349.99, 30);
        Produkt produkt3 = new Produkt(3, "Monitor Samsung 27\"", "Monitor", 1299.99, 15);
        Produkt produkt4 = new Produkt(4, "Klawiatura mechaniczna", "Akcesoria", 399.99, 20);

        sklep.dodajProdukt(produkt1);
        sklep.dodajProdukt(produkt2);
        sklep.dodajProdukt(produkt3);
        sklep.dodajProdukt(produkt4);

        System.out.println("--- Stan magazynowy po dodaniu produktów ---");
        sklep.wyswietlWszystkieProdukty();

        Klient klient1 = new Klient(1, "Jan", "Kowalski", "jan.kowalski@example.com", true);
        Klient klient2 = new Klient(2, "Anna", "Nowak", "anna.nowak@example.com", false);
        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);

        System.out.println("--- Lista klientów ---");
        sklep.wyswietlWszystkichKlientow();

        System.out.println("--- Tworzenie zamówienia 1 (Klient 1) ---");
        Produkt[] produktyZamowienia1 = {produkt1, produkt2};
        int[] ilosciZamowienia1 = {1, 1};
        Zamowienie zamowienie1 = sklep.utworzZamowienie(klient1, produktyZamowienia1, ilosciZamowienia1);

        if (zamowienie1 != null) {
            System.out.println("--- Szczegóły zamówienia 1 ---");
            zamowienie1.wyswietlSzczegoly();
            System.out.println("\n--- Stan magazynowy po zamówieniu 1 ---");
            sklep.wyswietlWszystkieProdukty();
        }

        System.out.println("\n--- Tworzenie zamówienia 2 (Klient 2) ---");
        Produkt[] produktyZamowienia2 = {produkt3, produkt4};
        int[] ilosciZamowienia2 = {2, 1};
        Zamowienie zamowienie2 = sklep.utworzZamowienie(klient2, produktyZamowienia2, ilosciZamowienia2);

        if (zamowienie2 != null) {
            System.out.println("--- Szczegóły zamówienia 2 ---");
            zamowienie2.wyswietlSzczegoly();
            System.out.println("\n--- Stan magazynowy po zamówieniu 2 ---");
            sklep.wyswietlWszystkieProdukty();
        }

        if (zamowienie1 != null) {
            System.out.println("\n--- Zmiana statusu zamówienia 1 ---");
            sklep.zmienStatusZamowienia(zamowienie1.getId(), "Zrealizowane");
            System.out.println("Nowy status zamówienia ID " + zamowienie1.getId() + ": " + zamowienie1.getStatus());
            System.out.println("--- Szczegóły zamówienia 1 po zmianie statusu ---");
            zamowienie1.wyswietlSzczegoly();
        }

        System.out.println("\n--- Produkty w kategorii: Akcesoria ---");
        sklep.wyswietlProduktyWKategorii("Akcesoria");

        System.out.println("\n--- Produkty w kategorii: Laptop ---");
        sklep.wyswietlProduktyWKategorii("Laptop");

        System.out.println("\n--- Wyświetlanie zamówień klienta Jana Kowalskiego (ID: 1) ---");
        sklep.wyswietlZamowieniaKlienta(1);

        System.out.println("\n--- Wyświetlanie zamówień klienta Anny Nowak (ID: 2) ---");
        sklep.wyswietlZamowieniaKlienta(2);
    }
}
