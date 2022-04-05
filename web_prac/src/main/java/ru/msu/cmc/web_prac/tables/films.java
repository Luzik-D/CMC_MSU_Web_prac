package web_prac.tables;

import  lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "film")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Films {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "id")
    private Long filmId;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "company")
    private String company;

    @Column(nullable = false, name = "director")
    private String director;

    @Column(nullable = false, name = "year_of_release")
    private String yearOfRelease;

    @Column(nullable = false, name = "description")
    private String description;
}
