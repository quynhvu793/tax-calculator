package org.technical;

public class TaxCalculator {

    public static double calculateTax(double income) {
        if (income < 0) {
            throw new IllegalArgumentException("Thu nhập không hợp lệ!");
        }

        double rate;
        if (income <= 5) {
            rate = 0.05;
        } else if (income <= 10) {
            rate = 0.10;
        } else if (income <= 18) {
            rate = 0.15;
        } else if (income <= 32) {
            rate = 0.20;
        } else if (income <= 52) {
            rate = 0.25;
        } else if (income <= 80) {
            rate = 0.30;
        } else {
            rate = 0.35;
        }

        return income * rate;
    }

    public static void main(String[] args) {
        double[] incomes = {4, 7, 15, 25, 70, -10};

        for (double income : incomes) {
            try {
                double tax = calculateTax(income);
                System.out.printf("Thu nhập: %.2f triệu → Thuế phải nộp: %.2f triệu%n", income, tax);
            } catch (Exception e) {
                System.out.printf("Thu nhập: %.2f triệu → Lỗi: %s%n", income, e.getMessage());
            }
        }
    }
}
