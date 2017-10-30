package br.edu.ifc.blumenau.woworlds.core.Patched;

import br.edu.ifc.blumenau.woworlds.core.Patched.Bases.MainGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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

        TextureRegion curPlayerFrame = player.getCurrentAnimation().getKeyFrame(stateTime, true);
        batch.begin();
        batch.draw(player.getCurrentAnimation().getKeyFrame(stateTime, true), Gdx.graphics.getWidth() / 2 - curPlayerFrame.getRegionWidth() / 2, Gdx.graphics.getHeight() / 2 - curPlayerFrame.getRegionHeight() / 2, 75, 75);
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

        //Verificar colisão
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        if (1 < 123) {
            System.out.println("Hah");
        } else if (getMoveX() < 0) {

        } else {

        }

        setAnimation();
        camera.zoom = 0.2f;
        camera.translate(getMoveX(), getMoveY());
        camera.update();
        //System.out.println(camera.position.x + ";" + camera.position.y);
    }

    //<editor-fold defaultstate="collapsed" desc="Definidor de animações">
    private void setAnimation() {
        if (getMoveX() > 0) {
            player.setState(EstadoJogador.WALK_RIGHT);
            return;
        } else if (getMoveX() < 0) {
            player.setState(EstadoJogador.WALK_LEFT);
            return;
        } else if (getMoveY() > 0) {
            player.setState(EstadoJogador.WALK_UP);
            return;
        } else if (getMoveY() < 0) {
            player.setState(EstadoJogador.WALK_DOWN);
            return;
        }
        switch (player.getState()) {
            case WALK_RIGHT:
                player.setState(EstadoJogador.STOP_RIGHT);
                break;
            case WALK_LEFT:
                player.setState(EstadoJogador.STOP_LEFT);
                break;
            case WALK_UP:
                player.setState(EstadoJogador.STOP_UP);
                break;
            case WALK_DOWN:
                player.setState(EstadoJogador.STOP_DOWN);
                break;

        }
    }
    //</editor-fold>
}
