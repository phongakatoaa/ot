package model.uml.abstracts;

import model.Element;
import model.uml.UMLClass;

public abstract class UMLRelationship extends Element {
    private UMLClass first;
    private UMLClass second;

    public UMLRelationship(UMLClass first, UMLClass second) {
        this.first = first;
        this.second = second;
    }

    public UMLClass getFirst() {
        return first;
    }

    public void setFirst(UMLClass first) {
        this.first = first;
    }

    public UMLClass getSecond() {
        return second;
    }

    public void setSecond(UMLClass second) {
        this.second = second;
    }
}
