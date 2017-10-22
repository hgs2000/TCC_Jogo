package br.edu.ifc.blumenau.woworlds.core.Patched;


import br.edu.ifc.blumenau.woworlds.core.Patched.Bases.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Level1 extends Level {

    public Level1(MainGame game, Player player, OrthographicCamera camera, int level) {
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

        if (getGame().getMoveDown()) {

        }

        batch.end();



        camera.update();
    }
}
