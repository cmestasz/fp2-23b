package FX.MainGame.Classes;

import java.io.Serializable;

import javax.swing.*;

public abstract class Soldier implements Serializable {
    private String name;
    private int team;
    private int initialHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private String type;

    public Soldier(String name, int team, int initialHealth, int attack, int defense, String type) {
        this.name = name;
        this.team = team;
        this.initialHealth = initialHealth;
        this.currentHealth = initialHealth;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getTeam() {
        return team;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getType() {
        return type;
    }

    public void heal() {
        currentHealth++;
    }

    public void attack(Soldier other, int instances) {
        other.hurt(instances * (Math.max(0, attack - other.getDefense())));
    }

    public void hurt(int vida) {
        currentHealth -= vida;
    }

    public void defend() {
        defense++;
    }
}
