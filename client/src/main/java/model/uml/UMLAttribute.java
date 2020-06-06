package model.uml;

import model.MyEditableElement;
import ot.UMLDocumentControl;
import ot.helper.CanvasEventToOperationMapper;
import ot.operation.Operation;

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

        Operation operation = CanvasEventToOperationMapper.getInstance().updateAttributeEventOperation(this);
        UMLDocumentControl.getInstance().applyOperation(operation);
    }
}
