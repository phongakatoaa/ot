package painter.uml;

import model.uml.UMLClass;
import painter.Painter;
import ui.Constants;
import ui.DiagramTextField;
import ui.MyCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UMLClassPainter extends Painter {
    private final UMLClass umlClass;
    private final DiagramTextField classNameTextField;
    private final ArrayList<DiagramTextField> attributeTextFields;
    private final ArrayList<DiagramTextField> operationTextFields;
    private final Rectangle classRectangle;

    public UMLClassPainter(UMLClass umlClass) {
        this.umlClass = umlClass;
        this.classNameTextField = new DiagramTextField(umlClass.getName());
        this.classNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        this.attributeTextFields = new ArrayList<>();
        this.operationTextFields = new ArrayList<>();
        this.umlClass.getAttributes().forEach(attr -> attributeTextFields.add(new DiagramTextField(attr)));
        this.umlClass.getOperations().forEach(op -> operationTextFields.add(new DiagramTextField(op)));
        this.classRectangle = new Rectangle();
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        final ArrayList<DiagramTextField> textFields = new ArrayList<>();
        final int classX = umlClass.getX();
        final int classY = umlClass.getY();
        final int classWidth = 200;
        final int rowHeight = 30;
        final int rowNum = umlClass.getAttributes().size() + umlClass.getOperations().size() + 2;
        final int classHeight = rowNum * rowHeight;
        classRectangle.setBounds(classX, classY, classWidth, classHeight);
        if (isFocused()) {
            graphics2D.setColor(Constants.FOCUSED_BORDER_COLOR);
        } else if (isHovered()) {
            graphics2D.setColor(Constants.HOVERED_BORDER_COLOR);
        }
        graphics2D.draw(classRectangle);
        graphics2D.setColor(Constants.DEFAULT_BORDER_COLOR);

        final int textFieldX = classX + Constants.DEFAULT_HORIZONTAL_PADDING;
        final int textFieldWidth = classWidth - Constants.DEFAULT_HORIZONTAL_PADDING * 2;
        final int textFieldHeight = rowHeight - Constants.DEFAULT_VERTICAL_PADDING * 2;

        int textFieldY = classY + (rowHeight - textFieldHeight) / 2;
        classNameTextField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);

        final int lineX1 = classX;
        final int lineX2 = classX + classWidth;
        int lineY = classY + rowHeight;
        graphics2D.drawLine(lineX1, lineY, lineX2, lineY);

        for (int i = 0; i < umlClass.getAttributes().size(); i++) {
            int rowIndex = i + 1;
            textFieldY = classY + rowHeight * rowIndex + (rowHeight - textFieldHeight) / 2;
            DiagramTextField textField = attributeTextFields.get(i);
            textField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);
        }

        lineY += rowHeight * umlClass.getAttributes().size();
        graphics2D.drawLine(lineX1, lineY, lineX2, lineY);

        for (int i = 0; i < umlClass.getOperations().size(); i++) {
            int rowIndex = i + 1 + umlClass.getAttributes().size();
            textFieldY = classY + rowHeight * rowIndex + (rowHeight - textFieldHeight) / 2;
            DiagramTextField textField = operationTextFields.get(i);
            textField.setBounds(textFieldX, textFieldY, textFieldWidth, textFieldHeight);
        }

        lineY += rowHeight * umlClass.getOperations().size();
        graphics2D.drawLine(lineX1, lineY, lineX2, lineY);
    }

    @Override
    public boolean intersectMouse(int x, int y) {
        return (x >= classRectangle.getX() && x <= (classRectangle.getX() + classRectangle.getWidth()) &&
                y >= classRectangle.getY() && y <= (classRectangle.getY() + classRectangle.getHeight()));
    }

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void bindTextFields(MyCanvas myCanvas) {
        myCanvas.add(classNameTextField);
        this.attributeTextFields.forEach(myCanvas::add);
        this.operationTextFields.forEach(myCanvas::add);
    }

    public Rectangle getClassRectangle() {
        return classRectangle;
    }
}
