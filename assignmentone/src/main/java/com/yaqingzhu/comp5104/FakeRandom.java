package com.yaqingzhu.comp5104;

import java.util.ArrayList;

public class FakeRandom {

	public static int FC_counter = 0;
	public static int Roll_counter = 0;
	public static int Roll_counter_4 = 0;
	
	ArrayList<String> dices_8;
	ArrayList<String> dices_4;
	public int getFakeRandom() {
		return FC_counter++;
	}
	
	public String getFakeRandomDice(int num) {
		if(num == 8)
			return dices_8.get(Roll_counter++);
		else
			return dices_4.get(Roll_counter_4++);
	}
	
	public static void resetCount() {
		FC_counter = 0;
		Roll_counter = 0;
		Roll_counter_4 = 0;
	}
	
	public FakeRandom() {
		dices_8 = new ArrayList();
		dices_4 = new ArrayList();
		
		dices_8.add("Diamond,Skull,Monkey,Parrot,Sword,Gold,Monkey,Monkey");
		dices_8.add("Monkey,Monkey,Gold,Parrot,Sword,Gold,Monkey,Monkey");
		dices_8.add("Sword,Sword,Gold,Parrot,Sword,Gold,Gold,Sword");
		dices_8.add("Skull,Skull,Skull,Parrot,Sword,Gold,Monkey,Sword");
		dices_8.add("Skull,Gold,Skull,Gold,Sword,Gold,Monkey,Sword");
		dices_8.add("Diamond,Diamond,Skull,Gold,Sword,Gold,Monkey,Sword");
		dices_8.add("Sword,Sword,Skull,Sword,Sword,Gold,Monkey,Sword");
		dices_8.add("Skull,Gold,Skull,Sword,Sword,Gold,Monkey,Sword");
		dices_8.add("Gold,Gold,Gold,Gold,Gold,Gold,Gold,Gold");

		dices_4.add("Sword,Gold,Sword,Sword");
		dices_4.add("Skull,Skull,Diamond,Gold");

	}
}
