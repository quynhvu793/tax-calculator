package org.technical;

public class Main {
    public static void main(String[] args) {
        // Tính học phí tháng
        double fee = FeeCalculator.calculateMonthlyFee("Class1_An", 10000, 20000, 15000);
        System.out.println("Học phí tháng của Class1_An: " + fee);

        // Thống kê điểm danh
        Attendance.attendanceSummary("Class1", "2025-11-15");
    }
}
