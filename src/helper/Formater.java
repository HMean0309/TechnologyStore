package helper;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Formater {

    public static String formatVND(double vnd) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(vnd) + "đ";
    }

    public static String formatTime(LocalDateTime datetime) {
        // Định nghĩa định dạng        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        // Chuyển đổi LocalDateTime sang String
        return datetime.format(formatter);
    }

    public static String formatDate(LocalDate date) {
        // Định nghĩa định dạng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi LocalDate sang String
        return date.format(formatter);
    }
}
