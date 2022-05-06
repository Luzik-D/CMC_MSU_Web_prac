package ru.msu.cmc.web_prac.video_rental.tables;

import lombok.*;

import javax.persistence.*;

/* TABLE "Film" INTO JAVA CLASS */

@Entity
@Table(name="Film")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class Film implements AbstractTable {
    @Id
    @Column(name = "film_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "year_of_release", nullable = false)
    private String yearOfRelease;

    @Column(name = "description", nullable = false)
    private String description;

    //constructor to insert (without id)
    public Film(String title, String company, String director, String yearOfRelease, String description) {
        this.title = title;
        this.company = company;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.description = description;
    }
}
