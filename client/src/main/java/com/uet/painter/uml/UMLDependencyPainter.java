package com.uet.painter.uml;

import com.uet.model.uml.abstracts.UMLRelationship;
import com.uet.ui.Constants;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UMLDependencyPainter extends UMLRelationshipPainter {
    public UMLDependencyPainter(UMLRelationship umlRelationship) {
        super(umlRelationship);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        if (isHovered()) {
            graphics2D.setStroke(Constants.DASHED_THICK_STROKE);
        } else {
            graphics2D.setStroke(Constants.DASHED_THIN_STROKE);
        }
        super.paint(graphics2D);
        graphics2D.setStroke(Constants.THIN_STROKE);
        AffineTransform oldTransform = graphics2D.getTransform();
        graphics2D.rotate(Math.toRadians(rotateDegree), x2, y2);
        graphics2D.drawLine(x2, y2, x2 - 10, y2 - 20);
        graphics2D.drawLine(x2, y2, x2 + 10, y2 - 20);
        graphics2D.setTransform(oldTransform);
    }
}
