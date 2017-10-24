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
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        stateTime += Gdx.graphics.getDeltaTime();
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();
        TextureRegion curPlayerFrame = player.getCurrentAnimation().getKeyFrame(0, true);
        batch.begin();
        batch.draw(curPlayerFrame, Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2);

        if (getGame().getMoveDown()) {

        }

        batch.end();
        if (getGame().getMoveDown() == true && getGame().getMoveUp() == true) {
            setMoveY(0);
        } else if (getGame().getMoveDown() == true && getGame().getMoveUp() == false) {
            setMoveY(-1);
        } else if (getGame().getMoveDown() == false && getGame().getMoveUp() == true) {
            setMoveY(1);
        } else {
            setMoveY(getMoveY() - getMoveY());
        }

        if (getGame().getMoveLeft() == true && getGame().getMoveRight() == true) {
            setMoveX(0);
        } else if (getGame().getMoveLeft() == true && getGame().getMoveRight() == false) {
            setMoveX(-1);
        } else if (getGame().getMoveLeft() == false && getGame().getMoveRight() == true) {
            setMoveX(1);
        } else {
            setMoveX(getMoveX() - getMoveX());
        }

        camera.zoom = 0.5f;
        camera.translate(getMoveX(), getMoveY());
        camera.update();
        //System.out.println(camera.position.x + ";" + camera.position.y);
    }
}
