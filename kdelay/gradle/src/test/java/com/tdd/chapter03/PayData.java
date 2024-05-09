package com.tdd.chapter03;

import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int payMount;

    //constructor
    public PayData() {
    }

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int payMount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.payMount = payMount;
    }

    //getter
    public LocalDate getFirstBillingDate() { return firstBillingDate; }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPayMount() {
        return payMount;
    }

    //builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PayData data = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            data.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }
        public Builder payAmount(int payAmount) {
            data.payMount = payAmount;
            return this;
        }
        public PayData build() {
            return data;
        }
    }
}
