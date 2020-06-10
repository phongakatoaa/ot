package com.uet.ot_server.database;

import com.uet.ot_server.model.OTMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OTMessageRepository extends MongoRepository<OTMessage, String> {
    List<OTMessage> getAllByOtFileId(String otFileId);
}
