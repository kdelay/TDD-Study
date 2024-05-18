package com.tdd.report;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Billing {

    private double total;
    private double tax;
    private LocalDateTime date;
    private PaymentMethod paymentMethod;

    public Billing(double total, double tax, LocalDateTime date, PaymentMethod paymentMethod) {
        this.total = total;
        this.tax = tax;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

}

