
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
    public String getStatus() { return status; }
    public String getDataZamowienia() { return dataZamowienia; }

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
        System.out.println("Produkty:");
        for (int i = 0; i < produkty.length; i++) {
            System.out.println(" - " + produkty[i].getNazwa() + " (Cena jedn.: " + String.format("%.2f", cenyPoZnizce[i]) + ") x " + ilosci[i] + " = " + String.format("%.2f", cenyPoZnizce[i] * ilosci[i]));
        }
        System.out.println("Łączna wartość zamówienia: " + String.format("%.2f", obliczWartoscZamowienia()));
    }
}
