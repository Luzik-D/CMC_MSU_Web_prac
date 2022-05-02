package ru.msu.cmc.web_prac.video_rental.tables;

import lombok.*;

import javax.persistence.*;

/*TABLE ClientHistoryRecord INTO JAVA CLASS */

@Entity
@Table(name="ClientHistoryRecord")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientHistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId
    private Client clientId;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId
    private Copy copyId;

    @Column(name = "date_of_transfer", nullable = false)
    private String dateOfTransfer;

    @Column(name = "date_of_receipt", nullable = false)
    private String dateOfReceipt;

    @Column(name = "actual_date_of_receipt", nullable = false)
    private String actualDateOfReceipt;

    @Column(name = "transfer_amount", nullable = false)
    private int transferAmount;
}
