package com.fjhdream.truckbilling.repository.entity;

import com.fjhdream.truckbilling.repository.enums.UseStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "team_car")
public class TeamCar {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Size(max = 255)
    @NotNull
    @Column(name = "car_plate_number", nullable = false)
    private String carPlateNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UseStatusEnum status;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public void setCarPlateNumber(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
    }

    public UseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UseStatusEnum status) {
        this.status = status;
    }
}