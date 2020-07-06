package com.uet.ui;

import com.uet.model.uml.UMLDiagram;
import com.uet.ot.UMLDocumentControl;
import com.uet.parser.UMLParser;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class MyMenuBar extends JMenuBar {
    private final JMenu menuFile;
    private final JMenu menuView;
    private final JMenu subMenuOpen;

    private final JMenuItem menuItemAbout;
    private final JMenuItem menuItemOpenFromDisk;
    private final JMenuItem menuItemOpenFromServer;
    private final JMenuItem menuItemSave;
    private final JMenuItem menuItemClose;
    private final JMenuItem menuItemNew;

    private final JFileChooser fileChooser;

    private XMLViewer xmlViewer;
    private MyFrame frame;

    public MyMenuBar() {
        super();
        menuFile = new JMenu("File");
        menuView = new JMenu("View");

        menuItemNew = new JMenuItem("New");
        menuItemSave = new JMenuItem("Save");
        subMenuOpen = new JMenu("Open");
        menuItemOpenFromDisk = new JMenuItem("From disk");
        menuItemOpenFromDisk.addActionListener(e -> onClickMenuItemOpenFromDisk());
        menuItemOpenFromServer = new JMenuItem("From server");
        subMenuOpen.add(menuItemOpenFromDisk);
        subMenuOpen.add(menuItemOpenFromServer);
        menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(e -> onClickMenuItemAbout());
        menuItemClose = new JMenuItem("Close");
        menuItemClose.addActionListener(e -> onClickMenuItemClose());

        menuFile.add(menuItemNew);
        menuFile.add(subMenuOpen);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemAbout);
        menuFile.add(menuItemClose);

        this.add(menuFile);
        this.add(menuView);

        this.fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("xml", "xml"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    }

    private void onClickMenuItemAbout() {
        JOptionPane.showMessageDialog(null, "UML Collaborative Editor - Ha Tuan Phong - UET 2020", "About", JOptionPane.PLAIN_MESSAGE);
    }


    private void onClickMenuItemOpenFromDisk() {
        int result = this.fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(file);

                UMLDocumentControl.getInstance().setDocument(document);
                UMLDocumentControl.getInstance().setXmlViewer(frame.getXmlViewer());

                UMLParser parser = new UMLParser();
                UMLDiagram diagram = parser.parse(document);
                //UMLDocumentControl.getInstance().setUmlDiagram(diagram);

                frame.getMyCanvas().setDiagram(diagram);
                frame.getXmlViewer().parseXML(file);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onClickMenuItemClose() {
        frame.dispose();
    }

    public XMLViewer getXmlViewer() {
        return xmlViewer;
    }

    public void setXmlViewer(XMLViewer xmlViewer) {
        this.xmlViewer = xmlViewer;
    }

    public MyFrame getFrame() {
        return frame;
    }

    public void setFrame(MyFrame frame) {
        this.frame = frame;
    }
}
