package com.ynov.b3info.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Obstacle {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
	private boolean isTraversables;
    private String name;
    private Integer effectId;
    private Integer appearanceId;
    private Integer maxInLevel;
    private Integer minInLevel;

    public Integer getMinInLevel() {
        return minInLevel;
    }

    public void setMinInLevel(Integer minInLevel) {
        this.minInLevel = minInLevel;
    }

    public Integer getMaxInLevel() {
        return maxInLevel;
    }

    public void setMaxInLevel(Integer maxInLevel) {
        this.maxInLevel = maxInLevel;
    }

    public Integer getAppearanceId() {
        return appearanceId;
    }

    public void setAppearanceId(Integer appearanceId) {
        this.appearanceId = appearanceId;
    }

    public Integer getEffectId() {
        return effectId;
    }

    public void setEffectId(Integer effectId) {
        this.effectId = effectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTraversables() {
        return isTraversables;
    }

    public void setTraversables(boolean isTraversables) {
        this.isTraversables = isTraversables;
    }
}
