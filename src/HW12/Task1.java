package HW12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }
        minValue(arr);
        System.out.println("\n-----------------------");
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);

        System.out.println(oddOrEven(list));
    }

    public static int minValue(int[] values) {
        IntStream streamValues = Arrays.stream(values);
        streamValues.distinct().sorted().forEach(System.out::print);
        return 0;
    }

//    реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная
//    - удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional
//    - решение в один стрим.

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum =  integers.stream().reduce((acc, element) ->  acc += element).get();
        return integers.stream().filter( x -> (x % 2) == (sum % 2)).collect(Collectors.toList());
    }
}
