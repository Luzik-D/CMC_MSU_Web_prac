package ru.msu.cmc.web_prac.video_rental.tables;

import lombok.*;

import javax.persistence.*;
import java.security.GeneralSecurityException;

/* TABLE Copy INTO JAVA CLASS */
@Entity
@Table(name="Copy")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Copy implements AbstractTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "copy_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    private Film film_id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private int price;
}
