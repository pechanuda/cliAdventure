package org.pechanuda.model;

public class Monster {
    public Monster(int hitPoints, int attack, int defense, Item loot) {
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.loot = loot;
    }

    public Monster() {
    }

    private int hitPoints;
    private int attack;
    private int defense;
    private Item loot;

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public Item getLoot() {
        return loot;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }
}
