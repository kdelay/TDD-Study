package com.tdd.report;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class reportTest {

    private static final BillingUtils billingUtils = new BillingUtils();

    @Test
    @DisplayName("만료일 계산 기능 테스트")
    void calculateExpirationDate() {
        assertExpiredDateCal(
                LocalDateTime.of(2024, 5, 12, 0, 0),
                LocalDateTime.of(2024, 6, 11, 0, 0)
        );
        assertExpiredDateCal(
                LocalDateTime.of(2024, 5, 31, 0, 0),
                LocalDateTime.of(2024, 6, 30, 0, 0)
        );
    }

    private static void assertExpiredDateCal(LocalDateTime startDate, LocalDateTime expiredDate) {
        assertThat(billingUtils.calculateExpiryDate(startDate)).isEqualTo(expiredDate);
    }

    @Test
    @DisplayName("만료일 연장 기능 테스트")
    public void extendExpirationDate() {
        assertExpiredDateExtend(
                LocalDateTime.of(2024, 5, 12, 0, 0),
                LocalDateTime.of(2024, 7, 11, 0, 0),
                10_000
        );
        assertExpiredDateExtend(
                LocalDateTime.of(2024, 5, 31, 0, 0),
                LocalDateTime.of(2024, 7, 30, 0, 0),
                10_000
        );
    }

    private static void assertExpiredDateExtend(LocalDateTime startDate, LocalDateTime expiredDate, int price) {
        LocalDateTime expectedDate = null;

        if (price == 10_000) {
            expectedDate = billingUtils.extendExpiryDate(
                    billingUtils.calculateExpiryDate(startDate)
            );
        }

        assertThat(expectedDate).isEqualTo(expiredDate);
    }
}
