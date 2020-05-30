package painter.uml;

import model.uml.UMLRealization;
import painter.Painter;

import java.awt.*;

public class UMLRealizationPainter extends Painter {
    private UMLRealization umlRealization;

    @Override
    public void paint(Graphics2D graphics2D) {

    }

    @Override
    public boolean intersectMouse(int x, int y) {
        return false;
    }

    public UMLRealization getUmlRealization() {
        return umlRealization;
    }

    public void setUmlRealization(UMLRealization umlRealization) {
        this.umlRealization = umlRealization;
    }
}
