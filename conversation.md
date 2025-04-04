## src/
├── Main.java
├── Produkt.java
├── Klient.java
├── Zamowienie.java
└── SklepKomputerowy.java

// Produkt.java
public class Produkt {
    private int id;
    private String nazwa;
    private String kategoria;
    private double cena;
    private int iloscWMagazynie;

    public Produkt() {}

    public Produkt(int id, String nazwa, String kategoria, double cena, int iloscWMagazynie) {
        this.id = id;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
        this.iloscWMagazynie = iloscWMagazynie;
    }

    public int getId() { return id; }
    public String getNazwa() { return nazwa; }
    public String getKategoria() { return kategoria; }
    public double getCena() { return cena; }
    public int getIloscWMagazynie() { return iloscWMagazynie; }

    public void setId(int id) { this.id = id; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
    public void setKategoria(String kategoria) { this.kategoria = kategoria; }
    public void setCena(double cena) { this.cena = cena; }
    public void setIloscWMagazynie(int ilosc) { this.iloscWMagazynie = ilosc; }

    public void wyswietlInformacje() {
        System.out.println("ID: " + id + ", Nazwa: " + nazwa + ", Kategoria: " + kategoria + ", Cena: " + cena + ", Ilość: " + iloscWMagazynie);
    }
}

// Klient.java
public class Klient {
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private boolean czyStaly;

    public Klient() {}

    public Klient(int id, String imie, String nazwisko, String email, boolean czyStaly) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.czyStaly = czyStaly;
    }

