package painter.uml;

import model.uml.UMLClass;
import painter.Painter;

import java.awt.*;

public class UMLClassPainter extends Painter {
    private UMLClass umlClass;
    private static final int RECT_WIDTH = 300;

    @Override
    public void paint(Graphics graphics) {

    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
    }
}
