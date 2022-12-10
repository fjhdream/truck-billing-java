package com.fjhdream.truckbilling.repository.entity;

import com.fjhdream.truckbilling.repository.enums.BillingStatusEnum;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "billing")
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Type(value = PostgreSQLEnumType.class)
    private BillingStatusEnum status;


    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @OneToMany(mappedBy = "billing")
    private Set<BillingItem> billingItems = new LinkedHashSet<>();

    @Size(max = 128)
    @Column(name = "commodity", length = 128)
    private String commodity;

    @Size(max = 128)
    @Column(name = "start_location", length = 128)
    private String startLocation;

    @Size(max = 128)
    @Column(name = "end_location", length = 128)
    private String endLocation;

    @Column(name = "start_money")
    private BigDecimal startMoney;

    @Column(name = "end_money")
    private BigDecimal endMoney;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_car_id", nullable = false)
    private TeamCar teamCar;

    public TeamCar getTeamCar() {
        return teamCar;
    }

    public void setTeamCar(TeamCar teamCar) {
        this.teamCar = teamCar;
    }

    public BigDecimal getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(BigDecimal endMoney) {
        this.endMoney = endMoney;
    }

    public BigDecimal getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(BigDecimal startMoney) {
        this.startMoney = startMoney;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public BillingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BillingStatusEnum status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Set<BillingItem> getBillingItems() {
        return billingItems;
    }

    public void setBillingItems(Set<BillingItem> billingItems) {
        this.billingItems = billingItems;
    }


}