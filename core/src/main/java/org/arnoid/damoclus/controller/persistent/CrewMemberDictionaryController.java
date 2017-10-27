package org.arnoid.damoclus.controller.persistent;

import org.arnoid.damoclus.R;
import org.arnoid.damoclus.data.CrewMemberDictionary;

public class CrewMemberDictionaryController extends JsonPersistingController<CrewMemberDictionary> {

    private static final String FILE_PATH = R.json.crew_dictionary;

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
        return FILE_PATH;
    }
}
