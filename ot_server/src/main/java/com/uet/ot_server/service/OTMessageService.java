package com.uet.ot_server.service;

import com.uet.ot_server.database.OTMessageRepository;
import com.uet.ot_server.model.OTMessage;
import com.uet.ot_server.model.ChannelSetting;
import com.uet.ot_server.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTMessageService {
    @Autowired
    private OTMessageRepository otMessageRepository;

    public ResponseMessage convertToResponseMessage(OTMessage message) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage(message.getOtOperation());
        return responseMessage;
    }

    public OTMessage saveMessage(OTMessage message) {
        return otMessageRepository.save(message);
    }
}