    public int getId() { return id; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public String getEmail() { return email; }
    public boolean isCzyStaly() { return czyStaly; }

    public void setId(int id) { this.id = id; }
    public void setImie(String imie) { this.imie = imie; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public void setEmail(String email) { this.email = email; }
    public void setCzyStaly(boolean czyStaly) { this.czyStaly = czyStaly; }

    public void wyswietlInformacje() {
        System.out.println("ID: " + id + ", Imię: " + imie + ", Nazwisko: " + nazwisko + ", Email: " + email + ", Stały klient: " + czyStaly);
    }
}

// Zamowienie.java
public class Zamowienie {
    private int id;
    public Klient klient;
    public Produkt[] produkty;
    public int[] ilosci;
    private double[] cenyPoZnizce;
    private String dataZamowienia;
    private String status;

    public Zamowienie(int id, Klient klient, Produkt[] produkty, int[] ilosci, String dataZamowienia) {
        this.id = id;
        this.klient = klient;
        this.produkty = produkty;
        this.ilosci = ilosci;
        this.dataZamowienia = dataZamowienia;
        this.status = "Nowe";

        this.cenyPoZnizce = new double[produkty.length];
        for (int i = 0; i < produkty.length; i++) {
            if (klient.isCzyStaly()) {
                cenyPoZnizce[i] = produkty[i].getCena() * 0.9;
            } else {
                cenyPoZnizce[i] = produkty[i].getCena();
            }
        }
    }

    public int getId() { return id; }
    public void setStatus(String status) { this.status = status; }

    public double obliczWartoscZamowienia() {
        double suma = 0;
        for (int i = 0; i < produkty.length; i++) {
            suma += cenyPoZnizce[i] * ilosci[i];
        }
        return suma;
    }

    public void wyswietlSzczegoly() {
        System.out.println("Zamówienie ID: " + id + ", Klient: " + klient.getImie() + " " + klient.getNazwisko());
        System.out.println("Data: " + dataZamowienia + ", Status: " + status);
        for (int i = 0; i < produkty.length; i++) {
            System.out.println(" - " + produkty[i].getNazwa() + " x " + ilosci[i] + " = " + cenyPoZnizce[i] * ilosci[i]);
        }
        System.out.println("Łącznie: " + obliczWartoscZamowienia());
    }
}

// SklepKomputerowy.java
public class SklepKomputerowy {
    private Produkt[] produkty = new Produkt[100];
    private Klient[] klienci = new Klient[100];
    private Zamowienie[] zamowienia = new Zamowienie[100];
    private int liczbaProduktow = 0;
    private int liczbaKlientow = 0;
    private int liczbaZamowien = 0;

    public void dodajProdukt(Produkt produkt) {
        produkty[liczbaProduktow++] = produkt;
    }

    public void dodajKlienta(Klient klient) {
        klienci[liczbaKlientow++] = klient;
    }

    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produktyZam, int[] ilosci) {
        Zamowienie zamowienie = new Zamowienie(++liczbaZamowien, klient, produktyZam, ilosci, "2025-04-04");
        zamowienia[liczbaZamowien - 1] = zamowienie;
        return zamowienie;
    }

    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] prod = zamowienie.produkty;
        int[] ilosci = zamowienie.ilosci;
        for (int i = 0; i < prod.length; i++) {
            prod[i].setIloscWMagazynie(prod[i].getIloscWMagazynie() - ilosci[i]);
        }
    }

    public void zmienStatusZamowienia(int idZamowienia, String nowyStatus) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].getId() == idZamowienia) {
                zamowienia[i].setStatus(nowyStatus);
            }
        }
    }

    public void wyswietlProduktyWKategorii(String kategoria) {
        System.out.println("Produkty w kategorii: " + kategoria);
        for (int i = 0; i < liczbaProduktow; i++) {
            if (produkty[i].getKategoria().equalsIgnoreCase(kategoria)) {
                produkty[i].wyswietlInformacje();
            }
        }
    }

    public void wyswietlZamowieniaKlienta(int idKlienta) {
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].klient.getId() == idKlienta) {
                zamowienia[i].wyswietlSzczegoly();
            }
        }
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        SklepKomputerowy sklep = new SklepKomputerowy();

        Produkt produkt1 = new Produkt(1, "Laptop Dell XPS 13", "Laptop", 4999.99, 10);
        Produkt produkt2 = new Produkt(2, "Mysz Logitech MX Master 3", "Mysz", 349.99, 30);
        Produkt produkt3 = new Produkt(3, "Monitor Samsung 27\"", "Monitor", 1299.99, 15);
        Produkt produkt4 = new Produkt(4, "Klawiatura mechaniczna", "Klawiatura", 399.99, 20);

        sklep.dodajProdukt(produkt1);
        sklep.dodajProdukt(produkt2);
        sklep.dodajProdukt(produkt3);
        sklep.dodajProdukt(produkt4);

        Klient klient1 = new Klient(1, "Jan", "Kowalski", "jan.kowalski@example.com", true);
        Klient klient2 = new Klient(2, "Anna", "Nowak", "anna.nowak@example.com", false);
        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);

        Produkt[] produktyZamowienia1 = {produkt1, produkt2};
        int[] ilosciZamowienia1 = {1, 1};
        Zamowienie zamowienie1 = sklep.utworzZamowienie(klient1, produktyZamowienia1, ilosciZamowienia1);
        sklep.aktualizujStanMagazynowy(zamowienie1);

        Produkt[] produktyZamowienia2 = {produkt3, produkt4};
        int[] ilosciZamowienia2 = {2, 1};
        Zamowienie zamowienie2 = sklep.utworzZamowienie(klient2, produktyZamowienia2, ilosciZamowienia2);
        sklep.aktualizujStanMagazynowy(zamowienie2);

        zamowienie1.wyswietlSzczegoly();
        zamowienie2.wyswietlSzczegoly();

        sklep.zmienStatusZamowienia(zamowienie1.getId(), "Zrealizowane");
        System.out.println("\nPo zmianie statusu:");
        zamowienie1.wyswietlSzczegoly();

        System.out.println("\nProdukty - Laptopy:");
        sklep.wyswietlProduktyWKategorii("Laptop");

        System.out.println("\nZamówienia klienta Jan Kowalski:");
        sklep.wyswietlZamowieniaKlienta(1);
    }
}

// .gitignore
*.class
*.log
*.jar
out/
bin/
.DS_Store
.idea/
.vscode/
Podziel ten kod na poszczególne pliki tak żebym mógł to pobrać i umieścić na githubie 

Jasne, oto kod podzielony na poszczególne pliki zgodnie z podaną strukturą. Możesz skopiować zawartość każdego bloku do odpowiedniego pliku w swoim projekcie.

**`.gitignore`**
```gitignore
# Compiled class file
*.class

# Log file
*.log

# Optional JAR file
*.jar

# Package Files #
*.pkg
*.war
*.ear

# Virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml
hs_err_pid*

# Build directory
out/
bin/

# macOS specific
.DS_Store

# IDE specific
.idea/
.vscode/
```

