package model.uml;

import model.MyEditableElement;
import ot.UMLDocumentControl;
import ot.helper.CanvasEventToOperationMapper;
import ot.operation.Operation;

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
