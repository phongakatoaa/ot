package com.uet.painter.uml;

import com.uet.model.uml.UMLGeneralization;
import com.uet.ui.Constants;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UMLGeneralizationPainter extends UMLRelationshipPainter {
    public UMLGeneralizationPainter(UMLGeneralization umlGeneralization) {
        super(umlGeneralization);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        System.out.println(isHovered());
        if (isHovered()) {
            graphics2D.setStroke(Constants.THICK_STROKE);
        } else {
            graphics2D.setStroke(Constants.THIN_STROKE);
        }
        super.paint(graphics2D);
        graphics2D.setStroke(Constants.MEDIUM_STROKE);
        AffineTransform oldTransform = graphics2D.getTransform();
        Paint oldPaint = graphics2D.getPaint();
        Polygon polygon = new Polygon();
        polygon.addPoint(x2, y2);
        polygon.addPoint(x2 - 10, y2 - 20);
        polygon.addPoint(x2 + 10, y2 - 20);
        graphics2D.rotate(Math.toRadians(rotateDegree), x2, y2);
        graphics2D.drawPolygon(polygon);
        graphics2D.setPaint(Constants.DEFAULT_BACKGROUND_COLOR);
        graphics2D.fill(polygon);
        graphics2D.setTransform(oldTransform);
        graphics2D.setPaint(oldPaint);
    }
}
