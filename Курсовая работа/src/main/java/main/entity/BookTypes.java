package main.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Book_types")
public class BookTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "cnt")
    private Long cnt;

    @Column(name = "fine")
    private Long fine;

    @Column(name = "dayCount")
    private Long dayCount;

    @OneToMany(targetEntity = Books.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    private List<Books> books = new ArrayList<>();

    public BookTypes() {
    }

    public BookTypes(String name, Long cnt, Long fine, Long dayCount) {
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }

}
