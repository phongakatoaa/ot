package com.uet.model.uml;

import com.uet.model.MyEditableElement;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasOperationMapper;
import com.uet.ot.operation.Operation;

public class UMLAttribute extends MyEditableElement {
    public UMLAttribute() {
        super();
    }

    public UMLAttribute(String id, String value) {
        super(value);
        this.id = id;
    }

    public UMLAttribute(String value) {
        super(value);
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);

        Operation operation = CanvasOperationMapper.getInstance().updateAttributeEventOperation(this);
        UMLDocumentControl.getInstance().applyLocal(operation);
    }
}
