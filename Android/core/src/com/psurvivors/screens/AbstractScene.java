package com.psurvivors.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.psurvivors.PTSGame;
import com.psurvivors.PTSGame.TypeScene;
import com.psurvivors.ui.Microphone;

public abstract class AbstractScene extends ScreenAdapter{
	
	protected TypeScene type;
	
	public void setType(TypeScene typeS){
		type = typeS;
	}
	
	public TypeScene getType(){
		return type;
	}
	
	public abstract void changeMicrophone(Microphone status);

}
