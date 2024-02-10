package chiggi24.SortingVisualizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

public class SortingHubController implements Initializable {

    // Initializing integer array
    private int[] intArray;

    // Initializing thread
    private Thread myThread;

    // Initializing sorting strategy
    private SortingStrategy sortingMethod;

    // Initializing label for array size box
    @FXML
    private Label arraySizeBox;

    // Initializing slider for array size
    @FXML
    private Slider arraySize;

    // Initializing Combo box
    @FXML
    private ComboBox<String> algorithmName;

    // Initializing pane
    @FXML
    private Pane myPane;

    // Initialize method to load names and initial array conditions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Making ArrayList with names of sorting strategy
        ArrayList<String> algorithmList = new ArrayList<>(Arrays.asList("Merge Sort", "Selection Sort"));
        // Creating ObservableList out of ArrayList
        ObservableList<String> algorithmObsList = FXCollections.observableArrayList(algorithmList);
        // Setting all algorithm names using ObservableList
        algorithmName.getItems().setAll(algorithmObsList);
        // Setting default sorting algorithm to merge sort
        algorithmName.getSelectionModel().select(0);
        // Calling on initializeArray to initialize array
        initializeArray();
    }

    // Creating method to initialize array with non-duplicate keys ranging from zero to the size of the array
    @FXML
    void initializeArray() {
        // Getting the size from the slider for array size
        int size = (int) arraySize.getValue();
        // Setting label value to slider value
        arraySizeBox.setText(String.format("%d", size));
        // Instantiating integer array field with size from slider
        intArray = new int[size];
        // Creating an object of random to help randomize numbers
        Random rand = new Random();
        // First loop populates each index with the iteration number plus one (don't want zero height)
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }
        // Second loop shuffles the array
        for (int i = 0; i < intArray.length; i++) {
            int randomIndex = rand.nextInt(intArray.length);
            int temp = intArray[randomIndex];
            intArray[randomIndex] = intArray[i];
            intArray[i] = temp;
        }
        // Call to updateGraph to display initial graph to screen
        updateGraph(intArray);
    }


    // Method to process given array and represent it visually through rectangles (height is the key)
    public void updateGraph(int[] data) {
        // Start by clearing screen of any previously drawn rectangles
        myPane.getChildren().clear();
        // Using size calculate width, xPos and yConst according to dimensions of the pane
        int size = data.length;
        double width = (750.0 - 2 * size) / size;
        double xPos = 2;
        double yConst = 325.0 / size;
        // For each set in the data set create a rectangle with given dimensions and add to myPane
        for (int j : data) {
            double height = j * yConst;
            double yPost = 326;
            yPost -= height;
            Rectangle rec = new Rectangle(xPos, yPost, width, height - 1);
            xPos += width + 2;
            rec.setFill(Color.RED);
            myPane.getChildren().add(rec);
        }
    }

    // Reset event for when reset button is clicked
    @SuppressWarnings("deprecation")
    @FXML
    void resetEvent() {
        // If the thread is already running, stop it. Useful for if the user presses reset while it is sorting
        try {
            myThread.stop();
        } catch (Exception ignored) {
        }
        // Setting reset conditions (array size of 64 and merge sort)
        arraySize.setValue(64.0);
        arraySizeBox.setText("64");
        algorithmName.getSelectionModel().select(0);
        // Call on initializeArray() to make a new shuffle array and update screen
        initializeArray();
    }

    // Set sorting strategy method
    @FXML
    public void setSortingStrategy() {
        // Check the name of combo box to get the sorting algorithm name and instantiate sortingStrategy accordingly
        if (algorithmName.getValue().equals("Merge Sort")) {
            sortingMethod = new MergeSort(this, intArray);
        } else sortingMethod = new SelectionSort(this, intArray);
    }

    // Sort event for when sort button is pressed
    @FXML
    void sortEvent() {
        // Call setSortingStrategy to instantiate sortingStrategy as an object of one the sorting types
        setSortingStrategy();
        // Creating a thread using sortingStrategy (extends runnable)
        myThread = new Thread(sortingMethod);
        // Call the start method on the thread to go to the run method inside sorting method class
        myThread.start();
    }

}