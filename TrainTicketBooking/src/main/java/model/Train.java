package model;

public class Train {
    private static int MAX_ID;
    private int id;

    public Train() {
        this.id = ++MAX_ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
