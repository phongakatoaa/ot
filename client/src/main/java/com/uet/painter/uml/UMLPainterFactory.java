package com.uet.painter.uml;

import com.uet.model.MyElement;
import com.uet.model.uml.*;
import com.uet.model.uml.abstracts.UMLRelationship;
import com.uet.painter.Painter;

public class UMLPainterFactory {
    public Painter createPainter(MyElement myElement) {
        Painter painter = null;
        if (myElement instanceof UMLClass) {
            painter = new UMLClassPainter((UMLClass) myElement);
        } else if (myElement instanceof UMLRelationship) {
            switch (((UMLRelationship) myElement).getType()) {
                case ASSOCIATION:
                    painter = new UMLAssociationPainter((UMLAssociation) myElement);
                    break;
                case GENERALIZATION:
                    painter = new UMLGeneralizationPainter((UMLGeneralization) myElement);
                    break;
                case REALIZATION:
                    painter = new UMLRealizationPainter((UMLRealization) myElement);
                    break;
                case DEPENDENCY:
                    painter = new UMLDependencyPainter((UMLDependency) myElement);
                    break;
                default:
                    break;
            }
        }
        //System.out.println(com.uet.painter);
        myElement.setPainter(painter);
        return painter;
    }
}
