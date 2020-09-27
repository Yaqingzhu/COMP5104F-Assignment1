package com.yaqingzhu.comp5104;

import java.util.ArrayList;
import java.util.Random;

public class Dice {
	
	private ArrayList<String> diceRange;
	private boolean dead;
	private String lastResult;
	
	public String getLastResult() {
		return lastResult;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Dice() {
		diceRange = new ArrayList<String>();
		diceRange.add("Skull");
		diceRange.add("Diamond");
		diceRange.add("Gold");
		diceRange.add("Monkey");
		diceRange.add("Parrot");
		diceRange.add("Sword");
		
		dead = false;
	}
	
	public String roll() {
		if(isDead())
			return "";
		
		Random random = new Random();
		int number = random.nextInt(5);
		setLastResult(diceRange.get(number));
		return diceRange.get(number);
	}
	
	public String roll(Card card) {
		if(isDead())
			return "";
		
		Random random = new Random();
		int number = random.nextInt(5);
		String opt = diceRange.get(number);
		
		switch (card.getCardName()){
			case "Skull":
				opt = "Skull";
				break;
			case "MonkeyBusiness":
				if (opt.equalsIgnoreCase("Parrot")) {
					opt = "Monkey";
				}
				break;
		}
		setLastResult(opt);
		return opt;	
	}
}
