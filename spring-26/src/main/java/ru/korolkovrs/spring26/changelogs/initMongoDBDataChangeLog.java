package ru.korolkovrs.spring26.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;


@ChangeLog(order = "001")
public class initMongoDBDataChangeLog {

    @ChangeSet(order = "000", id = "dropDB", author = "korolkovrs", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }
}