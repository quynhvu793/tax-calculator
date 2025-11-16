package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeeCalculator {
    public static double calculateMonthlyFee(String studentId, double lunchFee, double pickupFee, double extraFee) {
        double totalFee = 0;
        String sql = "SELECT COUNT(*) AS days_present, c.basic_fee " +
                "FROM AttendanceDetail d " +
                "JOIN AttendanceRecord r ON d.attendance_id = r.attendance_id " +
                "JOIN Student s ON d.student_id = s.student_id " +
                "JOIN Class c ON s.class_id = c.class_id " +
                "WHERE d.status = 'Present' AND s.student_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int daysPresent = rs.getInt("days_present");
                double basicFee = rs.getDouble("basic_fee");
                totalFee = daysPresent * basicFee + lunchFee + pickupFee + extraFee;
            }

        } catch (SQLException e) {
            System.out.println("Lỗi tính học phí: " + e.getMessage());
        }

        return totalFee;
    }
}
