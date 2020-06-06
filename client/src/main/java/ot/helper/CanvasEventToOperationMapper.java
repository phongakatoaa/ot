package ot.helper;

import model.MyElement;
import model.uml.UMLAttribute;
import model.uml.UMLClass;
import model.uml.UMLOperation;
import model.uml.abstracts.UMLRelationship;
import org.jdom.Element;
import ot.operation.Delete;
import ot.operation.Insert;
import ot.operation.Operation;
import ot.operation.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CanvasEventToOperationMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final CanvasEventToOperationMapper instance = new CanvasEventToOperationMapper();

    private CanvasEventToOperationMapper() {

    }

    public static CanvasEventToOperationMapper getInstance() {
        return instance;
    }

    public Operation addClassEventOperation(UMLClass umlClass) {
        String newId = "c_" + formatter.format(LocalDateTime.now());
        umlClass.setId(newId);

        Element element = new Element("class");
        element.setAttribute("id", newId);
        element.setAttribute("name", umlClass.getName().getValue());
        element.setAttribute("x", Integer.toString(umlClass.getX()));
        element.setAttribute("y", Integer.toString(umlClass.getY()));

        return new Insert(element, null);
    }

    public Operation updateClassEventOperation(UMLClass umlClass) {
        HashMap<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("name", umlClass.getName().getValue());
        keyValuePairs.put("x", Integer.toString(umlClass.getX()));
        keyValuePairs.put("y", Integer.toString(umlClass.getY()));

        return new Update(umlClass.getId(), null, keyValuePairs);
    }

    public Operation addAttributeEventOperation(UMLAttribute umlAttribute, UMLClass umlClass) {
        String newId = "a_" + formatter.format(LocalDateTime.now());
        umlAttribute.setId(newId);

        Element element = new Element("attribute");
        element.setAttribute("id", newId);
        element.setText(umlAttribute.getValue());

        return new Insert(element, umlClass.getId());
    }

    public Operation updateAttributeEventOperation(UMLAttribute umlAttribute) {
        return new Update(umlAttribute.getId(), umlAttribute.getValue(), null);
    }

    public Operation addOperationEventOperation(UMLOperation umlOperation, UMLClass umlClass) {
        String newId = "o_" + formatter.format(LocalDateTime.now());
        umlOperation.setId(newId);

        Element element = new Element("operation");
        element.setAttribute("id", newId);
        element.setText(umlOperation.getValue());

        return new Insert(element, umlClass.getId());
    }

    public Operation updateOperationEventOperation(UMLOperation umlOperation) {
        return new Update(umlOperation.getId(), umlOperation.getValue(), null);
    }

    public Operation addRelationshipEventOperation(UMLRelationship umlRelationship) {
        String newId = "r_" + formatter.format(LocalDateTime.now());
        umlRelationship.setId(newId);

        Element element = new Element("relationship");
        element.setAttribute("id", newId);
        element.setAttribute("type", umlRelationship.getType().name().toLowerCase());
        element.setAttribute("source", umlRelationship.getSrc().getId());
        element.setAttribute("destination", umlRelationship.getDesc().getId());

        return new Insert(element, null);
    }

    public Operation deleteElementEventOperation(MyElement element) {
        return new Delete(element.getId());
    }
}
