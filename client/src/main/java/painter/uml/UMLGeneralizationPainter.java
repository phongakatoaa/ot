package painter.uml;

import model.uml.UMLGeneralization;
import painter.Painter;

import java.awt.*;

public class UMLGeneralizationPainter extends Painter {
    private UMLGeneralization umlGeneralization;

    @Override
    public void paint(Graphics graphics) {

    }

    public UMLGeneralization getUmlGeneralization() {
        return umlGeneralization;
    }

    public void setUmlGeneralization(UMLGeneralization umlGeneralization) {
        this.umlGeneralization = umlGeneralization;
    }
}
