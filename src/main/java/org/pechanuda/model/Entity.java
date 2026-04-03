package org.pechanuda.model;

public abstract class Entity {
    String name;
    int id;

    public Entity() {
    }

    public Entity(String name) {
        this.name = name;
    }

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
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
