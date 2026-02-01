package org.pechanuda.model;

public abstract class Entity {
    String name;
    int id;

    public Entity() {
    }

    public Entity(String name) {
        this.name = name;
    }

    public Entity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
