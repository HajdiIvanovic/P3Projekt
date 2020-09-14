package ffos.hivanovicp3.redateljiapk;

public class OntologijaRedatelji {

    private int sifra;
    private String redatelj;
    private String najpoznatijiRadovi;
    private String opisZanimanja;
    private String rdfOpis;

    public OntologijaRedatelji(int sifra, String redatelj, String najpoznatijiRadovi, String opisZanimanja, String rdfOpis) {
        this.sifra = sifra;
        this.redatelj = redatelj;
        this.najpoznatijiRadovi = najpoznatijiRadovi;
        this.opisZanimanja = opisZanimanja;
        this.rdfOpis = rdfOpis;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public String getRedatelj() {
        return redatelj;
    }

    public void setRedatelj(String redatelj) {
        this.redatelj = redatelj;
    }

    public String getNajpoznatijiRadovi() {
        return najpoznatijiRadovi;
    }

    public void setNajpoznatijiRadovi(String najpoznatijiRadovi) {
        this.najpoznatijiRadovi = najpoznatijiRadovi;
    }

    public String getOpisZanimanja() {
        return opisZanimanja;
    }

    public void setOpisZanimanja(String opisZanimanja) {
        this.opisZanimanja = opisZanimanja;
    }

    public String getRdfOpis() {
        return rdfOpis;
    }

    public void setRdfOpis(String rdfOpis) {
        this.rdfOpis = rdfOpis;
    }
}
