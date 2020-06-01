package model.uml;

import model.uml.abstracts.UMLDirectedRelationship;

public class UMLRealization extends UMLDirectedRelationship {
    public UMLRealization(UMLClass first, UMLClass second) {
        super(first, second);
    }
}
