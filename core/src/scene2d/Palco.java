package scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Palco extends Stage {

    @Override
    public void draw() {
        this.act(Gdx.graphics.getDeltaTime());
        super.draw();
    }
}
