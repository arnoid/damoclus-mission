package org.arnoid.damoclus.controller.persistent;

import org.arnoid.damoclus.data.CrewMemberDictionary;

public class CrewMemberDictionaryController extends JsonPersistingController<CrewMemberDictionary> {

    private static final String FILE_PATH = "data/json/crew-dictionary.json";

    @Override
    protected Class<CrewMemberDictionary> getContentClass() {
        return CrewMemberDictionary.class;
    }

    @Override
    protected void onPostWrite(boolean result, CrewMemberDictionary instance) {

    }

    @Override
    protected void onPostRead(CrewMemberDictionary instance) {

    }

    @Override
    protected CrewMemberDictionary generateDefaultInstance() {
        return new CrewMemberDictionary();
    }

    @Override
    protected String getJsonFilePath() {
        return null;
    }
}
