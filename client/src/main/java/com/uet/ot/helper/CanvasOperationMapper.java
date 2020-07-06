package com.uet.ot.helper;

import com.uet.model.MyElement;
import com.uet.model.uml.UMLAttribute;
import com.uet.model.uml.UMLClass;
import com.uet.model.uml.UMLOperation;
import com.uet.model.uml.abstracts.UMLRelationship;
import com.uet.ot.operation.Delete;
import com.uet.ot.operation.Insert;
import com.uet.ot.operation.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CanvasOperationMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static final CanvasOperationMapper instance = new CanvasOperationMapper();

    public static CanvasOperationMapper getInstance() {
        return instance;
    }

    private String userId;

    private CanvasOperationMapper() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Insert addClassEventOperation(UMLClass umlClass) {
        String newId = "c_" + formatter.format(LocalDateTime.now());
        umlClass.setId(newId);

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("id", newId);
        attributes.put("name", umlClass.getName().getValue());
        attributes.put("x", Integer.toString(umlClass.getX()));
        attributes.put("y", Integer.toString(umlClass.getY()));

        return new Insert(userId, LocalDateTime.now().toString(), "class", "", attributes, -1, null);
    }

    public Update updateClassEventOperation(UMLClass umlClass) {
        HashMap<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("name", umlClass.getName().getValue());
        keyValuePairs.put("x", Integer.toString(umlClass.getX()));
        keyValuePairs.put("y", Integer.toString(umlClass.getY()));

        return new Update(userId, LocalDateTime.now().toString(), umlClass.getId(), null, keyValuePairs);
    }

    public Insert addAttributeEventOperation(UMLAttribute umlAttribute, UMLClass umlClass) {
        String newId = "a_" + formatter.format(LocalDateTime.now());
        umlAttribute.setId(newId);

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("id", newId);

        int position = umlClass.getAttributes().size() + umlClass.getOperations().size() - 1;

        return new Insert(userId, LocalDateTime.now().toString(), "attribute", umlAttribute.getValue(), attributes, position, umlClass.getId());
    }

    public Update updateAttributeEventOperation(UMLAttribute umlAttribute) {
        return new Update(userId, LocalDateTime.now().toString(), umlAttribute.getId(), umlAttribute.getValue(), null);
    }

    public Insert addOperationEventOperation(UMLOperation umlOperation, UMLClass umlClass) {
        String newId = "o_" + formatter.format(LocalDateTime.now());
        umlOperation.setId(newId);

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("id", newId);

        int position = umlClass.getAttributes().size() + umlClass.getOperations().size() - 1;

        return new Insert(userId, LocalDateTime.now().toString(), "operation", umlOperation.getValue(), attributes, position, umlClass.getId());
    }

    public Update updateOperationEventOperation(UMLOperation umlOperation) {
        return new Update(userId, LocalDateTime.now().toString(), umlOperation.getId(), umlOperation.getValue(), null);
    }

    public Insert addRelationshipEventOperation(UMLRelationship umlRelationship) {
        String newId = "r_" + formatter.format(LocalDateTime.now());
        umlRelationship.setId(newId);

        HashMap<String, String> attributes = new HashMap<>();
        attributes.put("id", newId);
        attributes.put("type", umlRelationship.getType().name().toLowerCase());
        attributes.put("source", umlRelationship.getSrc().getId());
        attributes.put("destination", umlRelationship.getDesc().getId());

        return new Insert(userId, LocalDateTime.now().toString(), "relationship", "", attributes, -1, null);
    }

    public Delete deleteElementEventOperation(MyElement element) {
        return new Delete(userId, LocalDateTime.now().toString(), element.getId());
    }
}
