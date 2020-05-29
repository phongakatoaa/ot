package painter.uml;

import model.uml.UMLRealization;
import painter.Painter;

import java.awt.*;

public class UMLRealizationPainter extends Painter {
    private UMLRealization umlRealization;

    @Override
    public void paint(Graphics graphics) {

    }

    public UMLRealization getUmlRealization() {
        return umlRealization;
    }

    public void setUmlRealization(UMLRealization umlRealization) {
        this.umlRealization = umlRealization;
    }
}
