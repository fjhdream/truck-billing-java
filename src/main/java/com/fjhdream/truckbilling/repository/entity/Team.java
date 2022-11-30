package com.fjhdream.truckbilling.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 128)
    @NotNull
    @Column(name = "team_name", nullable = false, length = 128)
    private String teamName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "team")
    private Set<Item> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<Billing> billings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<TeamCar> teamCars = new LinkedHashSet<>();

    @OneToMany(mappedBy = "team")
    private Set<TeamDriver> teamDrivers = new LinkedHashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Set<Billing> getBillings() {
        return billings;
    }

    public void setBillings(Set<Billing> billings) {
        this.billings = billings;
    }

    public Set<TeamCar> getTeamCars() {
        return teamCars;
    }

    public void setTeamCars(Set<TeamCar> teamCars) {
        this.teamCars = teamCars;
    }

    public Set<TeamDriver> getTeamDrivers() {
        return teamDrivers;
    }

    public void setTeamDrivers(Set<TeamDriver> teamDrivers) {
        this.teamDrivers = teamDrivers;
    }

}