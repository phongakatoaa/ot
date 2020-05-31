package painter.uml;

import model.MyElement;
import model.uml.UMLAssociation;
import model.uml.UMLClass;
import model.uml.UMLGeneralization;
import model.uml.UMLRealization;
import painter.Painter;

public class UMLPainterFactory {
    public Painter createPainter(MyElement myElement) {
        Painter painter = null;
        switch (myElement.getClass().getSimpleName()) {
            case "UMLAssociation":
                painter = new UMLAssociationPainter((UMLAssociation) myElement);
                break;
            case "UMLClass":
                painter = new UMLClassPainter((UMLClass) myElement);
                break;
            case "UMLGeneralization":
                painter = new UMLGeneralizationPainter((UMLGeneralization) myElement);
                break;
            case "UMLRealization":
                painter = new UMLRealizationPainter((UMLRealization) myElement);
                break;
            default:
                //throw new PainterFactoryException(myElement.getClass().getSimpleName());
                break;
        }
        myElement.setPainter(painter);
        return painter;
    }
}
