package com.mycompany.training;

public class PaymentStatusController {
    private static boolean isPaid = false;

    public static boolean isPaid() {
        return isPaid;
    }

    public static void setPaid(boolean status) {
        isPaid = status;
    }
}
