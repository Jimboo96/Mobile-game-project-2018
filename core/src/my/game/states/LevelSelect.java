package my.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import com.badlogic.gdx.utils.viewport.StretchViewport;

import my.game.Game;
import my.game.handlers.GameStateManager;


public class LevelSelect extends GameState {

    private TextureRegion reg;
    private int lvl;
    Skin mySkin;
    Stage stage;
    private Button exitButton,tutorialButton,rightButton,leftButton;
    Image lvlImg;
    private int width, height;
    BitmapFont font,font2;
    String lvlname,score,toothpaste;
    private StretchViewport viewport;

    public LevelSelect(final GameStateManager gsm) {
        super(gsm);
        lvl = game.lvls.getInteger("key");
        width= Game.V_WIDTH*2;
        height= Game.V_HEIGHT*2;
        viewport = new StretchViewport(width,height, cam);
        mySkin = game.mySkin;
        stage = gsm.stage;
        font = game.font12;
        font2 = game.font24;
        stage = new Stage(viewport);
        reg = new TextureRegion(Game.res.getTexture("menubg"), 0, 0, width, height);
        cam.setToOrtho(false, width, height);
        Play.level = 1;
        lvlname = "Level number " + Play.level;
        score = "your score is: 1000";
        toothpaste = "You collected 0/5 toothpaste";
        buttons();
        Gdx.input.setInputProcessor(stage);

    }

    private void buttons(){

        lvlImg =  new Image(Game.res.getTexture("olvi"));
        lvlImg.setSize(width/2,height/2);
        lvlImg.setPosition(width/4,height/3);
        lvlImg.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if ( event.getStageX() > width/4 && event.getStageX() < width*0.75f
                        && Play.level > 0 && Play.level <10) {
                    dispose();
                    gsm.setState(GameStateManager.PLAY);
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;}
        });
        stage.addActor(lvlImg);

        Table table = new Table();
        table.left().bottom();
        //button back to menu
        exitButton = new ImageButton(mySkin);
        exitButton.setSize(80,80);
        exitButton.setStyle(gsm.backStyle);
        exitButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                gsm.setState(GameStateManager.MENU);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;}
        });
        table.add(exitButton);
        //button tutorial
        tutorialButton = new ImageButton(mySkin);
        tutorialButton.setSize(80,80);
        tutorialButton.setStyle(gsm.toothStyle);
        tutorialButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                dispose();
                gsm.setState(GameStateManager.CUTSCENE);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;}
        });
        table.add(tutorialButton);

        stage.addActor(table);
        table.debug();

        //move to previous lvl
        leftButton = new ImageButton(mySkin);
        leftButton.setStyle(gsm.backStyle);
        leftButton.setSize(80,80);
        leftButton.setPosition(width/10,height/2,1);
        leftButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if ( event.getStageX() < width/4 && leftButton.isPressed() && Play.level > 1) {
                    Play.level -= 1;
                    lvlname = "Level number " + Play.level;
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;}
        });
        stage.addActor(leftButton);

        //move to next lvl
        rightButton = new ImageButton(mySkin);
        rightButton.setStyle(gsm.rightStyle);
        rightButton.setSize(80,80);
        rightButton.setPosition(width*0.9f,height/2,1);
        rightButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if (event.getStageX() > width*0.75  && rightButton.isPressed() && Play.level < 9) {
                    Play.level += 1;
                    lvlname = "Level number " + Play.level;
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;}
        });
        stage.addActor(rightButton);
    }

    public void handleInput() {
    }

    public void update(float dt) {
    }

    public void render() {
        if (Play.level == 1){
            leftButton.setVisible(false);
        }else leftButton.setVisible(true);
        if (Play.level == 9){
            rightButton.setVisible(false);
        }else rightButton.setVisible(true);
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(reg, 0, 0); //background
        font2.draw(sb,lvlname,width/4,height*0.90f);//lvlname
        font2.draw(sb,toothpaste,width/4,height/3.5f);
        font2.draw(sb,score,width/4,height/4.5f);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.clear();
    }

}

