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
public class ClientHistoryRecord implements AbstractTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @Column(name = "date_of_transfer", nullable = false)
    private String dateOfTransfer;

    @Column(name = "date_of_receipt", nullable = false)
    private String dateOfReceipt;

    @Column(name = "actual_date_of_receipt", nullable = false)
    private String actualDateOfReceipt;

    @Column(name = "transfer_amount", nullable = false)
    private int transferAmount;

    public ClientHistoryRecord(Client client, Copy copy, String dateOT, String dateOR, String aDateOR, int transferAmount) {
        this.client = client;
        this.copy = copy;
        this.dateOfTransfer = dateOT;
        this.dateOfReceipt = dateOR;
        this.actualDateOfReceipt = aDateOR;
        this.transferAmount = transferAmount;
    }
}
