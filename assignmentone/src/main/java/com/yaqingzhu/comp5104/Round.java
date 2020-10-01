package com.yaqingzhu.comp5104;

import java.util.ArrayList;
import java.util.HashMap;

public class Round {

	private ArrayList<Dice> dice;
	private int scoreOfRound;
	private Card activeCard;
	private int skullNumber;
	private int scoreOfSkullIsland;
	private int swordsRequiredForSeaBattle;
	private boolean isToEndSkullIsland;
	private String TreasureChestDices;
	
	public String getTreasureChestDices() {
		return TreasureChestDices;
	}

	public void setTreasureChestDices(String treasureChestDices) {
		TreasureChestDices = treasureChestDices;
	}
	
	public boolean isToEndSkullIsland() {
		return isToEndSkullIsland;
	}

	public void setToEndSkullIsland(boolean isToEndSkullIsland) {
		this.isToEndSkullIsland = isToEndSkullIsland;
	}
	
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
		
		if(activeCard!= null && activeCard.getCardName().contains("Skull")) {
			if(activeCard.getCardName().contains("2")) {
				setSkullNumber(getSkullNumber() + 2);
			}else {
				setSkullNumber(getSkullNumber() + 1);
			}			
		}
		
		if(activeCard!= null && activeCard.getCardName().contains("Sea")) {
			if(activeCard.getCardName().contains("2")) {
				setSwordsRequiredForSeaBattle(2);
			}else if (activeCard.getCardName().contains("3")) {
				setSwordsRequiredForSeaBattle(3);
			}else if (activeCard.getCardName().contains("4")) {
				setSwordsRequiredForSeaBattle(4);
			}			
		}
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
	
	public int getScoreOfSkullIsland() {
		return scoreOfSkullIsland;
	}

	public void setScoreOfSkullIsland(int scoreOfSkullIsland) {
		this.scoreOfSkullIsland = scoreOfSkullIsland;
	}
	
	public int getSwordsRequiredForSeaBattle() {
		return swordsRequiredForSeaBattle;
	}

	public void setSwordsRequiredForSeaBattle(int swordsRequiredForSeaBattle) {
		this.swordsRequiredForSeaBattle = swordsRequiredForSeaBattle;
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
				
				if(numberOfSet > 8) {
					numberOfSet--;
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
				i++;
			}
		}
		return numberofSkullDice;
	}
	
	public void calcRoundScore() {
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		
		if(isSeaBattleActive()) {
			if(!isToEndRound()) {
				for(Dice d: dice) {
					results.put(d.getLastResult(), results.getOrDefault(d.getLastResult(), 0) + 1);
				}
				
				if(getSwordsRequiredForSeaBattle() <= results.getOrDefault("Sword", 0)) {
					countSeaBattleScore(results, 1);
					countSetScore(results);
					countDiamondAndGold(results);
					countFullChestScore(results);
					countCaptainCardScore();
				}else {
					countSeaBattleScore(results, -1); 
				}
			} else {
				countSeaBattleScore(results, -1);
			}
		} else {
			if(!isToEndRound()) {
				for(Dice d: dice) {
					results.put(checkMonkeyBusiness(d.getLastResult()), (results.getOrDefault(checkMonkeyBusiness(d.getLastResult()), 0) + 1));
				}
				
				if(getSkullNumber() > 3) {
					countSkullIslandScore();
				}else {
					countSetScore(results);
					countDiamondAndGold(results);
					countFullChestScore(results);
					countCaptainCardScore();
				}
			} else if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("TreasureChest")) {
				getTreasureChestScore(getTreasureChestDices());
			}
		}
	}	
	
	public void process(String rerollNumbers) {
		String[] diceOrder = rerollNumbers.split(",");
		scoreOfRound = 0;
		for (int i = 0; i < diceOrder.length; i++) {
			this.rollDice(Integer.parseInt(diceOrder[i]) - 1);
		}	
		checkSkullsOfRoll(rerollNumbers);
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
	
	public boolean isWentToSkullIsland() {
		return (!(activeCard!= null && activeCard.getCardName().contains("Sea")) && getSkullNumber() > 3);
	}
	
	public String checkMonkeyBusiness(String input) {
		String output = input;
		if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("MonkeyBusiness") && input.equalsIgnoreCase("Parrot")) {
			output = "Monkey";
		}
		return output;
	}
	
	public void countSkullIslandScore() {
		scoreOfSkullIsland = getSkullNumber() * 100;
		
		if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Captain")) {
			scoreOfSkullIsland = scoreOfSkullIsland * 2;
		}

	}
	
	public void checkSkullsOfRoll(String rerollNumbers) {
		String[] diceOrder = rerollNumbers.split(",");
		int skullsOfThisTry = 0;
		scoreOfRound = 0;
		
		for (int i = 0; i < diceOrder.length; i++) {
			if(getDice().get(Integer.parseInt(diceOrder[i]) - 1).getLastResult().equals("Skull")) {
				getDice().get(Integer.parseInt(diceOrder[i]) - 1).setDead(true);
					setSkullNumber(getSkullNumber() + 1);
					skullsOfThisTry++;
			}
		}
		
		if(skullsOfThisTry == 0) {
			isToEndSkullIsland = true;
		}
	}
	
	public void countSeaBattleScore(HashMap<String, Integer> results, int isSeaBattleWon) {
		int score = 0;
		switch(getSwordsRequiredForSeaBattle()) {
		case 2:
			score = 300 * isSeaBattleWon;
			break;
		case 3:
			score = 500 * isSeaBattleWon;
			break;
		case 4:
			score = 1000 * isSeaBattleWon;
			break;
		}
		
		scoreOfRound = scoreOfRound + score;
	}
	
	public void countCaptainCardScore() {
		if(activeCard!= null && activeCard.getCardName().equalsIgnoreCase("Captain")) {
			scoreOfRound = scoreOfRound * 2;
		}
	}
	
	public void getTreasureChestScore(String diceNumber) {
		String[] diceOrder = diceNumber.split(",");
		HashMap<String, Integer> results = new HashMap<String, Integer>();
		
		for (int i = 0; i < diceOrder.length; i++) {
			results.put(getDice().get(Integer.parseInt(diceOrder[i]) - 1).getLastResult(), results.getOrDefault(getDice().get(Integer.parseInt(diceOrder[i]) - 1).getLastResult(), 0) + 1);
		}
		
		for(int i = 0; i < 8-diceOrder.length; i++) {
			results.put("", 1);
		}
		
		scoreOfRound = 0;
		countSetScore(results);
		countDiamondAndGold(results);
		countFullChestScore(results);
		
	}
	
}
