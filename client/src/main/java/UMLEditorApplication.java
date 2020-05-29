import model.uml.UMLClass;
import model.uml.UMLGeneralization;
import model.uml.UMLRealization;
import painter.PainterFactoryException;
import painter.uml.UMLPainterFactory;
import ui.MyFrame;

import javax.swing.*;
import java.io.IOException;

public class UMLEditorApplication {
    public static void main(String[] args) {
        JFrame frame = new MyFrame();
        frame.setVisible(true);
//        UMLClass umlClass = new UMLClass("Person");
//        UMLClass umlClass1 = new UMLClass("Car");
//        UMLRealization umlRealization = new UMLRealization(umlClass, umlClass1);
//        System.out.println(umlClass.getClass().getSimpleName());
//        System.out.println(umlRealization.getClass().getSimpleName());
//
//        UMLPainterFactory factory = new UMLPainterFactory();
//        try {
//            System.out.println(factory.getPainter(umlRealization).getClass().getSimpleName());
//        } catch (PainterFactoryException ignored) {
//
//        }
    }
}
