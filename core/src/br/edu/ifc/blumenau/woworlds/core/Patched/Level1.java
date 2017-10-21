package br.edu.ifc.blumenau.woworlds.core.Patched;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Level1 extends Level {

    Level1(Game game, Player player, OrthographicCamera camera, int level) {
        super(game, player, camera, level);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();

        TextureRegion curPlayerFrame = player.getCurrentAnimation().getKeyFrame(0, true);
        batch.begin();
        batch.draw(curPlayerFrame, 0, 0);

        batch.end();
        camera.update();
    }
}
