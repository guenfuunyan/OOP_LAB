import java.util.Arrays;
import java.util.Scanner;

public class NumericArrayOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước mảng
        System.out.print("Enter the number of elements in the array: ");
        int size = scanner.nextInt();

        int[] myArray = new int[size];

        // Nhập phần tử mảng từ người dùng
        System.out.println("Enter " + size + " numbers:");
        for (int i = 0; i < size; i++) {
            myArray[i] = scanner.nextInt();
        }

        // Sắp xếp mảng
        Arrays.sort(myArray);

        // Tính tổng và giá trị trung bình
        int sum = 0;
        for (int num : myArray) {
            sum += num;
        }
        double average = (double) sum / size;

        // Hiển thị kết quả
        System.out.println("Sorted array: " + Arrays.toString(myArray));
        System.out.println("Sum of array elements: " + sum);
        System.out.println("Average value of array elements: " + average);

        scanner.close();
    }
}
