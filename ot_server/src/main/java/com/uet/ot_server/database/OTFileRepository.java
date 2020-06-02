package com.uet.ot_server.database;

import com.uet.ot_server.model.OTFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OTFileRepository extends MongoRepository<OTFile, String> {
    OTFile findBy_id(String id);

    OTFile findByName(String name);
}
