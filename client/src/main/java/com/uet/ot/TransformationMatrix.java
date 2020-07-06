package com.uet.ot;

import com.uet.ot.operation.Delete;
import com.uet.ot.operation.Insert;
import com.uet.ot.operation.Operation;
import com.uet.ot.operation.Update;

public class TransformationMatrix {
    public Operation transform(Operation o1, Operation o2) {
        if (o1 instanceof Insert) {
            if (o2 instanceof Insert) {
                return transform((Insert) o1, (Insert) o2);
            } else if (o2 instanceof Update) {
                return transform((Insert) o1, (Update) o2);
            } else if (o2 instanceof Delete) {
                return transform((Insert) o1, (Delete) o2);
            }
        } else if (o1 instanceof Update) {
            if (o2 instanceof Insert) {
                return transform((Update) o1, (Insert) o2);
            } else if (o2 instanceof Update) {
                return transform((Update) o1, (Update) o2);
            } else if (o2 instanceof Delete) {
                return transform((Update) o1, (Delete) o2);
            }
        } else if (o1 instanceof Delete) {
            if (o2 instanceof Insert) {
                return transform((Delete) o1, (Insert) o2);
            } else if (o2 instanceof Update) {
                return transform((Delete) o1, (Update) o2);
            } else if (o2 instanceof Delete) {
                return transform((Delete) o1, (Delete) o2);
            }
        }
        return null;
    }

    public Operation transform(Insert o1, Insert o2) {
        if (o1.getName().equals(o2.getName())
                && (o1.getName().equals("operation") || o1.getName().equals("attribute"))
                && o1.getParentId().equals(o2.getParentId())) {
            if (o1.getTimestamp().compareTo(o2.getTimestamp()) >= 0) {
                if (o1.getPosition() >= o2.getPosition()) {
                    return new Insert(o1.getUserId(), o1.getTimestamp(), o1.getName(), o1.getText(), o1.getKeyValuePairs(), o1.getPosition() + 1, o1.getParentId());
                }
            }
        }
        return o1;
    }

    public Operation transform(Insert o1, Update o2) {
        return o1;
    }

    public Operation transform(Insert o1, Delete o2) {
        return o1;
    }

    public Operation transform(Update o1, Update o2) {
        if (o1.getNodeId().equals(o2.getNodeId())) {
            if (o1.getTimestamp().compareTo(o2.getTimestamp()) >= 0) {
                return o1;
            } else {
                return null;
            }
        }
        return o1;
    }

    public Operation transform(Update o1, Insert o2) {
        return o1;
    }

    public Operation transform(Update o1, Delete o2) {
        if (o1.getNodeId().equals(o2.getNodeId())) {
            return null;
        }
        return o1;
    }

    public Operation transform(Delete o1, Insert o2) {
        return o1;
    }

    public Operation transform(Delete o1, Update o2) {
        return o1;
    }

    public Operation transform(Delete o1, Delete o2) {
        if (o1.getNodeId().equals(o2.getNodeId())) {
            return null;
        }
        return o1;
    }
}
