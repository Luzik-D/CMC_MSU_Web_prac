package ru.msu.cmc.web_prac.video_rental.tables;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/* TABLE "Film" INTO JAVA CLASS */

@Entity
@Table(name="Film")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film implements AbstractTable {
    @Id
    @Column(name = "film_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "year_of_release", nullable = false)
    private String yearOfRelease;
}
