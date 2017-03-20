package org.arnoid.damoclus.ui.scene.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.arnoid.damoclus.R;
import org.arnoid.damoclus.component.MainComponent;
import org.arnoid.damoclus.logic.delegate.menu.TeamAssemblyMenuSceneDelegate;

public class TeamAssemblyMenuScene extends AbstractMenuScene<TeamAssemblyMenuSceneDelegate> {
    private static final String TAG = TeamAssemblyMenuScene.class.getSimpleName();
    private Table tblTeamList;

    public TeamAssemblyMenuScene(MainComponent component) {
        super();
        component.inject(this);
        init();
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