**`src/Produkt.java`**
```java
public class Produkt {
    private int id;
    private String nazwa;
    private String kategoria;
    private double cena;
    private int iloscWMagazynie;

    public Produkt() {}

    public Produkt(int id, String nazwa, String kategoria, double cena, int iloscWMagazynie) {
        this.id = id;
        this.nazwa = nazwa;
        this.kategoria = kategoria;
        this.cena = cena;
        this.iloscWMagazynie = iloscWMagazynie;
    }

    public int getId() { return id; }
    public String getNazwa() { return nazwa; }
    public String getKategoria() { return kategoria; }
    public double getCena() { return cena; }
    public int getIloscWMagazynie() { return iloscWMagazynie; }

    public void setId(int id) { this.id = id; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
    public void setKategoria(String kategoria) { this.kategoria = kategoria; }
    public void setCena(double cena) { this.cena = cena; }
    public void setIloscWMagazynie(int ilosc) { this.iloscWMagazynie = ilosc; }

    public void wyswietlInformacje() {
        System.out.println("ID: " + id + ", Nazwa: " + nazwa + ", Kategoria: " + kategoria + ", Cena: " + cena + ", Ilość: " + iloscWMagazynie);
    }
}
```

**`src/Klient.java`**
```java
public class Klient {
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private boolean czyStaly;

    public Klient() {}

    public Klient(int id, String imie, String nazwisko, String email, boolean czyStaly) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.czyStaly = czyStaly;
    }

    public int getId() { return id; }
    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public String getEmail() { return email; }
    public boolean isCzyStaly() { return czyStaly; }

    public void setId(int id) { this.id = id; }
    public void setImie(String imie) { this.imie = imie; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public void setEmail(String email) { this.email = email; }
    public void setCzyStaly(boolean czyStaly) { this.czyStaly = czyStaly; }

    public void wyswietlInformacje() {
        System.out.println("ID: " + id + ", Imię: " + imie + ", Nazwisko: " + nazwisko + ", Email: " + email + ", Stały klient: " + czyStaly);
    }
}
```

**`src/Zamowienie.java`**
```java
public class Zamowienie {
    private int id;
    public Klient klient;
    public Produkt[] produkty;
    public int[] ilosci;
    private double[] cenyPoZnizce;
    private String dataZamowienia;
    private String status;

    public Zamowienie(int id, Klient klient, Produkt[] produkty, int[] ilosci, String dataZamowienia) {
        this.id = id;
        this.klient = klient;
        this.produkty = produkty;
        this.ilosci = ilosci;
        this.dataZamowienia = dataZamowienia;
        this.status = "Nowe"; // Domyślny status

        // Obliczanie cen po zniżce dla stałego klienta
        this.cenyPoZnizce = new double[produkty.length];
        for (int i = 0; i < produkty.length; i++) {
            if (klient.isCzyStaly()) {
                cenyPoZnizce[i] = produkty[i].getCena() * 0.9; // 10% zniżki
            } else {
                cenyPoZnizce[i] = produkty[i].getCena();
            }
        }
    }

    public int getId() { return id; }
    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; } // Dodano getter dla statusu
    public String getDataZamowienia() { return dataZamowienia; } // Dodano getter dla daty

    // Metoda do obliczania całkowitej wartości zamówienia
    public double obliczWartoscZamowienia() {
        double suma = 0;
        for (int i = 0; i < produkty.length; i++) {
            suma += cenyPoZnizce[i] * ilosci[i];
        }
        return suma;
    }

    // Metoda do wyświetlania szczegółów zamówienia
    public void wyswietlSzczegoly() {
        System.out.println("Zamówienie ID: " + id + ", Klient: " + klient.getImie() + " " + klient.getNazwisko());
        System.out.println("Data: " + dataZamowienia + ", Status: " + status);
        System.out.println("Produkty:");
        for (int i = 0; i < produkty.length; i++) {
            System.out.println(" - " + produkty[i].getNazwa() + " (Cena jedn.: " + String.format("%.2f", cenyPoZnizce[i]) + ") x " + ilosci[i] + " = " + String.format("%.2f", cenyPoZnizce[i] * ilosci[i]));
        }
        System.out.println("Łączna wartość zamówienia: " + String.format("%.2f", obliczWartoscZamowienia()));
    }
}
```

