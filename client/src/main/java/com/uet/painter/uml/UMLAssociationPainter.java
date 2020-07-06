package com.uet.painter.uml;

import com.uet.model.uml.UMLAssociation;
import com.uet.ui.Constants;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class UMLAssociationPainter extends UMLRelationshipPainter {
    public UMLAssociationPainter(UMLAssociation umlAssociation) {
        super(umlAssociation);
    }

    @Override
    public void paint(Graphics2D graphics2D) {
        if (isHovered()) {
            graphics2D.setStroke(Constants.THICK_STROKE);
        } else {
            graphics2D.setStroke(Constants.THIN_STROKE);
        }
        super.paint(graphics2D);
    }
}
