package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.controller.skin.AssetsController;
import org.arnoid.damoclus.data.CrewMemberDictionary;
import org.arnoid.damoclus.logic.delegate.menu.TeamAssemblyMenuSceneDelegate;

import javax.inject.Inject;

public class TeamAssemblyMenuScene extends AbstractMenuScene<TeamAssemblyMenuSceneDelegate> {
    private static final String TAG = TeamAssemblyMenuScene.class.getSimpleName();
    private Table tblTeamList;

    @Inject
    CrewMemberDictionary crewMemberDictionary;

    @Inject
    AssetsController assetsController;
    private Texture demoTexture;

    public TeamAssemblyMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();

        for (String key : crewMemberDictionary.keySet()) {
            Gdx.app.log(TAG, "CREW [" + key + "] = " + crewMemberDictionary.get(key).toString());
        }
    }

    @Override
    protected void produceLayout() {
        setLayout(R.layout.menu_team_assembly);

        tblTeamList = (Table) findActor(R.id.tbl_team_list);

        registerMenuItem(findButton(R.id.btn_back));
    }

    @Override
    public void postProduceLayout() {
        registerMenuItem(findButton(R.id.btn_back));

        demoTexture = assetsController.loadSyncAndGet(R.image.helmet_astronaut_logo, Texture.class);
    }

    @Override
    public void onInteract(Actor actor, InputEvent event) {
        switch (actor.getName()) {
            case R.id.btn_back:
                getSceneDelegate().onBack();
                break;
        }
    }

    @Override
    public void onBack() {
        super.onBack();
        getSceneDelegate().onBack();
    }

    @Override
    protected void changed(Actor actor, ChangeListener.ChangeEvent event) {

    }
}