package com.uet.painter;


import java.awt.*;

public abstract class Painter {
    private boolean focused;
    private boolean hovered;
//    private boolean dragged;

    public abstract void paint(Graphics2D graphics2D);

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean isHovered() {
        return hovered;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
    }

//    public boolean isDragged() {
//        return dragged;
//    }
//
//    public void setDragged(boolean dragged) {
//        this.dragged = dragged;
//    }

    public abstract boolean intersectMouse(int x, int y);
}
