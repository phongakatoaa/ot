package com.uet.model.uml;

import com.uet.model.MyEditableElement;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasEventToOperationMapper;
import com.uet.ot.operation.Operation;

public class UMLOperation extends MyEditableElement {
    public UMLOperation() {
    }

    public UMLOperation(String value) {
        super(value);
    }

    public UMLOperation(String id, String value) {
        super(value);
        this.id = id;
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);

        Operation operation = CanvasEventToOperationMapper.getInstance().updateOperationEventOperation(this);
        UMLDocumentControl.getInstance().applyOperation(operation);
    }
}
