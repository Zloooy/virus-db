package com.example.virusdb.model;




import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="humans")
public class Human {
    @Id
    @SequenceGenerator(name="human_sequence", sequenceName = "human_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="human_sequence")
    @Column(name="id",nullable = false)
    private Long id;
    @OneToMany(targetEntity = Injury.class, cascade = CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name="humanId")
    private List<Injury> injuries;

    @Entity
    @Table(name="injuries")
    public static class Injury
    {
        /*@ManyToOne
        @JoinColumn(name="human_id")
        private Human human;*/
        @Id
        @SequenceGenerator(name="injury_sequence", sequenceName = "injury_sequence",allocationSize = 1, initialValue = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="injury_sequence")
        @Column(name="id",nullable = false)
        private Long id;
        @ManyToOne(targetEntity=Virus.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name="virusId")
        private Virus virus;
        @Temporal(value=TemporalType.DATE)
        @Column(name="infectionDate")
        private Date infectionDate;
        @Enumerated(value = EnumType.STRING)
        @Column(name= "virusStage")
        VirusStage virusStage;
        @Temporal(TemporalType.DATE)
        @Column(name="deathDate")
        private Date deathDate;
        private enum VirusStage{ATTACHED,PENETRATED,STRIPPED_OFF_SHELLS,REPLICATION,RECOVERED}

        public Injury(Virus virus, Date infectionDate, VirusStage virusStage, Date deathDate) {
            this.virus = virus;
            this.infectionDate = infectionDate;
            this.virusStage = virusStage;
            this.deathDate = deathDate;
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

        public Date getDeathDate() {
            return deathDate;
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

}
