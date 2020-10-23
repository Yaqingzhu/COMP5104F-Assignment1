package com.yaqingzhu.comp5104;

import java.util.ArrayList;

public class Player {

	private int score;
	private Round round;
	private boolean isFakeRandom = false;
	
	public boolean isFakeRandom() {
		return isFakeRandom;
	}
	public void setFakeRandom(boolean isFakeRandom) {
		this.isFakeRandom = isFakeRandom;
		this.round.isFakeRandom = this.isFakeRandom();
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
		this.round.isFakeRandom = this.isFakeRandom();
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
		if(this.score < 0) {
			this.score = 0;
		}
	}
	
	public Player() {
		round = new Round();
	}
	
	public String processInput(int option, String input) {
		String opt = "";
		switch(option) {
		case 1:
			opt = this.rollDice(input);
			opt = opt + this.showResult(input);
			break;
		case 2:
			this.setScore(this.getScore() + this.getRound().getScoreOfRound());
			opt = "INFO: Your score now is: " + this.getScore();
			break;
		case 3:
			if(this.getRound().getActiveCard() != null && this.getRound().getActiveCard().getCardName().equalsIgnoreCase("Treasurechest")) {
				this.round.setTreasureChestDices(input);
			}else {
				opt = "INFO: You can not use Treasure chest since you don't have this card";
			}
			break;
		default:
			opt = "this is wrong option";
		}
		
		return opt;
	}
	
	public String rollDice(String rerollNumbers) {		
		String opt = "";		
		
		if(rerollNumbers.split(",").length < 2) {
			opt = "INFO: you have to roll at least 2 dices.";
		}else {
			this.getRound().processRoll(rerollNumbers);
			
		}

		return opt;
	}
	
	public String showResult(String rerollNumbers) {
		String rerollableDice;
		String opt = "";
		this.getRound().processCalc(rerollNumbers);
		opt = "INFO: For this round, you get: " + this.getRound().showResult();
		rerollableDice = this.getRound().rerollableSkullDice();
		
		if(rerollableDice != null) {
			opt = opt + "INFO: You have Sorceress card and you can reroll one Skull dice. Rerolling";
			opt = opt + "INFO: Rerolling This Dice. The dice number is " + rerollableDice;
			this.getRound().processRoll(rerollableDice);
			this.getRound().processCalc(rerollNumbers);
			opt = opt + "INFO: after reroll the Skull Dice, you get: " + this.getRound().showResult();
		}
		
		if(this.getRound().isToEndRound()) {
			opt = opt +"INFO: You are disqualified for this turn.";
			this.setScore(this.getScore() + this.getRound().getScoreOfRound());
		}
		if(this.getRound().isWentToSkullIsland()) {
			opt = opt + "INFO: You went to Skull island this round. Other players will lose " + this.getRound().getScoreOfSkullIsland();
		}else {
			opt = opt +"INFO: Your score of this round is" + this.getRound().getScoreOfRound();
		}
		
		return opt;
	}
	
}
