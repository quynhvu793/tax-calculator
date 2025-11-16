package org.technical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Attendance {
    public static void attendanceSummary(String classId, String date) {
        String sqlTotal = "SELECT COUNT(*) AS total_students " +
                "FROM Student WHERE class_id = ?";
        String sqlPresent = "SELECT COUNT(*) AS present_count " +
                "FROM AttendanceDetail d " +
                "JOIN AttendanceRecord r ON d.attendance_id = r.attendance_id " +
                "WHERE r.class_id = ? AND r.date = ? AND d.status = 'Present'";
        String sqlAbsentList = "SELECT s.student_name " +
                "FROM AttendanceDetail d " +
                "JOIN AttendanceRecord r ON d.attendance_id = r.attendance_id " +
                "JOIN Student s ON d.student_id = s.student_id " +
                "WHERE r.class_id = ? AND r.date = ? AND d.status = 'Absent'";

        try (Connection conn = Database.getConnection()) {
            // Tổng số học sinh
            PreparedStatement pstmtTotal = conn.prepareStatement(sqlTotal);
            pstmtTotal.setString(1, classId);
            ResultSet rsTotal = pstmtTotal.executeQuery();
            int totalStudents = rsTotal.next() ? rsTotal.getInt("total_students") : 0;

            // Số học sinh đi học
            PreparedStatement pstmtPresent = conn.prepareStatement(sqlPresent);
            pstmtPresent.setString(1, classId);
            pstmtPresent.setString(2, date);
            ResultSet rsPresent = pstmtPresent.executeQuery();
            int presentCount = rsPresent.next() ? rsPresent.getInt("present_count") : 0;

            // Danh sách học sinh vắng
            PreparedStatement pstmtAbsent = conn.prepareStatement(sqlAbsentList);
            pstmtAbsent.setString(1, classId);
            pstmtAbsent.setString(2, date);
            ResultSet rsAbsent = pstmtAbsent.executeQuery();
            ArrayList<String> absentStudents = new ArrayList<>();
            while (rsAbsent.next()) {
                absentStudents.add(rsAbsent.getString("student_name"));
            }

            System.out.println("Lớp: " + classId + ", Ngày: " + date);
            System.out.println("Tổng học sinh: " + totalStudents);
            System.out.println("Đi học: " + presentCount);
            System.out.println("Vắng: " + absentStudents.size());
            System.out.println("Danh sách học sinh vắng: " + absentStudents);

        } catch (SQLException e) {
            System.out.println("Lỗi attendanceSummary: " + e.getMessage());
        }
    }
}
