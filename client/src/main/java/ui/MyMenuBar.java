package ui;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {
    private JMenu menuFile;
    private JMenu menuView;

    private JMenu subMenuOpen;

    private JMenuItem menuItemAbout;
    private JMenuItem menuItemOpenFromDisk;
    private JMenuItem menuItemOpenFromServer;
    private JMenuItem menuItemSave;
    private JMenuItem menuItemClose;

    public MyMenuBar() {
        super();
        menuFile = new JMenu("File");
        menuView = new JMenu("View");

        menuItemSave = new JMenuItem("Save");
        subMenuOpen = new JMenu("Open");
        menuItemOpenFromDisk = new JMenuItem("From disk");
        menuItemOpenFromServer = new JMenuItem("From server");
        subMenuOpen.add(menuItemOpenFromDisk);
        subMenuOpen.add(menuItemOpenFromServer);
        menuItemAbout = new JMenuItem("About");
        menuItemAbout.addActionListener(e -> onClickMenuAbout());
        menuItemClose = new JMenuItem("Close");

        menuFile.add(subMenuOpen);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemAbout);
        menuFile.add(menuItemClose);

        this.add(menuFile);
        this.add(menuView);
    }

    private void onClickMenuAbout() {
        JOptionPane.showMessageDialog(null, "UML Collaborative Editor - Ha Tuan Phong - UET 2020", "About", JOptionPane.PLAIN_MESSAGE);
    }
}
