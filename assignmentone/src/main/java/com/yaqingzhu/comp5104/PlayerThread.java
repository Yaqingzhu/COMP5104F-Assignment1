package com.yaqingzhu.comp5104;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class PlayerThread{
	
	
	PrintStream out;
	BufferedReader buf;
	private boolean isFakeRamdon = false;
	private Player player;
	private ArrayList<Card> allCards;

	public boolean isFakeRamdon() {
		return isFakeRamdon;
	}

	public void setFakeRamdon(boolean isFakeRamdon) {
		this.isFakeRamdon = isFakeRamdon;
		this.player.setFakeRandom(this.isFakeRamdon());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;		
	}
	public void setSocket(Socket client) {
		try {
			out = new PrintStream(client.getOutputStream());
			buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.println("Welcome to the game! Please wait for other players.");
	}

	public PlayerThread() {
		this.setPlayer(new Player());
		this.allCards = new ArrayList<Card>();
		allCards.add(new MonkeyBusinessCard());
		allCards.add(new MonkeyBusinessCard());
		allCards.add(new MonkeyBusinessCard());
		allCards.add(new MonkeyBusinessCard());
		allCards.add(new GoldCard());
		allCards.add(new GoldCard());
		allCards.add(new GoldCard());
		allCards.add(new GoldCard());
		allCards.add(new DiamondCard());
		allCards.add(new DiamondCard());
		allCards.add(new DiamondCard());
		allCards.add(new DiamondCard());
		allCards.add(new SorceressCard());
		allCards.add(new SorceressCard());
		allCards.add(new SorceressCard());
		allCards.add(new SorceressCard());
		allCards.add(new CaptainCard());
		allCards.add(new CaptainCard());
		allCards.add(new CaptainCard());
		allCards.add(new CaptainCard());
		allCards.add(new TreasureChestCard());
		allCards.add(new TreasureChestCard());
		allCards.add(new TreasureChestCard());
		allCards.add(new TreasureChestCard());
		allCards.add(new SkullsCard());
		allCards.add(new SkullsCard());
		allCards.add(new SkullsCard());
		allCards.add(new SkullCardTwo());
		allCards.add(new SkullCardTwo());
		allCards.add(new SeaBattleCardTwo());
		allCards.add(new SeaBattleCardTwo());
		allCards.add(new SeaBattleCardThree());
		allCards.add(new SeaBattleCardThree());
		allCards.add(new SeaBattleCardFour());
		allCards.add(new SeaBattleCardFour());
		
	}
	
	public void runYourRound() {
		boolean flag = true;
		int times = 0;
		
		out.println("INFO: It's your turn.");
		drawCard();
		while(flag) {
			out.println("INFO: Please select option 1. (re)roll; 2. end this round. your input?");
			String str = "";
			String option1Return = "";
			try {
				str = buf.readLine();
				int option = Integer.parseInt(str);
				
				if(option > 2) {
					out.println("INFO: You have to select option 1. (re)roll; 2. end this round; 3. Treasure chest(when you have Treasure chest card). your input?");
					str = buf.readLine();
				}
				
				switch(option) {
				case 1:
					out.println("INFO: You selected option 1. (re)rolling.....");
					if(times == 0) {
						option1Return = this.getPlayer().processInput(1, "1,2,3,4,5,6,7,8");
						out.println(option1Return);
						times++;
					} else {
						out.println("INFO: Please select which dice you want to reroll. If you want to reroll dice 1 and 2, please input 1,2. your input?");
						str = buf.readLine();
						String[] temp = str.split(",");
						option1Return = this.getPlayer().processInput(1, str);
						out.println(option1Return);
					}
					
					if(option1Return.contains("disqualified")) {
						out.println("INFO: You final score of this turn is: " + getPlayer().getRound().getScoreOfRound());
						out.println("INFO: You score now is: " + getPlayer().getScore());
						flag = false;
					}
						
					break;
				case 2:
					out.println("INFO: You selected to end this turn. You final score of this turn is: " + getPlayer().getRound().getScoreOfRound());
					out.println(this.getPlayer().processInput(2, ""));
					flag = false;
					break;
				case 3:
					out.println("INFO: Please select which dice you want to put in treasure chest?");
					str = buf.readLine();
					out.println("INFO: " + this.getPlayer().processInput(3, str));
					break;
				default:
					out.println("INFO: You have to select option 1. (re)roll; 2. end this round; 3. Treasure chest(when you have Treasure chest card)");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		out.println("INFO: Your turn finished.");
		if(getPlayer().getScore() >= 6000) {
			out.println("INFO: You are the winner.");
		}
	}
	
	public void broadCast(String message) {
		out.println("INFO: " + message);
	}
	
	private void drawCard() {
		Random random = new Random();
		int number = random.nextInt(33);
		Card ca;
		if(this.isFakeRamdon()) {
			ca = allCards.get(new FakeRandom().getFakeRandom());
		}else {
			ca = allCards.get(number);
		}
		
		
		getPlayer().getRound().setActiveCard(ca);
		out.println("INFO: You get " + ca.getCardName() +" Card at the beginning.");
	}

}
