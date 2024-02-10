module chiggi24.SortingVisualizer {
    requires javafx.controls;
    requires javafx.fxml;


    opens chiggi24.SortingVisualizer to javafx.fxml;
    exports chiggi24.SortingVisualizer;
}