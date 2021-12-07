package HW3;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        System.out.println(getAbsentNumber(arr));
    }

    private static int getAbsentNumber(int[] arr) {
        if (arr.length == 0) return 1;

        int start = 0;
        int end = arr.length - 1;
        int base = -1;

        while (start != end) {
            base = (end + start) / 2;

            if (base != 0 && arr[base] - arr[base - 1] == 2) return arr[base] - 1;
            if (base != arr.length - 1 && arr[base + 1] - arr[base] == 2) return arr[base] + 1;

            if (base + 2 == arr[base]) {
                end = base - 1;
            } else if (base + 1 == arr[base]) {
                start = base + 1;
            }
        }

        return -1;
    }
}
