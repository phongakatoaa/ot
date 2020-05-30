package model;

public abstract class EditableElement extends Element {
    private String value;

    public EditableElement() {

    }

    public EditableElement(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
