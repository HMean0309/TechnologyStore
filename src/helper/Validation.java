package helper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;


public class Validation {

    public static String[] messPassword = {
            "Độ dài mật khẩu ít nhất 6 kí tự và không quá 20 kí tự"
    };
    public static String[] messName = {
            "Độ dài tên không quá 50 kí tự",
            "Tên không được có ký tự số",
            "Tên không được có ký tự đặc biệt",
            "Tên phải đảm bảo các chữ cái đầu luôn được viết hoa"
    };

    public static Boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return input.isEmpty();
    }

    public static Boolean isEmail(String email) {
        //Yêu cầu: Đúng định dạng của email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        if (email == null) {
            return false;
        }
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isOTP(String OTP) {
        //Yêu cầu: đúng định dạng của OTP
        String OTPRegex = "\\d{6}";
        if (OTP == null) {
            return false;
        }
        return Pattern.matches(OTPRegex, OTP);
    }

    public static boolean isNumberPhone(String phone) {
        //Yêu cầu: Đúng định dạng của số điện thoại
        String phoneRegex = "^0\\d{9}$";
        if (phone == null) {
            return false;
        }
        return Pattern.matches(phoneRegex, phone);
    }

    public static int isPassword(String pass) {
        int index = -1;
        //Lỗi: Độ dài mật khẩu ít hơn 6 kí tự hay vượt quá 20 kí tự
        if (pass.length() < 6 || pass.length() > 20) {
            return 0;
        }

        return index;
    }

    public static int isName(String name) {
        int index = -1;
        //Lỗi: Độ dài vượt quá 50 kí tự
        if (name.length() > 50) {
            return 0;
        }
        //Lỗi: Tồn tại ký tự số
        if (Pattern.matches(".*\\d.*", name)) {
            return 1;
        }
        //Lỗi: Tồn tại có ký tự đặc biệt
        if (Pattern.matches(".*[^a-zA-Z0-9\\s].*", name.replaceAll("[^\\x00-\\x7F]", ""))) {
            return 2;
        }
        //Yêu cầu: Đảm bảo chữ cái luôn được viết hoa
        if (!Pattern.matches("^[\\p{Lu}][\\p{Ll}\\p{M}]*(?:\\s[\\p{Lu}][\\p{Ll}\\p{M}]*)*$", name)) {
            return 3;
        }
        return index;
    }

    public static boolean isBirth(LocalDate birth) {
        Period period = Period.between(birth, LocalDate.now());
        return period.getYears() >= 18;
    }

    public static boolean isFromToDate(LocalDate fromDate, LocalDate toDate) {
        if (fromDate == null || toDate == null) {
            return true;
        }
        return fromDate.isBefore(toDate) || fromDate.isEqual(toDate);
    }

    public static boolean isComponentInPanel(JPanel panel, Component component) {
        for (Component comp : panel.getComponents()) {
            if (comp.equals(component)) {
                return true;
            }
        }
        return false;
    }

}
