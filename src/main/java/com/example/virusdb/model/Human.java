package com.example.virusdb.model;




import com.example.virusdb.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="humans")
public class Human {
    @JsonView(View.ShortHuman.class)
    @Id
    @SequenceGenerator(name="human_sequence", sequenceName = "human_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="human_sequence")
    @Column(name="id",nullable = false)
    private Long id;
    @JsonView(View.ShortHuman.class)
    @OneToMany(targetEntity = Injury.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="humanId")
    private List<Injury> injuries;
    @JsonView(View.ShortHuman.class)
    @Temporal(TemporalType.DATE)
    @Column(name="deathDate", nullable = true)
    private Date deathDate;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Entity
    @Table(name="injuries")
    public static class Injury
    {
        /*@ManyToOne
        @JoinColumn(name="human_id")
        private Human human;*/
        @JsonView({View.ShortHuman.class, View.ShortInjury.class})
        @Id
        @SequenceGenerator(name="injury_sequence", sequenceName = "injury_sequence",allocationSize = 1, initialValue = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="injury_sequence")
        @Column(name="id",nullable = false)
        private Long id;
        @JsonView({View.ShortHuman.class, View.ShortInjury.class})
        @ManyToOne(targetEntity=Virus.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name="virusId")
        private Virus virus;
        @JsonView({View.ShortHuman.class, View.ShortInjury.class})
        @Temporal(value=TemporalType.DATE)
        @Column(name="infectionDate")
        private Date infectionDate;
        @JsonView({View.ShortHuman.class, View.ShortInjury.class})
        @Enumerated(value = EnumType.STRING)
        @Column(name= "virusStage")
        VirusStage virusStage;
        private enum VirusStage{ATTACHED,PENETRATED,STRIPPED_OFF_SHELLS,REPLICATION,RECOVERED}

        public Injury(){}
        public Injury(Virus virus, Date infectionDate, VirusStage virusStage, Date deathDate) {
            this.virus = virus;
            this.infectionDate = infectionDate;
            this.virusStage = virusStage;
        }


        public Virus getVirus() {
            return virus;
        }

        public Date getInfectionDate() {
            return infectionDate;
        }

        public VirusStage getVirusStage() {
            return virusStage;
        }

        public Long getId() {
            return id;
        }
    }

    public Human() {
    }

    public Long getId() {
        return id;
    }

    public List<Injury> getInjuries() {
        return injuries;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate)
    {
        this.deathDate = deathDate;
    }
}
