package com.uet.model.uml;

import com.uet.model.uml.abstracts.UMLRelationship;

public class UMLDependency extends UMLRelationship {
    public UMLDependency(UMLClass src, UMLClass desc) {
        super(src, desc, UMLRelationshipType.DEPENDENCY);
    }
}
