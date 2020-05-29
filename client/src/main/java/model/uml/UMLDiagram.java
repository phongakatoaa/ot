package model.uml;

import model.uml.abstracts.UMLRelationship;

import java.util.ArrayList;

public class UMLDiagram {
    private ArrayList<UMLClass> umlClasses;
    private ArrayList<UMLRelationship> umlRelationships;
    private String name;

    public UMLDiagram(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UMLClass> getUmlClasses() {
        return umlClasses;
    }

    public ArrayList<UMLRelationship> getUmlRelationships() {
        return umlRelationships;
    }

    public void addUMLClass(UMLClass umlClass) {
        this.umlClasses.add(umlClass);
    }

    public void addRelationship(UMLRelationship umlRelationship) {
        this.umlRelationships.add(umlRelationship);
    }
}
