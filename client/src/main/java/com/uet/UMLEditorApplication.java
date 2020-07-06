package com.uet;

import com.uet.config.UserConfig;
import com.uet.ot.UMLDocumentControl;
import com.uet.ot.helper.CanvasOperationMapper;
import com.uet.ui.ChatWidget;
import com.uet.ui.MyFrame;
import com.uet.ui.UserConfigDialog;
import com.uet.websocket.MySocketClient;
import com.uet.websocket.MyStompSessionHandler;

import javax.swing.*;

public class UMLEditorApplication {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);

        UMLDocumentControl.getInstance().setMyCanvas(frame.getMyCanvas());
        UMLDocumentControl.getInstance().setOperationLogger(frame.getOperationLogger());
        UserConfigDialog userConfigDialog = new UserConfigDialog();
        while (true) {
            int option = JOptionPane.showConfirmDialog(null, userConfigDialog, "", JOptionPane.YES_OPTION);
            if (userConfigDialog.getChannelId().isEmpty() || userConfigDialog.getUserId().isEmpty() || userConfigDialog.getHost().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all information", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                break;
            }
        }
        UserConfig userConfig = UserConfig.getInstance();
        userConfig.setUserId(userConfigDialog.getUserId());
        userConfig.setChannelId(userConfigDialog.getChannelId());
        userConfig.setHost(userConfigDialog.getHost());

        CanvasOperationMapper operationMapper = CanvasOperationMapper.getInstance();
        operationMapper.setUserId(userConfig.getUserId());
        try {
            MySocketClient mySocketClient = new MySocketClient(userConfig.getChannelId(), userConfig.getUserId(), userConfig.getHost());
            ChatWidget chatWidget = frame.getChatWidget();
            chatWidget.setSocketClient(mySocketClient);
            UMLDocumentControl.getInstance().setSocketClient(mySocketClient);
            MyStompSessionHandler stompSessionHandler = (MyStompSessionHandler) mySocketClient.getStompSessionHandler();
            stompSessionHandler.setChatWidget(chatWidget);
            mySocketClient.sendMessage("joined the session");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
