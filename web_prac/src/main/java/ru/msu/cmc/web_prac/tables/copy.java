package web_prac.tables;

import lombok.*;
import javax.persistnence.*;

@Entity
@Table(name = "Copy")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "id")
    private Long copyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Film filmId;

    @Column(nullable = false, name = "type")
    private String type;

    @Column(nullable = false, name = "status")
    private String status;

    @Column(nullable = false, name = "price")
    private Long price;
}