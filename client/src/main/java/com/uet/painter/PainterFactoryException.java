package com.uet.painter;

public class PainterFactoryException extends Exception {
    public PainterFactoryException(String type) {
        super("Painter of type " + type + " not found");
    }
}
