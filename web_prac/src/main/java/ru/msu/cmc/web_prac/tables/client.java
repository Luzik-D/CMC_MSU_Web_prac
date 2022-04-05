package web_prac.tables;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "id")
    private Long clientId;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "phone")
    private String phone;

    @Column(nullable = false, name = "address")
    private String address;
}