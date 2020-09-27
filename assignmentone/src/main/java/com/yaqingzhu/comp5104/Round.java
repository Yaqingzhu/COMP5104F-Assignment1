package com.yaqingzhu.comp5104;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {

	private ArrayList<Dice> dice;
	private int scoreOfRound;
	private Card activeCard;
	private int skullNumber;
	
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
	
	public int getSkullNumber() {
		return skullNumber;
	}
	public void setSkullNumber(int skullNumber) {
		this.skullNumber = skullNumber;
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
		int numDiamond = 0;
		int numGold = 0;
		
		numDiamond = numDiamond + results.getOrDefault("Diamond", 0);
		numGold = numGold + results.getOrDefault("Gold", 0);
		
		if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Diamond")) {
			numDiamond ++;
		}
		
		if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Gold")) {
			numGold ++;
		}
		
		if(numDiamond > 8) {
			numDiamond--;
		}
		
		if(numGold > 8) {
			numGold--;
		}
		
		scoreOfRound = scoreOfRound + ((numDiamond + numGold) * 100);
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
	
	
	public String rerollableSkullDice() {
		String numberofSkullDice = null;
		int i = 0;
		if(activeCard!= null && activeCard.getCardName().contains("Sorceress")) {
			for(Dice d: dice) {				
				if(d.getLastResult().equalsIgnoreCase("Skull")) {
					numberofSkullDice = "" + (i+1);
					setSkullNumber(getSkullNumber() - 1);
					activeCard = null;
					break;
				}
			}
		}
		return numberofSkullDice;
	}
	
	public void calcRoundScore() {
		HashMap<String, Integer> results = new HashMap<String, Integer>();

		if(!isToEndRound()) {
			for(Dice d: dice) {
				results.put(d.getLastResult(), (results.getOrDefault(d.getLastResult(), 0) + 1));
			}
			
			countSetScore(results);
			countDiamondAndGold(results);
			countFullChestScore(results);
		}
	}	
	
	public void processWithoutSeaBattle(String rerollNumbers) {
		String[] diceOrder = rerollNumbers.split(",");
		
		for (int i = 0; i < diceOrder.length; i++) {
			this.rollDice(Integer.parseInt(diceOrder[i]) - 1);
			if(getDice().get(Integer.parseInt(diceOrder[i]) - 1).getLastResult().equals("Skull")) {
				getDice().get(Integer.parseInt(diceOrder[i]) - 1).setDead(true);
					setSkullNumber(getSkullNumber() + 1);
			}
		}	
		calcRoundScore();
	}
	
	public String showResult() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 8; i++) { 
			String t = getDice().get(i).getLastResult();
			if(t.isBlank()) {
				t = "Skull";
			}
			sb.append(t);
			if(i < 7)
				sb.append(", ");
		}
		
		return sb.toString();
	}
	
	public boolean isSeaBattleActive() {
		if(activeCard!= null && activeCard.getCardName().contains("SeaBattle")) {
			return true;
		}
		return false;
	}
	
	public boolean isToEndRound() {
		return ((getSkullNumber() == 3) || (isSeaBattleActive() && getSkullNumber() > 3)) ;
	}	
}
