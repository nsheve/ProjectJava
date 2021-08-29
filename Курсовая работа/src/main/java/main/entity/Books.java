package main.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cnt")
    private Long cnt;
    @Column(name = "typeId")
    private Long typeId;

    @OneToMany(targetEntity = Journal.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    private List<Journal> journal = new ArrayList<>();


    public Books() {
    }

    public Books(String name, Long cnt, Long typeId) {
        this.name = name;
        this.cnt = cnt;
        this.typeId = typeId;
    }

}
