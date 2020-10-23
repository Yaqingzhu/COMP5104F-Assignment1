package com.yaqingzhu.comp5104.cucumber;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.annotation.en.And;


import com.yaqingzhu.comp5104.CaptainCard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import com.yaqingzhu.comp5104.*;
import com.yaqingzhu.comp5104.Player;
import com.yaqingzhu.comp5104.Round;


public class CaculateScoreSD {
	
	Player player;
	Player player2;
	Round round;
	String optFromConsole = "";
	
	@Given("^A player get a captain card$")
	public void givenGetCaptainCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new CaptainCard());
	}
	
	@Given("^A player get a gold card$")
	public void givenGetGoldCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new GoldCard());
	}
	
	@Given("^A player get a monkey business card$")
	public void givenGetMonkeyBusinessCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new MonkeyBusinessCard());
	}
	
	@Given("^A player get 2 sword sea battle card$")
	public void givenGetSeaBattleTwoCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SeaBattleCardTwo());
	}
	
	@Given("^A player get 3 sword sea battle card$")
	public void givenGetSeaBattleThreeCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SeaBattleCardThree());
	}
	
	@Given("^A player get 4 sword sea battle card$")
	public void givenGetSeaBattleFourCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SeaBattleCardFour());
	}
	
	@Given("^A player get a diamond card$")
	public void givenGetDiamondCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new DiamondCard());
	}
	
	@Given("^A player get a skullscard card$")
	public void givenGetSkullsCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SkullsCard());
	}
	
	@Given("^A player get a sorceress card$")
	public void givenGetSorceressCard() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SorceressCard());
	}
	
	@Given("^A player get a skullcardtwo card$")
	public void givenGetSkullCardTwo() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new SkullCardTwo());
	}
	
	@Given("^A player get a Tresure Chest card$")
	public void givenGetTreasureChest() throws Throwable {
		player = new Player();
		round = player.getRound();
		round.setActiveCard(new TreasureChestCard());
	}
	
	@And("^roll all 8 dices on the first roll$")
	public void andRollAllDices() throws Throwable {
		optFromConsole = player.rollDice("1,2,3,4,5,6,7,8");
	}
	
	@When("^the result of first roll is 1:\"([^\"]*)\", 2:\"([^\"]*)\", 3:\"([^\"]*)\", 4:\"([^\"]*)\", 5:\"([^\"]*)\", 6:\"([^\"]*)\", 7:\"([^\"]*)\", 8:\"([^\"]*)\"$")
	public void whenFirstRoll(String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8) throws Throwable {
		round.getDice().get(0).setLastResult(arg1);
		round.getDice().get(1).setLastResult(arg2);
		round.getDice().get(2).setLastResult(arg3);
		round.getDice().get(3).setLastResult(arg4);
		round.getDice().get(4).setLastResult(arg5);
		round.getDice().get(5).setLastResult(arg6);
		round.getDice().get(6).setLastResult(arg7);
		round.getDice().get(7).setLastResult(arg8);
		optFromConsole = player.showResult("1,2,3,4,5,6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^reroll dice \"([^\"]*)\"$")
	public void andRerollDice5(String arg1) throws Throwable {
		optFromConsole = player.rollDice(arg1);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:skull, 4:skull, 5:monkey, 6:parrot, 7:sword, 8:sword$")
	public void and2Row44() throws Throwable {
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("5");
		System.out.println(optFromConsole);
	}
	
	@Then("^score for this player is (\\d+)$")
	public void thenScore(int arg1) throws Throwable {
		System.out.println(optFromConsole);
		assertEquals(arg1, round.getScoreOfRound());
	}
	
	@Then("^the player die and lose (\\d+) points$")
	public void thenLoseScore(int arg1) throws Throwable {
		System.out.println(optFromConsole);
		assertTrue(optFromConsole.contains("disqualified"));
		assertEquals(arg1, round.getScoreOfRound() * -1);
	}
	
	@Then("^the player die and end of turn$")
	public void thenDead() throws Throwable {
		System.out.println(optFromConsole);
		assertTrue(optFromConsole.contains("disqualified"));
	}
	
	@Then("^the player die and end of turn with (\\d+) point$")
	public void thenNonNegative(int arg1) throws Throwable {
		System.out.println(optFromConsole);
		assertTrue(optFromConsole.contains("disqualified"));
		assertEquals(arg1, player.getScore());
	}
	
	@Then("^deduct (\\d+) for all other players$")
	public void thenDeductPoint(int arg1) throws Throwable {
		System.out.println(optFromConsole);
		assertEquals(arg1, round.getScoreOfSkullIsland());
	}
	
	@Then("the other players score is not negative$")
	public void thenOtherNonNegative() throws Throwable {
		player2 = new Player();
		assertTrue(player2.getScore() == 0);
		System.out.println(optFromConsole);
		player2.setScore(player2.getScore() - round.getScoreOfSkullIsland());
		assertTrue(player2.getScore() == 0);
	}
	
	@Then("dice \"([^\"]*)\" can be reroll$")
	public void thenSorceressReroll(String arg1) throws Throwable {
		assertEquals(true,optFromConsole.contains("Rerolling This Dice. The dice number is " + arg1));
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:skull, 5:sword, 6:parrot, 7:sword, 8:sword$")
	public void and2Row46() throws Throwable {
		round.getDice().get(4).setLastResult("Sword");
		optFromConsole = player.showResult("5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:gold, 2:gold, 3:gold, 4:monkey, 5:parrot, 6:sword, 7:sword, 8:sword")
	public void and2Row50() throws Throwable {
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Parrot");
		optFromConsole = player.showResult("4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:gold, 2:gold, 3:gold, 4:sword, 5:monkey, 6:sword, 7:sword, 8:sword")
	public void and3Row50() throws Throwable {
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:sword, 2:sword, 3:parrot, 4:parrot, 5:monkey, 6:parrot, 7:sword, 8:sword")
	public void and2Row52() throws Throwable {
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("1,2,3,4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:sword, 2:sword, 3:parrot, 4:sword, 5:monkey, 6:parrot, 7:sword, 8:sword")
	public void and3Row52() throws Throwable {
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:parrot, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:monkey, 7:monkey, 8:monkey")
	public void and2Row58() throws Throwable {
		round.getDice().get(0).setLastResult("Parrot");
		round.getDice().get(3).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Monkey");
		optFromConsole = player.showResult("1,4,5,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:monkey, 6:monkey, 7:monkey, 8:monkey")
	public void and3Row58() throws Throwable {
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("1,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:diamond, 2:skull, 3:monkey, 4:sword, 5:diamond, 6:monkey, 7:parrot, 8:sword")
	public void and1Row59() throws Throwable {
		round.getDice().get(1).setLastResult("Skull");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Diamond");
		optFromConsole = player.showResult("2,3,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:diamond, 2:diamond, 3:monkey, 4:sword, 5:diamond, 6:monkey, 7:parrot, 8:sword")
	public void and1Row60() throws Throwable {
		round.getDice().get(1).setLastResult("Diamond");
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(4).setLastResult("Diamond");
		optFromConsole = player.showResult("2,3,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:gold, 2:gold, 3:sword, 4:gold, 5:monkey, 6:monkey, 7:parrot, 8:sword")
	public void and1Row61() throws Throwable {
		round.getDice().get(1).setLastResult("Gold");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Gold");
		round.getDice().get(4).setLastResult("Monkey");
		optFromConsole = player.showResult("2,3,4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:gold, 7:sword, 8:gold")
	public void androw94() throws Throwable {
		round.getDice().get(5).setLastResult("Gold");
		round.getDice().get(6).setLastResult("Sword");
		optFromConsole = player.showResult("6,7");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:gold")
	public void and1row76() throws Throwable {
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		optFromConsole = player.showResult("3,4,5,6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:parrot, 6:parrot, 7:parrot, 8:gold")
	public void and2row76() throws Throwable {
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		optFromConsole = player.showResult("3,4,5,6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:parrot, 2:monkey, 3:gold, 4:sword, 5:sword, 6:diamond, 7:parrot, 8:gold")
	public void and3row76() throws Throwable {
		round.getDice().get(2).setLastResult("Gold");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Diamond");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		optFromConsole = player.showResult("3,4,5,6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:gold")
	public void and1row77() throws Throwable {
		round.getDice().get(2).setLastResult("Monkey");
		round.getDice().get(3).setLastResult("Sword");
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		round.getDice().get(6).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Gold");
		optFromConsole = player.showResult("3,4,5,6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:sword, 6:parrot, 7:parrot, 8:diamond")
	public void and2Row77() throws Throwable {
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Diamond");
		optFromConsole = player.showResult("5,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:parrot, 6:parrot, 7:parrot, 8:parrot")
	public void and3Row77() throws Throwable {
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(7).setLastResult("Parrot");
		optFromConsole = player.showResult("1,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:monkey, 5:sword, 6:skull, 7:sword, 8:skull")
	public void andRow114() throws Throwable {
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Skull");
		optFromConsole = player.showResult("7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:sword, 3:sword, 4:skull, 5:sword, 6:sword, 7:skull, 8:skull")
	public void andRow117() throws Throwable {
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Sword");
		round.getDice().get(2).setLastResult("Sword");
		round.getDice().get(3).setLastResult("Skull");
		optFromConsole = player.showResult("1,2,3,4");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:monkey, 3:monkey, 4:sword, 5:skull, 6:diamond, 7:sword, 8:sword")
	public void and1Row120() throws Throwable {
		round.getDice().get(6).setLastResult("Sword");
		round.getDice().get(7).setLastResult("Sword");
		optFromConsole = player.showResult("7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:sword, 2:parrot, 3:parrot, 4:sword, 5:skull, 6:diamond, 7:sword, 8:sword")
	public void and2Row120() throws Throwable {
		round.getDice().get(0).setLastResult("Sword");
		round.getDice().get(1).setLastResult("parrot");
		round.getDice().get(2).setLastResult("parrot");
		optFromConsole = player.showResult("1,2,3");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:sword, 7:diamond, 8:parrot")
	public void and1Row103() throws Throwable {
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Skull");
		optFromConsole = player.showResult("3,4");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:skull, 6:sword, 7:diamond, 8:parrot")
	public void and2Row103() throws Throwable {
		round.getDice().get(4).setLastResult("Skull");
		round.getDice().get(5).setLastResult("Sword");
		optFromConsole = player.showResult("5,6");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:sword, 5:parrot, 6:gold, 7:diamond, 8:sword")
	public void and1Row104() throws Throwable {
		round.getDice().get(4).setLastResult("Parrot");
		round.getDice().get(5).setLastResult("Gold");
		optFromConsole = player.showResult("5,6");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:sword, 7:parrot, 8:diamond")
	public void and1Row105() throws Throwable {
		round.getDice().get(3).setLastResult("Skull");
		round.getDice().get(4).setLastResult("Sword");
		optFromConsole = player.showResult("4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:skull, 5:sword, 6:parrot, 7:parrot, 8:diamond")
	public void and2Row105() throws Throwable {
		round.getDice().get(4).setLastResult("Sword");
		round.getDice().get(5).setLastResult("Parrot");
		optFromConsole = player.showResult("5,6");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:monkey, 3:monkey, 4:sword, 5:monkey, 6:parrot, 7:parrot, 8:sword")
	public void and1Row71() throws Throwable {
		round.getDice().get(0).setLastResult("Skull");
		round.getDice().get(1).setLastResult("Monkey");
		optFromConsole = player.showResult("1,2");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:monkey, 2:skull, 3:monkey, 4:sword, 5:monkey, 6:parrot, 7:parrot, 8:sword")
	public void and1Row72() throws Throwable {
		round.getDice().get(0).setLastResult("Monkey");
		round.getDice().get(1).setLastResult("Skull");
		optFromConsole = player.showResult("1,2");
		System.out.println(optFromConsole);
	}
	
	
	@And("^the player goes to skulls island")
	public void andGotoSkullIsland() throws Throwable {
		assertTrue(round.isWentToSkullIsland());
	}
	
	@And("^put dice \"([^\"]*)\" in chest")
	public void andPutChest(String arg1) throws Throwable {
		round.setTreasureChestDices(arg1);
	}
	
	@And("^the result of this roll is 1:parrot, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:diamond, 7:diamond, 8:gold")
	public void and1Row80() throws Throwable {
		round.getDice().get(3).setLastResult("Parrot");
		round.getDice().get(4).setLastResult("Parrot");
		optFromConsole = player.showResult("4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:parrot, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:gold, 8:parrot")
	public void and2Row80() throws Throwable {
		round.getDice().get(5).setLastResult("Skull");
		round.getDice().get(6).setLastResult("Gold");
		round.getDice().get(7).setLastResult("Parrot");
		optFromConsole = player.showResult("6,7,8");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:diamond, 4:diamond, 5:gold, 6:gold, 7:gold, 8:gold")
	public void and1Row85() throws Throwable {
		round.getDice().get(2).setLastResult("Diamond");
		round.getDice().get(3).setLastResult("Diamond");
		round.getDice().get(4).setLastResult("Gold");
		optFromConsole = player.showResult("3,4,5");
		System.out.println(optFromConsole);
	}
	
	@And("^the result of this roll is 1:skull, 2:skull, 3:skull, 4:gold, 5:gold, 6:gold, 7:gold, 8:gold")
	public void and2Row85() throws Throwable {
		round.getDice().get(2).setLastResult("Skull");
		round.getDice().get(3).setLastResult("Gold");
		optFromConsole = player.showResult("3,4");
		System.out.println(optFromConsole);
	}
	
	@Then("interface reports not allowed rerolling only one dice")
	public void thenCannotRollOne() throws Throwable {
		assertTrue(optFromConsole.contains("you have to roll at least 2 dices"));
	}
}
