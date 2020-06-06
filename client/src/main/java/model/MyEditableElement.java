package model;

import ot.helper.CanvasEventToOperationMapper;
import ot.operation.Operation;

public abstract class MyEditableElement extends MyElement {
    private String value;

    public MyEditableElement() {

    }

    public MyEditableElement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
