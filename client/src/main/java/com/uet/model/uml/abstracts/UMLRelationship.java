package com.uet.model.uml.abstracts;

import com.uet.model.MyElement;
import com.uet.model.uml.UMLClass;
import com.uet.model.uml.UMLRelationshipType;

public abstract class UMLRelationship extends MyElement {
    private UMLClass src;
    private UMLClass desc;
    private UMLRelationshipType type;

    public UMLRelationship(UMLClass src, UMLClass desc, UMLRelationshipType type) {
        this.src = src;
        this.desc = desc;
        this.type = type;
    }

    public UMLClass getSrc() {
        return src;
    }

    public void setSrc(UMLClass src) {
        this.src = src;
    }

    public UMLClass getDesc() {
        return desc;
    }

    public void setDesc(UMLClass desc) {
        this.desc = desc;
    }

    public UMLRelationshipType getType() {
        return type;
    }
}
