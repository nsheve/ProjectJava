package main.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "Journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "dateBeg")
    private Timestamp dateBeg;

    @Column(name = "dateEnd")
    private Timestamp dateEnd;

    @Column(name = "dateRet")
    private Timestamp dateRet;

    public Journal() {
    }

    public Journal(Long bookId, Long clientId, Timestamp dateBeg, Timestamp dateEnd,Timestamp dateRet){
        this.bookId = bookId;
        this.clientId = clientId;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
    }
}
