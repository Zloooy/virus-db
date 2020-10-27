package com.example.virusdb.model;

import javax.persistence.*;

@Entity
@Table(name="viruses")
public class Virus {
    @Id
    @SequenceGenerator(name="virus_sequence", sequenceName = "virus_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="virus_sequence")
    @Column(name="id",nullable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name="type",nullable = false)
    private VirusType type;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name="firstTransmissionProbability",nullable = false)
    private Float firstTransmissionProbability;
    @Column(name="averageContractTime",nullable = false)
    private Integer averageContractTime;
    @Column(name="mortality",nullable = false)
    private Float mortality;
    @Column(name="secondTransmissionProbability",nullable = false)
    private Float secondTransmissionProbability;
    public enum VirusType
    {RNA,DNA,RETROVIRUS}

    public Virus(){};
    public Virus(VirusType type, String name, Float firstTransmissionProbability, Integer averageContractTime, Float mortality, Float secondTransmissionProbability) {
        this.type = type;
        this.name = name;
        this.firstTransmissionProbability = firstTransmissionProbability;
        this.averageContractTime = averageContractTime;
        this.mortality = mortality;
        this.secondTransmissionProbability = secondTransmissionProbability;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id)
    {
        if (this.id!=null) throw new IllegalStateException("This entity already has an id");
        else this.id = id;
    }

    public VirusType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Float getFirstTransmissionProbability() {
        return firstTransmissionProbability;
    }

    public Integer getAverageContractTime() {
        return averageContractTime;
    }

    public Float getMortality() {
        return mortality;
    }

    public Float getSecondTransmissionProbability() {
        return secondTransmissionProbability;
    }
}
