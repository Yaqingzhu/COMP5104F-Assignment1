
Feature: Basic dying
  I want to use this feature file to test row 38-42.

  @TestRow38
  Scenario: Does the player die on first roll?
    Given A player roll all 8 dices on the first roll
    When get 3 skulls
    Then player die and end of turn
    
  @TestRow39
  Scenario: Does the player die?
    Given A player roll all 8 dices on the first roll
    When the result of first roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:sword, 7:sword, 8:sword
    And hold dices "2,3,4,5", reroll dices "6,7,8" for test row 39
    And  the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:skull, 8:sword
    Then player die and end of turn

	@TestRow40
  Scenario: Does the player die?
    Given A player roll all 8 dices on the first roll
    When the result of first roll is 1:skull, 2:skull, 3:parrot, 4:parrot, 5:parrot, 6:parrot, 7:sword, 8:sword
    And hold dices "3,4,5,6", reroll dices "7,8" for test row 40
    And  the result of this roll is 1:skull, 2:skull, 3:parrot, 4:parrot, 5:parrot, 6:parrot, 7:skull, 8:sword for test row 40
    Then player die and end of turn 

	@TestRow41
  Scenario: Does the player die?
    Given A player roll all 8 dices on the first roll
    When the result of first roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:sword, 7:sword, 8:sword
    And hold dices "2,3,4,5", reroll dices "6,7,8" for test row 41
    And  the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:monkey, 8:monkey
    And hold dices "2,3,4,5", reroll dices "7,8" for test row 41
    And  the result of this roll is 1:skull, 2:parrot, 3:parrot, 4:parrot, 5:parrot, 6:skull, 7:skull, 8:monkey
    Then player die and end of turn  

