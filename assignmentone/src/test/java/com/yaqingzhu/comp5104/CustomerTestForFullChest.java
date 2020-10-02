package com.yaqingzhu.comp5104;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTestForFullChest {

	/**
	 * 3 monkeys, 3 swords, 1 diamond, 1 parrot FC: coin   => SC 400  (ie no bonus)      
	 */
	@Test
	public void customerTest1Row101() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(400, round.getScoreOfRound());
	}
	
	/**
	 * 3 monkeys, 3 swords, 2 coins FC: captain   => SC (100+100+200+500)*2 =  1800      
	 */
	@Test
	public void customerTest2Row102() {
		Round round = new Round();
		round.setActiveCard(new CaptainCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Gold");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(1800, round.getScoreOfRound());
	}
	
	/**
	 * 3 monkeys, 4 swords, 1 diamond, FC: coin   => SC 1000  (ie 100++200+100+100+bonus)      
	 */
	@Test
	public void customerTest3Row103() {
		Round round = new Round();
		round.setActiveCard(new GoldCard());
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Monkey");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Sword");
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Diamond");
		round.checkSkullsOfRoll("1,2,3,4,5,6,7,8");
		round.calcRoundScore();

		Assert.assertEquals(1000, round.getScoreOfRound());
	}
}
