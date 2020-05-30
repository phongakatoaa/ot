package model.uml.abstracts;

import model.Element;
import model.uml.UMLClass;

public abstract class UMLRelationship extends Element {
    private UMLClass src;
    private UMLClass desc;

    public UMLRelationship(UMLClass src, UMLClass desc) {
        this.src = src;
        this.desc = desc;
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
}
