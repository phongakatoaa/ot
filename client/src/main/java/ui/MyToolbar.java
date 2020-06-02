package ui;

import model.uml.UMLClass;
import model.uml.UMLRelationshipType;
import painter.uml.UMLClassPainter;
import painter.uml.UMLPainterFactory;

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

    private final MyCanvas canvas;

    public MyToolbar(MyCanvas canvas) {
        super();
        this.canvas = canvas;
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

        buttonNewAttribute.setVisible(false);
        buttonNewOperation.setVisible(false);
        buttonNewMultiplicity.setVisible(false);
        buttonNewAggregation.setVisible(false);
        buttonNewComposition.setVisible(false);

        buttonNewClass.addActionListener(e -> onClickAddClass());
        buttonNewGeneralization.addActionListener(e -> onClickCreateRelationship(UMLRelationshipType.GENERALIZATION));
        buttonNewRealization.addActionListener(e -> onClickCreateRelationship(UMLRelationshipType.REALIZATION));
        buttonNewAssociation.addActionListener(e -> onClickCreateRelationship(UMLRelationshipType.ASSOCIATION));
        buttonNewDependency.addActionListener(e -> onClickCreateRelationship(UMLRelationshipType.DEPENDENCY));

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

    private void onClickAddClass() {
        String errorMessage = null;
        if (canvas.getDiagram() == null) {
            errorMessage = "Please create/open a diagram first";
        } else {
            String value = JOptionPane.showInputDialog(null, "Enter new class name").trim();
            if (value.length() == 0) {
                errorMessage = "Class name cannot be empty";
            } else {
                UMLClass umlClass = new UMLClass(value);
                umlClass.setPosition(50, 50);
                UMLPainterFactory painterFactory = new UMLPainterFactory();
                UMLClassPainter painter = (UMLClassPainter) painterFactory.createPainter(umlClass);
                canvas.getDiagram().addClass(umlClass);
                canvas.bindDiagram();
                this.repaint();
            }
        }
        if (errorMessage != null) {
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onClickCreateRelationship(UMLRelationshipType type) {
        CreateRelationshipMouseListener l = new CreateRelationshipMouseListener(canvas, type);
        canvas.unbindMouseListeners();
        canvas.addMouseListener(l);
        canvas.addMouseMotionListener(l);
    }
}
