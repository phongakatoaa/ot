package com.uet.ot;

import com.uet.config.UserConfig;
import com.uet.model.uml.UMLDiagram;
import com.uet.ot.helper.UMLElementFinder;
import com.uet.ot.operation.Operation;
import com.uet.parser.MyXMLParserException;
import com.uet.parser.UMLParser;
import com.uet.ui.MyCanvas;
import com.uet.ui.OperationLogger;
import com.uet.ui.XMLViewer;
import com.uet.websocket.MySocketClient;
import org.jdom2.Document;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class UMLDocumentControl {
    private static final UMLDocumentControl instance = new UMLDocumentControl();

    private XMLViewer xmlViewer;
    private Document document;
    private MySocketClient socketClient;
    private MyCanvas myCanvas;
    private OperationLogger operationLogger;

    private final UMLElementFinder finder;
    private final ArrayList<Operation> documentState;
    private final TransformationControl transformationControl;


    private UMLDocumentControl() {
        finder = new UMLElementFinder();
        documentState = new ArrayList<>();
        transformationControl = new TransformationControl();
    }

    public static UMLDocumentControl getInstance() {
        return instance;
    }

    public Document getDocument() {
        return document;
    }

    public XMLViewer getXmlViewer() {
        return xmlViewer;
    }

    public void setXmlViewer(XMLViewer xmlViewer) {
        this.xmlViewer = xmlViewer;
    }

    public void setDocument(Document document) {
        this.document = document;
        documentState.clear();
    }

    public MySocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(MySocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public MyCanvas getMyCanvas() {
        return myCanvas;
    }

    public void setMyCanvas(MyCanvas myCanvas) {
        this.myCanvas = myCanvas;
    }

    public void applyLocal(Operation operation) {
        operation.apply(document, finder);
        xmlViewer.parseXML(document);
        socketClient.sendOperation(operation, documentState);
        documentState.add(operation);
        operationLogger.appendOperation(operation);
    }

    public void applyRemote(Operation operation, ArrayList<Operation> remoteDS) throws MyXMLParserException {
        if (!operation.getUserId().equals(UserConfig.getInstance().getUserId())) {
            Operation transformed = transformationControl.transform(documentState, remoteDS, operation);
            if (transformed != null) {
                transformed.apply(document, finder);

                xmlViewer.parseXML(document);
                UMLDiagram umlDiagram = new UMLParser().parse(document);
                myCanvas.setDiagram(umlDiagram);
                myCanvas.repaint();

                documentState.add(operation);
                operationLogger.appendOperation(operation);
            }
        }
    }

    public void setOperationLogger(OperationLogger operationLogger) {
        this.operationLogger = operationLogger;
    }
}

