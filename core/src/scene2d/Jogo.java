package scene2d;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Jogo extends Game {

    public class Ator extends Actor {

        Animation animation = new Animation<TextureRegion>(0.2f);
        Texture currentframe = new Texture(Gdx.files.internal("player.png"));
        Vector2 pos = new Vector2();
        float stateTime = 0.0f;

        @Override
        public void draw(Batch batch, float parentAlpha) {
            int x = Gdx.graphics.getWidth() / 2 - currentframe.getWidth() / 2;
            int y = Gdx.graphics.getHeight() / 2 - currentframe.getHeight() / 2;
            currentframe = (Texture) animation.getKeyFrame(stateTime);
            batch.draw(currentframe, x, y);
            stateTime += Gdx.graphics.getDeltaTime();
        }
    }

    private SpriteBatch batch;
    private Stage stage;


    @Override
    public void create() {

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        Ator ator = new Ator();
        stage.addActor(ator);
        for (Actor actor : stage.getActors()) {

        }

        //setScreen(menu);
    }

    @Override
    public void render() {
        float r = 0 / 255f;
        float g = 0 / 255f;
        float b = 0 / 255f;
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        super.render();
    }

}
