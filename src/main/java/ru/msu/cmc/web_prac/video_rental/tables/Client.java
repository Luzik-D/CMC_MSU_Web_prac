package ru.msu.cmc.web_prac.video_rental.tables;

import lombok.*;

import javax.persistence.*;

/* TABLE Client INTO JAVA CLASS */

@Entity
@Table(name="Client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client implements AbstractTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    public Client(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
