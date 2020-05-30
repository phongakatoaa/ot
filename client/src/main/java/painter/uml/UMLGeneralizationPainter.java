package painter.uml;

import model.uml.UMLGeneralization;
import painter.Painter;

import java.awt.*;

public class UMLGeneralizationPainter extends Painter {
    private UMLGeneralization umlGeneralization;

    @Override
    public void paint(Graphics2D graphics2D) {

    }

    @Override
    public boolean intersectMouse(int x, int y) {
        return false;
    }

    public UMLGeneralization getUmlGeneralization() {
        return umlGeneralization;
    }

    public void setUmlGeneralization(UMLGeneralization umlGeneralization) {
        this.umlGeneralization = umlGeneralization;
    }
}
