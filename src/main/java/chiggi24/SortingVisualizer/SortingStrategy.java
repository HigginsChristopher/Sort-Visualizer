package chiggi24.SortingVisualizer;

// Creating an interface that extends runnable to allow use in threading
public interface SortingStrategy extends Runnable {
    void sort(int[] numbers);
}
