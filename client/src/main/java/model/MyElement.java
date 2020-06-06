package model;

import painter.Painter;

public abstract class MyElement {
    protected String id;
    private Painter painter;

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
