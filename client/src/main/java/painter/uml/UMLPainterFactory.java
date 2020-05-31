package painter.uml;

import model.Element;
import model.uml.UMLAssociation;
import model.uml.UMLClass;
import model.uml.UMLGeneralization;
import model.uml.UMLRealization;
import painter.Painter;
import painter.PainterFactoryException;

public class UMLPainterFactory {
    public Painter getPainter(Element element) throws PainterFactoryException {
        Painter painter;
        switch (element.getClass().getSimpleName()) {
            case "UMLAssociation":
                painter = getAssociationPainter(element);
                break;
            case "UMLClass":
                painter = getClassPainter(element);
                break;
            case "UMLGeneralization":
                painter = getGeneralizationPainter(element);
                break;
            case "UMLRealization":
                painter = getRealizationPainter(element);
                break;
            default:
                throw new PainterFactoryException(element.getClass().getSimpleName());
        }
        element.setPainter(painter);
        return painter;
    }

    private UMLAssociationPainter getAssociationPainter(Element element) {
        return new UMLAssociationPainter((UMLAssociation) element);
    }

    private UMLGeneralizationPainter getGeneralizationPainter(Element element) {
        return new UMLGeneralizationPainter((UMLGeneralization) element);
    }

    private UMLRealizationPainter getRealizationPainter(Element element) {
        return new UMLRealizationPainter((UMLRealization) element);
    }

    private UMLClassPainter getClassPainter(Element element) {
        return new UMLClassPainter((UMLClass) element);
    }
}
