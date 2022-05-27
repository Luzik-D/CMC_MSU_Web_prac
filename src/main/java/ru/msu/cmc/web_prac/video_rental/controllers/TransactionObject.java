package ru.msu.cmc.web_prac.video_rental.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionObject {
    private String clientPhone;
    private Integer copyId;
    private String date;
    private Integer amount;
}
