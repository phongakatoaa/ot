package ui;

import model.MyEditableElement;
import model.uml.UMLAttribute;
import model.uml.UMLClass;
import model.uml.UMLOperation;
import painter.uml.UMLClassPainter;

import javax.swing.*;

public class DiagramTextFieldPopupMenu extends JPopupMenu {
    private JMenuItem menuItemDeleteAttribute;
    private JMenuItem menuItemDeleteOperation;

    private final MyEditableElement editableElement;
    private final UMLClass umlClass;
    private MyCanvas canvas;

    public DiagramTextFieldPopupMenu(MyEditableElement editableElement, UMLClass umlClass) {
        this.editableElement = editableElement;
        this.umlClass = umlClass;
        if (editableElement instanceof UMLAttribute) {
            this.menuItemDeleteAttribute = new JMenuItem("Delete attribute");
            this.menuItemDeleteAttribute.addActionListener(e -> onClickDeleteAttribute());
            this.add(menuItemDeleteAttribute);
        } else if (editableElement instanceof UMLOperation) {
            this.menuItemDeleteOperation = new JMenuItem("Delete operation");
            this.menuItemDeleteOperation.addActionListener(e -> onClickDeleteOperation());
            this.add(menuItemDeleteOperation);
        }
    }

    private void onClickDeleteAttribute() {
        umlClass.removeAttribute((UMLAttribute) editableElement);
        ((UMLClassPainter) umlClass.getPainter()).revalidateProperties();
        if (canvas != null) {
            canvas.bindDiagram();
            canvas.repaint();
        }
    }

    private void onClickDeleteOperation() {
        umlClass.removeOperation((UMLOperation) editableElement);
        ((UMLClassPainter) umlClass.getPainter()).revalidateProperties();
        if (canvas != null) {
            canvas.bindDiagram();
            canvas.repaint();
        }
    }

    public void setCanvas(MyCanvas canvas) {
        this.canvas = canvas;
    }

    public MyCanvas getCanvas() {
        return canvas;
    }
}
