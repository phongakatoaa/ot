package painter.uml;

import model.uml.UMLGeneralization;

import java.awt.*;

public class UMLGeneralizationPainter extends UMLRelationshipPainter {
    public UMLGeneralizationPainter(UMLGeneralization umlGeneralization) {
        super(umlGeneralization);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        super.paint(graphics2D);
    }

    @Override
    public boolean intersectMouse(int x, int y) {
        return false;
    }
}
