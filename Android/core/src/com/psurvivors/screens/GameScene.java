package com.psurvivors.screens;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.psurvivors.GestureInput;
import com.psurvivors.PTSGame;
import com.psurvivors.PTSGame.TypeScene;
import com.psurvivors.callbacks.IRequestActionCallback;
import com.psurvivors.entities.Jogo;
import com.psurvivors.gameworld.GameController;
import com.psurvivors.loaders.GraphicsLoader;
import com.psurvivors.managers.GameManager;
import com.psurvivors.ui.HUD;
import com.psurvivors.ui.Microphone;

public class GameScene extends AbstractScene {

	public HUD hud;
	Stage stage;
	private InputMultiplexer inputs = new InputMultiplexer();

	private PTSGame game;
	private GameController controller;
	private GraphicsLoader loader;

	public enum Action {
		ZOOM_IN,
		ZOOM_OUT,
		LEFT,
		RIGHT,
		UP,
		DOWN,
		LEFT_P,
		RIGHT_P,
		UP_P,
		DOWN_P
	}
	
	public InputMultiplexer getInputs() {
		return inputs;
	}

	public void setInputs(InputMultiplexer inputs) {
		this.inputs = inputs;
	}
	
	public PTSGame getMain(){
		return game;
	}
	
	public GameScene(PTSGame game) {
		this.game = game;
		this.controller = new GameController(this);
		setType(TypeScene.GAME);
		
		loader = game.getGraphicsLoader();
		
		hud = new HUD(this);
		hud.createHUD();
		
		inputs.addProcessor(new GestureDetector(new GestureInput(controller.getCamera(), controller)));
		
		Gdx.input.setInputProcessor(inputs);
		
		IRequestActionCallback com = game.getRequestAction();
		if (com != null){
			GameManager.getInstance().setComands(com.currentCommands());
		}		
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		if (hud != null){
			hud.resize(width, height);
		}
	}
	
	public GameController getControler(){
		return this.controller;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(127f/255f, 140f/255f, 141f/255f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (controller != null){
			controller.render(delta);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
			exit();
		}
		
		if (hud != null){
			hud.onUpdate();
		}
	}
	
	public void exit(){
		GameManager gameManager = GameManager.getInstance();
		game.setScreen(new GamesSavesScene(game, (List<Jogo>) game.getRequestAction().buscarJogos(gameManager.getNome())));
	}

	@Override
	public void dispose() {
		controller.dispose();
	}
	
	public GraphicsLoader getLoader(){
		return loader;
	}

	@Override
	public void changeMicrophone(Microphone status) {
		switch (status){
		case ERRO:
			hud.changeMicrophoneImg((Texture) loader.manager.get("m_red.png"));
			break;
		case LISTENING:
			hud.changeMicrophoneImg((Texture) loader.manager.get("m_white.png"));
			break;
		case RECONHECIDO:
			hud.changeMicrophoneImg((Texture) loader.manager.get("m_green.png"));
			break;
		case STOPPED:
			hud.changeMicrophoneImg((Texture) loader.manager.get("m_white.png"));
			break;
	}		
	}

}
