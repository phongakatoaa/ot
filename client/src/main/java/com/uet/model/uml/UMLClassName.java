package com.uet.model.uml;

import com.uet.model.MyEditableElement;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasOperationMapper;
import com.uet.ot.operation.Operation;

public class UMLClassName extends MyEditableElement {
    private UMLClass umlClass;

    public UMLClassName(String value, UMLClass umlClass) {
        super(value);
        this.umlClass = umlClass;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);

        Operation operation = CanvasOperationMapper.getInstance().updateClassEventOperation(umlClass);
        UMLDocumentControl.getInstance().applyLocal(operation);
    }
}
