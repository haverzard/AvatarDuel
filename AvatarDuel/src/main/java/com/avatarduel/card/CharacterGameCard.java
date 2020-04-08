package com.avatarduel.card;
import java.util.List;
import java.util.ArrayList;
import com.avatarduel.element.Element;
import com.avatarduel.card.PowerUpSkillGameCard;

// Concrete Prototype
public class CharacterGameCard extends GameCard implements HasCostAttribute, HasBattleAttribute {
    private int cost;
    private int attack;
    private int defense;
    private PowerUpSkillGameCard powerUpCard;
    private List<AuraSkillGameCard> listofAura;
    public CharacterGameCard() {
        super();
        cost = 0;
        attack = 0;
        defense = 0;
        bonusAttack = 0;
        bonusDefense = 0;
        powerUpCard = null;
        this.listofAura = new ArrayList<AuraSkillGameCard>();
    }

    public CharacterGameCard(String _name, String _desc, Element _element, String _imgUrl, int _cost, int _attack, int _defense) {
        super(_name,_desc,_element, _imgUrl);
        cost = _cost;
        attack = _attack;
        defense = _defense;
        powerUpCard = null;
        this.listofAura = new ArrayList<AuraSkillGameCard>();
    }

    public GameCard clone() {
        return new CharacterGameCard(getName(), getDesc(), getElement(), getImgUrl(), cost, attack, defense);
    }

    // Getter & Setter
    public int getCost() {
        return cost;
    }

    public void setCost(int _cost) {
        cost = _cost;
    }

    public int getAttack() {
        int bonusAttack = 0;
        for (AuraSkillGameCard x : this.listofAura){
            this.bonusAttack += x.getAttackAura();
        }
        return attack + bonusAttack;
    }

    public void setAttack(int _attack) {
        attack = _attack;
    }

    public int getDefense() {
        int bonusDefense = 0;
        for (AuraSkillGameCard x : this.listofAura){
            this.bonusDefense += x.getDefenseAura();
        }
        return defense + bonusDefense;
    }

    public void setDefense(int _defense) {
        defense = _defense;
    }

    public void addAuraSkill (AuraSkillGameCard newCard){
        this.listofAura.add(newCard);
    }

    public void removeAuraSkill (AuraSkillGameCard newCard){
        this.listofAura.remove(newCard);
    }

    public void nullifyAllSkill (){
        this.bonusAttack = 0;
        this.bonusDefense = 0;
        this.listofAura.clear();
        powerUpCard = null;
    }

    public void setPowerUpinField(PowerUpSkillGameCard card){
        powerUpCard = card;
    }

    public void detachedPowerUpinField(){
        powerUpCard = null;
    }

    public AuraSkillGameCard getAuraSkillGameCard(int i) {
        return listofAura.get(i);
    }

    public List<AuraSkillGameCard> getAuraSkillGameCardsList() {
        return listofAura;
    }

    public boolean isAttachedPowerUpinField() {
        return powerUpCard != null;
    }

    public PowerUpSkillGameCard getPowerUpSkillGameCard() {
        return powerUpCard;
    }
}
