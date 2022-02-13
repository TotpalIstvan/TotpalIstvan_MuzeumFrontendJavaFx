package hu.petrik.muzeumfrontendjavafx;

public class Festmeny {
    private int id;
    private String cim;
    private int ev;
    private boolean kiallitva;

    public Festmeny(int id, String cim, int ev, boolean kiallitva) {
        this.id = id;
        this.cim = cim;
        this.ev = ev;
        this.kiallitva = kiallitva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcim() {
        return cim;
    }

    public void setcim(String cim) {
        this.cim = cim;
    }

    public int getev() {
        return ev;
    }

    public void setev(int ev) {
        this.ev = ev;
    }

    public boolean isKiallitva() {
        return kiallitva;
    }

    public void setKiallitva(boolean kiallitva) {
        this.kiallitva = kiallitva;
    }
}
