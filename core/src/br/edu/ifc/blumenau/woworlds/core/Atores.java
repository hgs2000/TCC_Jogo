package br.edu.ifc.blumenau.woworlds.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;


//https://github.com/libgdx/libgdx/wiki/Scene2d

public class Atores {
    static class Jogador extends Actor {

        private class Animacoes {
            Texture texture;
            Animation<TextureRegion> STOP_DOWN;
            Animation<TextureRegion> MOVE_DOWN;
            Animation<TextureRegion> STOP_UP;
            Animation<TextureRegion> MOVE_UP;
            Animation<TextureRegion> STOP_LEFT;
            Animation<TextureRegion> MOVE_LEFT;
            Animation<TextureRegion> STOP_RIGHT;
            Animation<TextureRegion> MOVE_RIGHT;

            public Animacoes(Texture texture) {
                this.texture = texture;
                setAnimacoes();
            }

            public ArrayList<Animation<TextureRegion>> getAnimacoes() {
                return new ArrayList<Animation<TextureRegion>>() {{
                    add(STOP_DOWN);
                    add(MOVE_DOWN);
                    add(STOP_UP);
                    add(MOVE_UP);
                    add(STOP_LEFT);
                    add(MOVE_LEFT);
                    add(STOP_RIGHT);
                    add(MOVE_RIGHT);
                }};
            }

            private void setAnimacoes() {
                TextureRegion[][] load = TextureRegion.split(texture, texture.getWidth() / 4, texture.getHeight() / 4);
                TextureRegion[] frames = new TextureRegion[load.length * load[0].length];
                int k = 0;
                for (TextureRegion[] aLoad : load) {
                    for (TextureRegion anALoad : aLoad) {
                        frames[k++] = anALoad;
                    }
                }

                //Bloco animação para baixo
                TextureRegion anim[] = new TextureRegion[4];
                for (int i = 0; i < 4; i++) {
                    anim[i] = frames[i];
                }
                MOVE_DOWN = new Animation<TextureRegion>(0.2f, anim);

                anim = new TextureRegion[1];
                anim[0] = frames[0];
                STOP_DOWN = new Animation<TextureRegion>(0.2f, anim);

                //Bloco animação para cima
                anim = new TextureRegion[4];
                for (int i = 0; i < 4; i++) {
                    anim[i] = frames[i + 4];
                }
                MOVE_UP = new Animation<TextureRegion>(0.2f, anim);

                anim = new TextureRegion[1];
                anim[0] = frames[4];
                MOVE_UP = new Animation<TextureRegion>(0.2f, anim);

                //Bloco animação para esquerda
                anim = new TextureRegion[4];
                for (int i = 0; i < 4; i++) {
                    anim[i] = frames[i + 8];
                }
                MOVE_LEFT = new Animation<TextureRegion>(0.2f, anim);

                anim = new TextureRegion[1];
                anim[0] = frames[8];
                STOP_LEFT = new Animation<TextureRegion>(0.2f, anim);

                //Bloco animação para direita
                anim = new TextureRegion[4];
                for (int i = 0; i < 4; i++) {
                    anim[i] = frames[i + 12];
                }
                MOVE_RIGHT = new Animation<TextureRegion>(0.2f, anim);

                anim = new TextureRegion[1];
                anim[0] = frames[12];
                STOP_RIGHT = new Animation<TextureRegion>(0.2f, anim);
            }
        }

        private Animation<TextureRegion> walkingUp;
        private Animation<TextureRegion> stopUp;
        private Animation<TextureRegion> walkingDown;
        private Animation<TextureRegion> stopDown;
        private Animation<TextureRegion> walkingLeft;
        private Animation<TextureRegion> stopLeft;
        private Animation<TextureRegion> walkingRight;
        private Animation<TextureRegion> stopRight;
        private float stateTime = 0;

        private SpriteBatch batch = new SpriteBatch();

        private Texture current_frame;
        private int current_frame_x = 0, current_frame_y = 0;
        private TextureRegion current_animation;
        private MainGame jogo;

        private EstadoJogador estado_jogador = EstadoJogador.STOP_DOWN;
        private ClasseJogador classe_jogador = ClasseJogador.INICIADOR;

        public Jogador() {
            ArrayList<Animation<TextureRegion>> anims = new Animacoes(new Texture(Gdx.files.internal("Knight_Walk.png"))).getAnimacoes();
            setAnimacoes(anims);
        }

        private void setAnimacoes(ArrayList<Animation<TextureRegion>> anims) {
            walkingDown = anims.get(0);
            stopDown = anims.get(1);
            walkingUp = anims.get(2);
            stopUp = anims.get(3);
            walkingLeft = anims.get(4);
            stopLeft = anims.get(5);
            walkingRight = anims.get(6);
            stopRight = anims.get(7);
        }

        @Override
        protected void positionChanged() {
            if (jogo.getMoveLeft() == true && jogo.getMoveRight() == true) {
                current_frame_x = current_frame_x;
            } else if (jogo.getMoveLeft() == true && jogo.getMoveRight() == false) {
                current_frame_x -= 1;
            } else if (jogo.getMoveLeft() == false && jogo.getMoveRight() == true) {
                current_frame_x += 1;
            }

            if (jogo.getMoveUp() == true && jogo.getMoveDown() == true) {
                current_frame_y = current_frame_y;
            } else if (jogo.getMoveUp() == true && jogo.getMoveDown() == false) {
                current_frame_y += 1;
            } else if (jogo.getMoveUp() == false && jogo.getMoveDown() == true) {
                current_frame_y -= 1;
            }
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            Color color = getColor();
            batch.setColor(color);
            batch.draw(current_frame, current_frame_x, current_frame_y);
        }

        //<editor-fold defaultstate="collapsed" desc="Enums do Jogador">
        enum ClasseJogador {
            INICIADOR, MAGO, CAVALEIRO, ASSASSINO
        }

        enum EstadoJogador {
            STOP_DOWN, STOP_UP, STOP_LEFT, STOP_RIGHT,
            WALK_DOWN, WALK_UP, WALK_LEFT, WALK_RIGHT
        }
        //</editor-fold>

    }
}