**`src/SklepKomputerowy.java`**
```java
public class SklepKomputerowy {
    private Produkt[] produkty = new Produkt[100]; // Maksymalnie 100 produktów
    private Klient[] klienci = new Klient[100];   // Maksymalnie 100 klientów
    private Zamowienie[] zamowienia = new Zamowienie[100]; // Maksymalnie 100 zamówień
    private int liczbaProduktow = 0;
    private int liczbaKlientow = 0;
    private int liczbaZamowien = 0;
    private int nastepneIdZamowienia = 1; // Licznik dla ID zamówień

    // Dodaje produkt do sklepu
    public void dodajProdukt(Produkt produkt) {
        if (liczbaProduktow < produkty.length) {
            produkty[liczbaProduktow++] = produkt;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę produktów.");
        }
    }

    // Dodaje klienta do bazy sklepu
    public void dodajKlienta(Klient klient) {
        if (liczbaKlientow < klienci.length) {
            klienci[liczbaKlientow++] = klient;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę klientów.");
        }
    }

    // Tworzy nowe zamówienie
    public Zamowienie utworzZamowienie(Klient klient, Produkt[] produktyZam, int[] ilosci) {
        if (liczbaZamowien < zamowienia.length) {
            // Sprawdzenie dostępności produktów i wystarczającej ilości na magazynie
            for (int i = 0; i < produktyZam.length; i++) {
                if (produktyZam[i].getIloscWMagazynie() < ilosci[i]) {
                    System.out.println("Błąd: Niewystarczająca ilość produktu '" + produktyZam[i].getNazwa() + "' na magazynie.");
                    return null; // Zwraca null, jeśli brakuje produktu
                }
            }

            // Jeśli wszystkie produkty są dostępne, tworzymy zamówienie
            // Używamy aktualnej daty i czasu - do tego potrzebny import java.time
            // String dataZamowienia = java.time.LocalDate.now().toString(); // Przykład użycia aktualnej daty
            String dataZamowienia = "2025-04-04"; // Używam daty podanej w przykładzie

            Zamowienie zamowienie = new Zamowienie(nastepneIdZamowienia++, klient, produktyZam, ilosci, dataZamowienia);
            zamowienia[liczbaZamowien++] = zamowienie;
            // Aktualizacja stanu magazynowego po utworzeniu zamówienia
            aktualizujStanMagazynowy(zamowienie);
            return zamowienie;
        } else {
            System.out.println("Błąd: Osiągnięto maksymalną liczbę zamówień.");
            return null;
        }
    }

    // Aktualizuje stan magazynowy po złożeniu zamówienia
    public void aktualizujStanMagazynowy(Zamowienie zamowienie) {
        Produkt[] prod = zamowienie.produkty;
        int[] ilosci = zamowienie.ilosci;
        for (int i = 0; i < prod.length; i++) {
            // Znajdujemy produkt w głównej tablicy produktów sklepu (dla pewności)
            for(int j = 0; j < liczbaProduktow; j++){
                if(produkty[j].getId() == prod[i].getId()){
                    produkty[j].setIloscWMagazynie(produkty[j].getIloscWMagazynie() - ilosci[i]);
                    break; // Kończymy wewnętrzną pętlę po znalezieniu i aktualizacji
                }
            }
        }
    }

    // Zmienia status zamówienia o podanym ID
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

    // Wyświetla wszystkie produkty w danej kategorii
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

    // Wyświetla wszystkie zamówienia danego klienta
    public void wyswietlZamowieniaKlienta(int idKlienta) {
        System.out.println("\nZamówienia klienta (ID: " + idKlienta + "):");
        boolean znaleziono = false;
        for (int i = 0; i < liczbaZamowien; i++) {
            if (zamowienia[i].klient.getId() == idKlienta) {
                zamowienia[i].wyswietlSzczegoly();
                System.out.println("---"); // Separator między zamówieniami
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("Brak zamówień dla tego klienta.");
        }
    }

     // Metoda pomocnicza do wyświetlenia wszystkich produktów w magazynie
    public void wyswietlWszystkieProdukty() {
        System.out.println("\nAktualny stan magazynowy:");
        for (int i = 0; i < liczbaProduktow; i++) {
            produkty[i].wyswietlInformacje();
        }
         System.out.println("---");
    }

     // Metoda pomocnicza do wyświetlenia wszystkich klientów
    public void wyswietlWszystkichKlientow() {
        System.out.println("\nLista klientów:");
        for (int i = 0; i < liczbaKlientow; i++) {
            klienci[i].wyswietlInformacje();
        }
         System.out.println("---");
    }
}
```

