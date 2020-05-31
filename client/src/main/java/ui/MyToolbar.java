package ui;

import model.uml.UMLRelationshipType;

import javax.swing.*;

public class MyToolbar extends JToolBar {
    private final JButton buttonNewClass;
    private final JButton buttonNewAttribute;
    private final JButton buttonNewOperation;
    private final JButton buttonUndo;
    private final JButton buttonRedo;
    private final JButton buttonNewDependency;
    private final JButton buttonNewGeneralization;
    private final JButton buttonNewRealization;
    private final JButton buttonNewAssociation;
    private final JButton buttonNewMultiplicity;
    private final JButton buttonNewAggregation;
    private final JButton buttonNewComposition;

    private final MyCanvas myCanvas;

    public MyToolbar(MyCanvas myCanvas) {
        super();
        this.myCanvas = myCanvas;
        this.setFloatable(false);

        buttonNewClass = new JButton("New Class");
        buttonNewAttribute = new JButton("New Attribute");
        buttonNewOperation = new JButton("New Operation");
        buttonUndo = new JButton("Undo");
        buttonRedo = new JButton("Redo");
        buttonNewDependency = new JButton("New Dependency");
        buttonNewGeneralization = new JButton("New Generalization");
        buttonNewRealization = new JButton("New Realization");
        buttonNewAssociation = new JButton("New Association");
        buttonNewMultiplicity = new JButton("New Multiplicity");
        buttonNewAggregation = new JButton("New Aggregation");
        buttonNewComposition = new JButton("New Composition");

        buttonNewClass.addActionListener(e -> myCanvas.onClickAddClass(50, 50));
        buttonNewGeneralization.addActionListener(e -> myCanvas.onClickCreateRelationship(UMLRelationshipType.GENERALIZATION));
        buttonNewRealization.addActionListener(e -> myCanvas.onClickCreateRelationship(UMLRelationshipType.REALIZATION));
        buttonNewAssociation.addActionListener(e -> myCanvas.onClickCreateRelationship(UMLRelationshipType.ASSOCIATION));

        this.add(buttonNewClass);
        this.add(buttonNewAttribute);
        this.add(buttonNewOperation);
        this.add(buttonNewDependency);
        this.add(buttonNewGeneralization);
        this.add(buttonNewRealization);
        this.add(buttonNewAssociation);
        this.add(buttonNewMultiplicity);
        this.add(buttonNewAggregation);
        this.add(buttonNewComposition);
        this.add(buttonUndo);
        this.add(buttonRedo);
    }
}
