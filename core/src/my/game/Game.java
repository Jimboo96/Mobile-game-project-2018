package my.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import my.game.handlers.BoundedCamera;
import my.game.handlers.Content;
import my.game.handlers.GameStateManager;

import static my.game.handlers.B2DVars.CRYSTALS_COLLECTED;
import static my.game.handlers.B2DVars.ENEMIES_DESTROYED;
import static my.game.handlers.B2DVars.HEARTHS_LEFT;
import static my.game.handlers.B2DVars.LVL_UNLOCKED;

public class Game implements ApplicationListener {

	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;

	public static final float STEP = 1 / 90f;

	private SpriteBatch sb;
	private static BoundedCamera cam;
	private OrthographicCamera hudCam;

	private GameStateManager gsm;

	public static Content res;
	public static Preferences lvls;

	public SpriteBatch getSpriteBatch(){return sb;}
	public static BoundedCamera getCamera(){return cam;}
	public OrthographicCamera getHUDCamera(){return hudCam;}



	@Override
	public void create() {

		res = new Content();
		loadTextures();
		loadSounds();

		res.loadMusic("res/music/bbsong.ogg", "bbsong");
		res.getMusic("bbsong").setLooping(true);
		res.getMusic("bbsong").setVolume(0.5f);
		res.getMusic("bbsong").play();

		sb = new SpriteBatch();
		cam = new BoundedCamera();
		cam.setToOrtho(false, V_WIDTH,V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH,V_HEIGHT);

		gsm = new GameStateManager(this);

		lvls = Gdx.app.getPreferences("mylvls");
		if(!lvls.contains("key")) lvls.putInteger("key", LVL_UNLOCKED);
		if(!lvls.contains("crystals")) lvls.putInteger("crystals", CRYSTALS_COLLECTED);
		if(!lvls.contains("hearths")) lvls.putInteger("hearths", HEARTHS_LEFT);
		if(!lvls.contains("enemies")) lvls.putInteger("enemies", ENEMIES_DESTROYED);
		lvls.flush();

	}


	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		res.removeAll();
	}

	public void loadTextures(){

		res.loadTexture("res/images/trap.png","trap");
		res.loadTexture("res/images/crystal.png", "Crystal");
		res.loadTexture("res/images/hud.png","hud");
		res.loadTexture("res/images/bgs.png","bg");
		res.loadTexture("res/images/menu.png","menu");
		res.loadTexture("kuva.png","olvi");
		res.loadTexture("res/UI_final/rebg.png","menubg");
		res.loadTexture("res/UI_final/resized_paavalikko.png","main");
		res.loadTexture("res/UI_final/resized_hammas.png","tooth");
		res.loadTexture("res/images/Game_Over.png", "gameover");
		res.loadTexture("res/background/testimaa.png","bgone");
		res.loadTexture("res/background/rsz_karkkimaas.png","bgones");
		res.loadTexture("res/background/mountains.png", "mountain");
		res.loadTexture("res/images/char.png","char");
		res.loadTexture("res/images/bullet.png","bullet");
		res.loadTexture("res/UI_assets/heartSilhoutte.png","heartSilhoutte");
		res.loadTexture("res/UI_assets/ammoSilhoutte.png","ammoSilhoutte");
		res.loadTexture("res/UI_assets/heart.png","heart");
		res.loadTexture("res/UI_assets/ammo.png","ammo");
		res.loadTexture("res/images/happyTooth.png","happyTooth");
		res.loadTexture("res/images/attack.png", "attack");
		res.loadTexture("res/images/complete.png", "complete");

	}

	private void loadSounds(){
		res.loadSound("res/sfx/necksnap.mp3","snap");
		res.loadSound("res/sfx/hit.wav","hit");
	}
}
