package com.uet.ui;

import com.uet.model.uml.UMLClass;
import com.uet.painter.Painter;
import com.uet.painter.uml.UMLClassPainter;

public class TranslateControl {
    public void translate(int x1, int y1, int x2, int y2, Painter painter) {
        switch (painter.getClass().getSimpleName()) {
            case "UMLClassPainter":
                translateUMLClass(x1, y1, x2, y2, (UMLClassPainter) painter);
                break;
            case "UMLAssociationPainter":
            case "UMLGeneralizationPainter":
            case "UMLRealizationPainter":
                translateUMLRelationship(x1, y1, x2, y2, painter);
                break;
            default:
                break;
        }
    }

    private void translateUMLClass(int x1, int y1, int x2, int y2, UMLClassPainter painter) {
        UMLClass umlClass = painter.getUmlClass();
        umlClass.setPosition(umlClass.getX() + (x2 - x1), umlClass.getY() + (y2 - y1), false);
    }

    private void translateUMLRelationship(int x1, int y1, int x2, int y2, Painter painter) {

    }
}
