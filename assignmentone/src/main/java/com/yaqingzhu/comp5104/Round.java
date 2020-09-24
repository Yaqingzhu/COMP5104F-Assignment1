package com.yaqingzhu.comp5104;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {

	private ArrayList<Dice> dice;
	private int scoreOfRound;
	private Card activeCard;
	
	public ArrayList<Dice> getDice() {
		return dice;
	}
	public void setDice(ArrayList<Dice> dice) {
		this.dice = dice;
	}
	public Card getActiveCard() {
		return activeCard;
	}
	public void setActiveCard(Card activeCard) {
		this.activeCard = activeCard;
	}
	
	public int getScoreOfRound() {
		return scoreOfRound;
	}
	public void setScoreOfRound(int scoreOfRound) {
		this.scoreOfRound = scoreOfRound;
	}
	public Round() {
		setDice(new ArrayList<Dice>());
		for (int i = 0; i < 8; i++) {
			getDice().add(new Dice());
		}
	}
	
	public void rollDice(int index) {
		Dice d = getDice().get(index);
		d.roll();
	}
	
	public void countSetScore(HashMap<String, Integer> results) {
		int numberOfSet = 0;
		
		for(String key: results.keySet()) {
			if(!key.isBlank()) {
				numberOfSet = results.get(key);
				if((key.equalsIgnoreCase("Diamond") && activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Diamond")) ||
						(key.equalsIgnoreCase("Gold") && activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Gold"))) {
					numberOfSet ++;
				}
				switch(numberOfSet) {
				case 3:
					scoreOfRound = scoreOfRound + 100;
					break;
				case 4:
					scoreOfRound = scoreOfRound + 200;
					break;
				case 5:
					scoreOfRound = scoreOfRound + 500;
					break;
				case 6:
					scoreOfRound = scoreOfRound + 1000;
					break;
				case 7:
					scoreOfRound = scoreOfRound + 2000;
					break;
				case 8:
					scoreOfRound = scoreOfRound + 4000;
					break;
				default:
					scoreOfRound = scoreOfRound + 0;
				}
			}
		}
	}
	
	public void countDiamondAndGold(HashMap<String, Integer> results) {
		int num = 0;
		
		num = num + results.get("Diamond");
		num = num + results.get("Gold");
		
		if((activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Diamond")) ||
				(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Gold"))) {
			num ++;
		}
		
		scoreOfRound = scoreOfRound + (num * 100);
	}
	
	public void countFullChestScore(HashMap<String, Integer> results) {
		boolean isFullChest = true;
		for(String key: results.keySet()) {
			// if has Skull, it's impossible to be full chest.
			if(key.isBlank()) {
				isFullChest = false;
			}
			/* if any set is less than 3 and not Gold or Diamond, it's impossible to be full chest.
			 * in terms of "monkey business card is active",  
			 * checkMonkeyBusiness function will translate Parrot to Monkey for this results hash set.
			 * For example, Dice result is {Monkey, Monkey, Parrot, Gold}. After checkMonkeyBusiness function, the values stored 
			 * in this results hashset is {Monkey, Monkey, Monkey, Gold}.
			 */
			if(results.get(key) < 3 && !key.contains("Diamond") && !key.contains("Gold")) {
				isFullChest = false;
			}
		}
		
		if(isFullChest) {
			scoreOfRound = scoreOfRound + 500;
		}
	}
}
