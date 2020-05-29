package model;

import painter.Painter;

public abstract class Element {
    private Painter painter;

    public Painter getPainter() {
        return painter;
    }

    public void setPainter(Painter painter) {
        this.painter = painter;
    }
}
