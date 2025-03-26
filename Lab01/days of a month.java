import java.util.Scanner;

public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập tháng từ người dùng
        System.out.print("Enter the month (name, abbreviation, or number 1-12): ");
        String monthInput = scanner.next();

        // Nhập năm từ người dùng
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();

        // Xác định số tháng từ input
        int month = getMonthNumber(monthInput);
        if (month == -1 || year < 0) {
            System.out.println("Invalid month or year. Please enter again.");
        } else {
            int days = getDaysInMonth(month, year);
            System.out.println("Number of days: " + days);
        }

        scanner.close();
    }

    // Hàm chuyển đổi input của người dùng thành số tháng (1-12)
    public static int getMonthNumber(String month) {
        month = month.toLowerCase();
        switch (month) {
            case "january": case "jan": case "1": return 1;
            case "february": case "feb": case "2": return 2;
            case "march": case "mar": case "3": return 3;
            case "april": case "apr": case "4": return 4;
            case "may": case "5": return 5;
            case "june": case "jun": case "6": return 6;
            case "july": case "jul": case "7": return 7;
            case "august": case "aug": case "8": return 8;
            case "september": case "sep": case "9": return 9;
            case "october": case "oct": case "10": return 10;
            case "november": case "nov": case "11": return 11;
            case "december": case "dec": case "12": return 12;
            default: return -1; // Trả về -1 nếu không hợp lệ
        }
    }

    // Hàm xác định số ngày trong tháng, có xét năm nhuận
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return (isLeapYear(year) ? 29 : 28);
            default:
                return -1;
        }
    }

    // Hàm kiểm tra năm nhuận
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
