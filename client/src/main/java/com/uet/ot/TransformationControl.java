package com.uet.ot;

import com.uet.ot.operation.Operation;

import java.util.ArrayList;

public class TransformationControl {
    private TransformationMatrix matrix;

    public TransformationControl() {
        this.matrix = new TransformationMatrix();
    }

    public Operation transform(ArrayList<Operation> documentState, ArrayList<Operation> remoteDocumentState, Operation operation) {
//        System.out.println("Original operation: " + operation);
//        StringBuilder dsString = new StringBuilder();
//        for (Operation o : remoteDocumentState) {
//            dsString.append(o.toString()).append("//");
//        }
//        System.out.println("Document state: " + dsString);
//        System.out.println();
        for (Operation o : documentState) {
            if (!remoteDocumentState.contains(o)) {
                System.out.println("Transforming " + operation.toString() + " against " + o);
                operation = matrix.transform(operation, o);
                if (operation == null) break;
                System.out.println("New operation: " + operation.toString());
            }
        }
        return operation;
    }
}
