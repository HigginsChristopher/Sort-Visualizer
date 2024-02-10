package chiggi24.SortingVisualizer;


import javafx.application.Platform;

public class MergeSort implements SortingStrategy {
    // Private fields to store SortingHubController object and an integer array
    private SortingHubController controller;
    private int[] list;

    // Constructor to instantiate SortingHubController and integer array
    public MergeSort(SortingHubController controller, int[] list) {
        this.controller = controller;
        this.list = list;

    }

    // Creating a method to sort utilizing mergeSort sort
    @Override
    public void sort(int[] numbers) {
        // Calling on inPlaceMergeSort which utilizes in-place merge sort (no extra memory)
        inPlaceMergeSort(numbers, 0, numbers.length - 1);
    }

    // Creating inPlaceMergeSort to sort integer arrays (sub arrays) in an in place implementation
    void inPlaceMergeSort(int[] numbers, int left, int right) {
        if (left < right) {
            // Finding middle index
            int middle = left + (right - left) / 2;
            // Splitting sub arrays based on middle position
            inPlaceMergeSort(numbers, left, middle);
            inPlaceMergeSort(numbers, middle + 1, right);
            // Calling merge to merge sorted sub arrays
            merge(numbers, left, middle, right);
        }
    }

    // Creating merge method to merge two sub arrays using in-place implementation
    void merge(int[] numbers, int s1, int m, int e) {
        // Create second start counter for the second half the new merged array
        int s2 = m + 1;
        // Return if sub arrays are already sorted
        if (numbers[m] <= numbers[s2]) {
            return;
        }
        // Ensure s1 (first half) and s2 (second half) are less
        // than the last position they can fill
        while (s1 <= m && s2 <= e) {
            // Check if the element at the left counter is in the correct location
            if (numbers[s1] <= numbers[s2]) {
                s1++;
            } else {
                int position = s2;
                int key = numbers[s2];
                // Move all elements between counters right one
                while (position != s1) {
                    numbers[position] = numbers[position - 1];
                    // Delay the thread execution then call updateGraph to display new array
                    try {
                        Thread.sleep(2);
                        Platform.runLater(() -> controller.updateGraph(numbers));
                    } catch (InterruptedException exception) {
                        Thread.currentThread().interrupt();
                    }
                    position--;
                }
                numbers[s1] = key;
                // Delay the thread execution then call updateGraph to display new array
                try {
                    Thread.sleep(2);
                    Platform.runLater(() -> controller.updateGraph(numbers));
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                }
                // Increment counters
                s1++;
                s2++;
                m++;
            }
        }
    }

    // Create run method to call sort function utilizing private list field obtained in constructor
    @Override
    public void run() {
        sort(list);
    }
}
