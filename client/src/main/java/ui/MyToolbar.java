package ui;

import javax.swing.*;

public class MyToolbar extends JToolBar {
    private JButton buttonNewClass;
    private JButton buttonNewAttribute;
    private JButton buttonNewOperation;
    private JButton buttonUndo;
    private JButton buttonRedo;
    private JButton buttonNewDependency;
    private JButton buttonNewGeneralization;
    private JButton buttonNewAssociation;
    private JButton buttonNewMultiplicity;
    private JButton buttonNewAggregation;
    private JButton buttonNewComposition;

    public MyToolbar() {
        super();
        this.setFloatable(false);

        buttonNewClass = new JButton("New Class");
        buttonNewAttribute = new JButton("New Attribute");
        buttonNewOperation = new JButton("New Operation");
        buttonUndo = new JButton("Undo");
        buttonRedo = new JButton("Redo");
        buttonNewDependency = new JButton("New Dependency");
        buttonNewGeneralization = new JButton("New Generalization");
        buttonNewAssociation = new JButton("New Association");
        buttonNewMultiplicity = new JButton("New Multiplicity");
        buttonNewAggregation = new JButton("New Aggregation");
        buttonNewComposition = new JButton("New Composition");

        this.add(buttonNewClass);
        this.add(buttonNewAttribute);
        this.add(buttonNewOperation);
        this.add(buttonNewDependency);
        this.add(buttonNewGeneralization);
        this.add(buttonNewAssociation);
        this.add(buttonNewMultiplicity);
        this.add(buttonNewAggregation);
        this.add(buttonNewComposition);
        this.add(buttonUndo);
        this.add(buttonRedo);
    }
}
