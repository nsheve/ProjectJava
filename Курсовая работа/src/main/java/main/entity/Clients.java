package main.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "patherName")
    private String patherName;

    @Column(name = "passportSeria")
    private String passportSeria;

    @Column(name = "passportNum")
    private String passportNum;

    @OneToMany(targetEntity = Journal.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private List<Journal> journal = new ArrayList<>();

    public Clients() {
    }

    public Clients(String firstName, String lastName, String patherName, String passportSeria, String passportNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.passportSeria = passportSeria;
        this.passportNum = passportNum;
    }

}
