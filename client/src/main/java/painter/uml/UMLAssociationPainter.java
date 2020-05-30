package painter.uml;

import model.uml.UMLAssociation;
import painter.Painter;

import java.awt.*;

public class UMLAssociationPainter extends Painter {
    private UMLAssociation umlAssociation;

    @Override
    public void paint(Graphics2D graphics2D) {

    }

    @Override
    public boolean intersectMouse(int x, int y) {
        return false;
    }

    public UMLAssociation getUmlAssociation() {
        return umlAssociation;
    }

    public void setUmlAssociation(UMLAssociation umlAssociation) {
        this.umlAssociation = umlAssociation;
    }
}
