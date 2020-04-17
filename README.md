# Avatar Card Duel
## Description
Avatar Card Duel using gradle with JavaFX for GUI.

## How to compile
- In Linux: 
```script
./gradlew
```

- In Windows: 
```script
gradlew.bat
```


## How to play
1. Enter the players' name
2. Click play
3. You can draw a card (initial phase is draw phase) or go to the next phase or just end turn
4. In main phase, you can put card into the field or delete skill card in the field using this mechanism:
- Click a character card in your hand and click the bottom field's first row (on any column) to put the card
- Click a skill card in your hand and click a character card you want to apply the skill
- Click a skill card in the field and click delete button (remember only the player who put the skill that can remove it).
5. In main phase, you can also use land card (only 1 time) by click any land card you want to use.
6. In main phase, you can also put your character card in the field into defense mode or attack mode by just simply clicking it.
7. In battle phase, you can click your character card, and then:
- If enemy's field is empty, you can attack his/her health immediately
- If enemy's field is not empty, you must click any enemy's character card to attack it (in attack mode or with power up, your attack must be bigger than enemy's attack | in defense mode, your attack must be bigger  than enemy's defense)
8. Character cards that were used for attack goes to defense mode immediately and you can change it in Main Phase 2.
9. By doing end turn, the turn goes to the next player.

### Note:
- You can also double click to cancel selection or just click other card to select that other card.
- You win if your attack makes enemy's health into 0.
- You lose if your deck is empty when you're entering draw phase.

## Scenario Test GamePlay
1. Add more than one Aura Skill Game Card
1. 1. Add multiple aura skill on a game card
1. 2. Sum attribute point card manually and match with the display information in card  
2. Ensure only one power up for one character
2. 1. Add one power up skill on a game card
2. 2. Add one more power up skill on a game card = not applicable
2. 3. Attack an opponent's defense mode card = effect the opponent's HP
3. Ensure the battle mechanism fulfill the specification
3. 1. For attacking an opponent's attack mode card
3. 1. 1. Case for player's attack point > opponent's attack point = effect the opponent's HP
3. 1. 2. Case for player's attack point <= opponent's attack point = failed to attack
3. 2. For attacking an opponent's defense mode card without power up effect
3. 2. 1. Case for player's attack point > opponent's defense point = card destroyed
3. 2. 2. Case for player's attack point <= opponent's defense point = failed to attack
3. 3. For attacking an opponent's defense mode card with power up effect
3. 2. 1. Case for player's attack point > opponent's attack point = effect the opponent's HP
3. 2. 2. Case for player's attack point <= opponent's attack point = failed to attack