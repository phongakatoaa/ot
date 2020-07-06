package com.uet.painter.uml;

import com.uet.model.uml.UMLClass;
import com.uet.painter.Painter;
import com.uet.ui.Constants;
import com.uet.ui.DiagramTextField;
import com.uet.ui.MyCanvas;

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
        this.classNameTextField = new DiagramTextField(umlClass.getName(), null);
        this.classNameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        this.attributeTextFields = new ArrayList<>();
        this.operationTextFields = new ArrayList<>();
        this.umlClass.getAttributes().forEach(attr -> attributeTextFields.add(new DiagramTextField(attr, this.umlClass)));
        this.umlClass.getOperations().forEach(op -> operationTextFields.add(new DiagramTextField(op, this.umlClass)));
        this.classRectangle = new Rectangle();
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        final ArrayList<DiagramTextField> textFields = new ArrayList<>();
        final int classX = umlClass.getX();
        final int classY = umlClass.getY();
        final int classWidth = 400;
        final int rowHeight = 30;
        final int rowNum = umlClass.getAttributes().size() + umlClass.getOperations().size() + 2;
        final int classHeight = rowNum * rowHeight;
        classRectangle.setBounds(classX, classY, classWidth, classHeight);
        if (isFocused()) {
            //graphics2D.setColor(Constants.FOCUSED_BORDER_COLOR);
            graphics2D.setStroke(Constants.THICK_STROKE);
        } else if (isHovered()) {
            graphics2D.setStroke(Constants.MEDIUM_STROKE);
        }
        graphics2D.draw(classRectangle);
        graphics2D.setStroke(Constants.THIN_STROKE);
        //graphics2D.setColor(Constants.DEFAULT_BORDER_COLOR);

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
        myCanvas.add(this.classNameTextField);
        this.attributeTextFields.forEach(attr -> {
            attr.getPopupMenu().setCanvas(myCanvas);
            myCanvas.add(attr);
        });
        this.operationTextFields.forEach(op -> {
            op.getPopupMenu().setCanvas(myCanvas);
            myCanvas.add(op);
        });
    }

    public Rectangle getClassRectangle() {
        return classRectangle;
    }

    public void revalidateProperties() {
        this.attributeTextFields.clear();
        this.operationTextFields.clear();
        this.umlClass.getAttributes().forEach(attr -> attributeTextFields.add(new DiagramTextField(attr, this.umlClass)));
        this.umlClass.getOperations().forEach(op -> operationTextFields.add(new DiagramTextField(op, this.umlClass)));
    }
}
