public class Deskovka {
    private String nazevHry;
    private boolean zakoupeno;
    private int oblibenost;

    public Deskovka(String nazevHry, boolean zakoupeno, int oblibenost) {
        this.nazevHry = nazevHry;
        this.zakoupeno = zakoupeno;
        this.oblibenost = oblibenost;
    }

    public Deskovka(String nazevHry) {
        this(nazevHry, false, 1);
    }

    public String getNazevHry() {
        return nazevHry;
    }

    public void setNazevHry(String nazevHry) {
        this.nazevHry = nazevHry;
    }

    public boolean isZakoupeno() {
        return zakoupeno;
    }

    public void setZakoupeno(boolean zakoupeno) {
        this.zakoupeno = zakoupeno;
    }

    public int getOblibenost() {
        return oblibenost;
    }

    public void setOblibenost(int oblibenost) {
        this.oblibenost = oblibenost;
    }
}