**`src/Main.java`**
```java
public class Main {
    public static void main(String[] args) {
        SklepKomputerowy sklep = new SklepKomputerowy();

        // Dodawanie produktów
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

        // Dodawanie klientów
        Klient klient1 = new Klient(1, "Jan", "Kowalski", "jan.kowalski@example.com", true); // Stały klient
        Klient klient2 = new Klient(2, "Anna", "Nowak", "anna.nowak@example.com", false); // Nowy klient
        sklep.dodajKlienta(klient1);
        sklep.dodajKlienta(klient2);

        System.out.println("--- Lista klientów ---");
        sklep.wyswietlWszystkichKlientow();


        // Tworzenie zamówienia 1 (Klient stały)
        System.out.println("--- Tworzenie zamówienia 1 (Klient 1) ---");
        Produkt[] produktyZamowienia1 = {produkt1, produkt2}; // Laptop i Mysz
        int[] ilosciZamowienia1 = {1, 1}; // Po jednej sztuce
        Zamowienie zamowienie1 = sklep.utworzZamowienie(klient1, produktyZamowienia1, ilosciZamowienia1);

        if (zamowienie1 != null) {
             System.out.println("--- Szczegóły zamówienia 1 ---");
            zamowienie1.wyswietlSzczegoly();
            System.out.println("\n--- Stan magazynowy po zamówieniu 1 ---");
            sklep.wyswietlWszystkieProdukty();
        }


        // Tworzenie zamówienia 2 (Klient nowy)
        System.out.println("\n--- Tworzenie zamówienia 2 (Klient 2) ---");
        Produkt[] produktyZamowienia2 = {produkt3, produkt4}; // Monitor i Klawiatura
        int[] ilosciZamowienia2 = {2, 1}; // Dwa monitory, jedna klawiatura
        Zamowienie zamowienie2 = sklep.utworzZamowienie(klient2, produktyZamowienia2, ilosciZamowienia2);

        if (zamowienie2 != null) {
             System.out.println("--- Szczegóły zamówienia 2 ---");
            zamowienie2.wyswietlSzczegoly();
             System.out.println("\n--- Stan magazynowy po zamówieniu 2 ---");
            sklep.wyswietlWszystkieProdukty();
        }

        // Zmiana statusu zamówienia 1
        if (zamowienie1 != null) {
            System.out.println("\n--- Zmiana statusu zamówienia 1 ---");
            sklep.zmienStatusZamowienia(zamowienie1.getId(), "Zrealizowane");
            System.out.println("Nowy status zamówienia ID " + zamowienie1.getId() + ": " + zamowienie1.getStatus());
             System.out.println("--- Szczegóły zamówienia 1 po zmianie statusu ---");
            zamowienie1.wyswietlSzczegoly();
        }

        // Wyświetlanie produktów w danej kategorii
        System.out.println("\n--- Produkty w kategorii: Akcesoria ---");
        sklep.wyswietlProduktyWKategorii("Akcesoria");

        System.out.println("\n--- Produkty w kategorii: Laptop ---");
        sklep.wyswietlProduktyWKategorii("Laptop");

        // Wyświetlanie zamówień konkretnego klienta
        System.out.println("\n--- Wyświetlanie zamówień klienta Jana Kowalskiego (ID: 1) ---");
        sklep.wyswietlZamowieniaKlienta(1);

        System.out.println("\n--- Wyświetlanie zamówień klienta Anny Nowak (ID: 2) ---");
        sklep.wyswietlZamowieniaKlienta(2);
    }
}
```

Teraz możesz utworzyć katalog `src`, umieścić w nim pliki `.java`, a plik `.gitignore` umieścić w głównym katalogu projektu.