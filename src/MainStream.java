import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter array size: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Enter number: ");
            arr[i] = scanner.nextInt();
        }
        System.out.println(minValue(arr));

        System.out.println("\n-----------------------");

        Integer[] arr2 = {1, 2, 4, 5};
      //  Integer[] arr2 = {8,9};
        List<Integer> list = Arrays.asList(arr2);
        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        return IntStream.of(values).distinct().sorted().reduce(0, (x,y) -> 10 * x + y);
    }

//    реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная
//    - удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional
//    - решение в один стрим.

    public static List<Integer> oddOrEven(List<Integer> integers) {
       // int sum =  integers.stream().reduce((acc, element) ->  acc += element).get();
        int sum =  integers.stream().reduce(0, Integer::sum);
        return integers.stream().filter( x -> (x % 2) == (sum % 2)).collect(Collectors.toList());
    }
}