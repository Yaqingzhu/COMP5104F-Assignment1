@tag
Feature: test1 of level2
  I want to use this feature file to test row 126

@TestRow126
  Scenario: game starts, each player plays a turn with scores being updated correctly 
    Given Start a game server
    And three players join
    When player 1 get FC card
    And player 1 start to roll all dices
    And player 1 the result of first roll is "Diamond,Skull,Monkey,Parrot,Sword,Gold,Monkey,Monkey"
    And player 1 end this turn
    And player 1 get 400
    When player 2 get FC card
    And player 2 start to roll all dices
    And player 2 the result of first roll is "Monkey,Monkey,Gold,Parrot,Sword,Gold,Monkey,Monkey"
    And player 2 end this turn
    And player 2 get 700
    When player 3 get FC card
    And player 3 start to roll all dices
    And player 3 the result of first roll is "Sword,Sword,Gold,Parrot,Sword,Gold,Gold,Sword"
    And player 3 end this turn
    And player 3 get 600
    Then end the game
