package hu.petrik.muzeumfrontendjavafx;

public class Szobrok {
    private int id;
    private String szemely;
    private int magassag;
    private int ar;

    public Szobrok(int id, String szemely, int magassag, int ar) {
        this.id = id;
        this.szemely = szemely;
        this.magassag = magassag;
        this.ar = ar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSzemely() {
        return szemely;
    }

    public void setSzemely(String szemely) {
        this.szemely = szemely;
    }

    public int getMagassag() {
        return magassag;
    }

    public void setMagassag(int magassag) {
        this.magassag = magassag;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }
}
