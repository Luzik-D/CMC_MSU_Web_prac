package ru.cs.msu.web_prac.tables;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "Client history record")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClientHistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name = "id")
    private Long recordId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Client clientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Copy copyId;

    @Column(nullable = false, name = "date_of_transfer")
    private String dateOfTransfer;

    @Column(nullable = true, name = "date_of_receipt")
    private String dateOfReceipt;

    @Column(nullable = true, name = "actual_date_of_receipt")
    private String actualDateOfReceipt;

    @Column(nullable = false, name = "transfer_amount")
    private Long transferAmount;
}