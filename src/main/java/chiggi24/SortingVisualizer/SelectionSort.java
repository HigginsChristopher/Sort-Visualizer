package chiggi24.SortingVisualizer;

import javafx.application.Platform;

public class SelectionSort implements SortingStrategy {
    // Private fields to store SortingHubController object and an integer array
    private SortingHubController controller;
    private int[] list;

    // Constructor to instantiate SortingHubController and integer array
    public SelectionSort(SortingHubController controller, int[] list) {
        this.controller = controller;
        this.list = list;
    }

    // Creating a method to sort utilizing selection sort
    @Override
    public void sort(int[] numbers) {
        // Loop through array
        for (int outerIndex = 0; outerIndex < numbers.length; outerIndex++) {
            // Find the index of the minimum element
            int indexOfNextSmallest = outerIndex;
            for (int innerIndex = outerIndex + 1; innerIndex < numbers.length; innerIndex++) {
                if (numbers[innerIndex] < numbers[indexOfNextSmallest]) {
                    indexOfNextSmallest = innerIndex;
                }
            }
            // Swap the smallest element with the outerIndex element
            int temp = numbers[outerIndex];
            numbers[outerIndex] = numbers[indexOfNextSmallest];
            numbers[indexOfNextSmallest] = temp;
            // Delay the thread execution then call updateGraph to display new array
            try {
                Thread.sleep(50);
                Platform.runLater(() -> controller.updateGraph(numbers));
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Create run method to call sort function utilizing private list field obtained in constructor
    @Override
    public void run() {
        sort(list);
    }
}
