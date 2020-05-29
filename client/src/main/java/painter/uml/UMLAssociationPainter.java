package painter.uml;

import model.uml.UMLAssociation;
import painter.Painter;

import java.awt.*;

public class UMLAssociationPainter extends Painter {
    private UMLAssociation umlAssociation;

    @Override
    public void paint(Graphics graphics) {

    }

    public UMLAssociation getUmlAssociation() {
        return umlAssociation;
    }

    public void setUmlAssociation(UMLAssociation umlAssociation) {
        this.umlAssociation = umlAssociation;
    }
}
