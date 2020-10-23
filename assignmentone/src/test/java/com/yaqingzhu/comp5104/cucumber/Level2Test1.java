package com.yaqingzhu.comp5104.cucumber;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.annotation.en.And;

import static org.junit.Assert.assertTrue;

import com.yaqingzhu.comp5104.FakeRandom;

public class Level2Test1 {

	MockServer ms;
	MockClient mc1;
	MockClient mc2;
	MockClient mc3;
	String t2;

	@Given("^Start a game server$")
	public void given() throws Throwable {
		FakeRandom.resetCount();
		ms = new MockServer();
		ms.start();
	}

	@And("^three players join$")
	public void andJoinPlayers() throws Throwable {
		mc1 = new MockClient();
		mc1.start("1");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mc2 = new MockClient();
		mc2.start("2");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}

		mc3 = new MockClient();
		mc3.start("3");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("^player (\\d+) get FC card$")
	public void whenCheckFCCard(int arg1) throws Throwable {
		MockClient m = new MockClient();
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		m.readFromServer();

	}

	@And("^player (\\d+) start to roll all dices$")
	public void andPlayerRollAll(int arg1) throws Throwable {
		MockClient m = new MockClient();
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		m.writeToServer("1");
		Thread.sleep(2000);
	}

	@And("^player (\\d+) the result of first roll is \"([^\"]*)\"$")
	public void andPlayerresult(int arg1, String arg2) throws Throwable {
		MockClient m = new MockClient();

		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		t2 = m.readFromServer();
		assertTrue(t2.contains(arg2));
	}

	@And("^player (\\d+) end this turn$")
	public void andPlayerEndTurn(int arg1) throws Throwable {
		MockClient m = new MockClient();
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		m.writeToServer("2");
		Thread.sleep(2000);
	}

	@And("^player (\\d+) get (\\d+)$")
	public void andPlayerScore(int arg1, int arg2) throws Throwable {
		MockClient m = new MockClient();
		String t;
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		t = m.readFromServer();
		assertTrue(t.contains("Your score now is: " + arg2));
	}

	@And("^player (\\d+) dead with (\\d+) point of this turn$")
	public void andDead(int arg1, int arg2) throws Throwable {
		MockClient m = new MockClient();
		String t;
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		assertTrue(t2.contains("disqualified"));
		assertTrue(t2.contains("You final score of this turn is: " + arg2));
	}

	@And("^player (\\d+) reroll Dice \"([^\"]*)\" get \"([^\"]*)\"$")
	public void andPlayerReroll(int arg1, String arg2, String arg3) throws Throwable {
		MockClient m = new MockClient();
		String t;
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		m.writeToServer("1");
		Thread.sleep(2000);

		t = m.readFromServer();

		Thread.sleep(2000);
		m.writeToServer(arg2);
		Thread.sleep(2000);

		t2 = m.readFromServer();
		assertTrue(t2.contains(arg3));
	}

	@Then("^player (\\d+) can see player (\\d+) is the winner")
	public void thenWinner(int arg1, int arg2) throws Throwable {
		MockClient m = new MockClient();
		String t;
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		t = m.readFromServer();
		assertTrue(t.contains("Winner is " + arg2));
	}

	@And("^player (\\d+) get (\\d+) and win$")
	public void andPlayerScoreWin(int arg1, int arg2) throws Throwable {
		MockClient m = new MockClient();
		String t;
		if (arg1 == 1) {
			m = mc1;
		}

		if (arg1 == 2) {
			m = mc2;
		}

		if (arg1 == 3) {
			m = mc3;
		}

		t = m.readFromServer();
		assertTrue(t.contains("Your score now is: " + arg2));
		assertTrue(t.contains("You are the winner"));
	}

	@Then("^end the game")
	public void thenEndGame() throws Throwable {
		mc1.closeClient();
		mc2.closeClient();
		mc3.closeClient();
		ms.server.close();
		ms.client.close();
	}

}