
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
