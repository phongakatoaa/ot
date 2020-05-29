package model.uml;

import model.uml.abstracts.UMLDirectedRelationship;

public class UMLGeneralization extends UMLDirectedRelationship {

    public UMLGeneralization(UMLClass first, UMLClass second) {
        super(first, second);
    }
}
