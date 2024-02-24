package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filename = "10m.txt";

        try {
            List<Integer> numbers = readNumbersFromFile(filename);
            System.out.println("numbers = " + numbers);//--------------------

            int maxValue = Collections.max(numbers);
            int minValue = Collections.min(numbers);

            List<Integer> increasingSequence = findMaxIncreasingSequence(numbers);
            List<Integer> decreasingSequence = findMaxDecreasingSequence(numbers);

            double median = calculateMedian(numbers);
            double average = calculateAverage(numbers);


            System.out.println("Max Value: " + maxValue);
            System.out.println("Min Value: " + minValue);
            System.out.println("Median: " + median);
            System.out.println("Average: " + average);
            System.out.println("Max Increasing Sequence: " + increasingSequence);
            System.out.println("Max Decreasing Sequence: " + decreasingSequence);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> readNumbersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null) {
            numbers.add(Integer.parseInt(line));
        }

        reader.close();
        return numbers;
    }

    private static double calculateMedian(List<Integer> sortNumbers) {
        Collections.sort(sortNumbers);
        int size = sortNumbers.size();

        if (size % 2 == 0) {
            int mid1 = sortNumbers.get(size / 2 - 1);
            int mid2 = sortNumbers.get(size / 2);
            return (mid1 + mid2) / 2.0;
        } else {
            return sortNumbers.get(size / 2);
        }
    }

    private static double calculateAverage(List<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }

    private static List<Integer> findMaxIncreasingSequence(List<Integer> numbers) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> maxSequence = new ArrayList<>();

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                currentSequence.add(numbers.get(i));
            } else {
                currentSequence.add(numbers.get(i));

                if (currentSequence.size() > maxSequence.size()) {
                    maxSequence = new ArrayList<>(currentSequence);
                }

                currentSequence.clear();
            }
        }

        // Додаємо останній елемент, оскільки цикл закінчується на numbers.size() - 1
        currentSequence.add(numbers.get(numbers.size() - 1));

        // Перевіряємо, чи поточна зростаюча послідовність є найдовшою
        if (currentSequence.size() > maxSequence.size()) {
            maxSequence = new ArrayList<>(currentSequence);
        }

        return maxSequence;
    }

    private static List<Integer> findMaxDecreasingSequence(List<Integer> numbers) {
        List<Integer> currentSequence = new ArrayList<>();
        List<Integer> maxSequence = new ArrayList<>();

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                currentSequence.add(numbers.get(i));
            } else {
                currentSequence.add(numbers.get(i));

                if (currentSequence.size() > maxSequence.size()) {
                    maxSequence = new ArrayList<>(currentSequence);
                }

                // Оновлено: очистка поточної послідовності тільки якщо вона не є зменшувальною
                if (!(currentSequence.size() > 1 && currentSequence.get(currentSequence.size() - 1) > currentSequence.get(currentSequence.size() - 2))) {
                    currentSequence.clear();
                }
            }
        }

        // Додаємо останній елемент, оскільки цикл закінчується на numbers.size() - 1
        currentSequence.add(numbers.get(numbers.size() - 1));

        // Перевіряємо, чи поточна зменшувальна послідовність є найдовшою
        if (currentSequence.size() > maxSequence.size()) {
            maxSequence = new ArrayList<>(currentSequence);
        }

        return maxSequence;
    }
}