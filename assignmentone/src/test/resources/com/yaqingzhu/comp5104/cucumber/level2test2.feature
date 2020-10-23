Feature: test2 of level2
  I want to use this feature file to test row 127

 @TestRow127
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
    When player 1 get FC card
    And player 1 start to roll all dices
    And player 1 the result of first roll is "Skull,Skull,Skull,Parrot,Sword,Gold,Monkey,Sword"
    And player 1 dead with 0 point of this turn
    When player 2 get FC card
    And player 2 start to roll all dices
    And player 2 the result of first roll is "Skull,Gold,Skull,Gold,Sword,Gold,Monkey,Sword"
    And player 2 reroll Dice "5,6,7,8" get "Skull,Gold,Skull,Gold,Sword,Gold,Sword,Sword"
    And player 2 end this turn
    And player 2 get 1400
    When player 3 get FC card
    And player 3 start to roll all dices
    And player 3 the result of first roll is "Diamond,Diamond,Skull,Gold,Sword,Gold,Monkey,Sword"
    And player 3 reroll Dice "5,6,7,8" get "Diamond,Diamond,Skull,Gold,Skull,Skull,Diamond,Gold"
    And player 3 dead with 0 point of this turn
    When player 1 get FC card
    And player 1 start to roll all dices
    And player 1 the result of first roll is "Sword,Sword,Skull,Sword,Sword,Gold,Monkey,Sword"
    And player 1 end this turn
    And player 1 get 1100
    When player 2 get FC card
    And player 2 start to roll all dices
    And player 2 the result of first roll is "Skull,Gold,Skull,Sword,Sword,Gold,Monkey,Sword"
    And player 2 end this turn
    And player 2 get 1900
    When player 3 get FC card
    And player 3 start to roll all dices
    And player 3 the result of first roll is "Gold,Gold,Gold,Gold,Gold,Gold,Gold,Gold"
    And player 3 end this turn
    And player 3 get 6000 and win
    Then player 1 can see player 3 is the winner
    Then player 2 can see player 3 is the winner
    Then end the game

