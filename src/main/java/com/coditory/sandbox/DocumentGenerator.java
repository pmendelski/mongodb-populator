package com.coditory.sandbox;

import org.bson.Document;

import java.time.Instant;
import java.util.List;

import static com.coditory.sandbox.generators.IntGenerator.randomInt;
import static com.coditory.sandbox.generators.LoremIpsumGenerator.loremIpsumWords;
import static com.coditory.sandbox.generators.NameGenerator.randomFullName;

class DocumentGenerator {
    Instant lastCreatedAt = Instant.parse("2007-12-03T10:15:30.00Z");
    int lastId = 1;
    int lastVersion = 0;

    Document generate() {
        if (lastVersion == 100) {
            lastId = lastId + 1;
            lastVersion = 0;
        }
        lastVersion = lastVersion + 1;
        lastCreatedAt = Instant.ofEpochMilli(lastCreatedAt.toEpochMilli() + 1_000);
        Document versionedId = new Document("id", lastId)
                .append("version", lastVersion);
        return new Document("_id", versionedId)
                .append("name", randomFullName())
                .append("currentVersion", lastVersion == 100)
                .append("createdAt", lastCreatedAt)
                .append(
                        "tags", List.of(randomInt(10), randomInt(10), randomInt(10), randomInt(10),
                                randomInt(10)))
                .append("description", loremIpsumWords(10));
    }
}

