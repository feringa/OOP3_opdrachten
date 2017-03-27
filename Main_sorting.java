import java.util.*;

import com.sun.deploy.util.ArrayUtil;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;

public class Main extends Application
{
    public int arr[] = new int[100];
    public int quickSortLength = 0;
    public int low, high;
    public boolean firstRecursion = true;

    public Button bubbleSortNextButton = new Button("bubble");
    public Button quickSortNextButton = new Button("quick");

    public int stepCount = 0;
    public Stage primaryStage;
    final XYChart.Series<String,Number> data = new XYChart.Series<String,Number>();

    private void init(Stage primaryStage) {

        CategoryAxis xAxis    = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series Data = new XYChart.Series();
        for(int j = 0; j < arr.length; j++) {
            Data.getData().add(new XYChart.Data(Integer.toString(arr[j]), arr[j]));
        }
        barChart.getData().add(Data);

        buttonhandler();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(bubbleSortNextButton, quickSortNextButton, barChart);
        Scene scene = new Scene(vbox, 700, 1000);

        setStage(scene);
    }

    private void setStage(Scene scene)
    {
        primaryStage.setScene(scene);
        primaryStage.setHeight(900);
        primaryStage.setWidth(1200);

        primaryStage.show();
    }

    public void buttonhandler()
    {
        bubbleSortNextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                BubbleSort();
            }
        });

        quickSortNextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                QuickSort();
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override public void start(Stage primaryStage)
    {
        arr = GenerateArray(arr, 10);
        this.primaryStage = primaryStage;
        init(primaryStage);
        primaryStage.show();
    }

    public int[] GenerateArray(int[] arr, int length)
    {
        int temp[] = new int[10];
        /*Random rand = new Random();
        for(int j = 0; j < length; j++) {
            int randomNumber = rand.nextInt(10) + 1;
                temp[j] = randomNumber;
        }
        return temp;*/

        for (int i = 0; i < length; i++) {
            temp[i] = (int)(Math.random()*10);//note, this generates numbers from [0,9]

            for (int j = 0; j < i; j++) {
                if (temp[i] == temp[j]) {
                    i--; //if a[i] is a duplicate of a[j], then run the outer loop on i again
                    break;
                }
            }
        }
        return temp;
    }

    public void BubbleSort()
    {
        int[] tempArr = Arrays.copyOf(arr, arr.length);
        int tempLow = 0;
        int tempHigh = 0;
        System.out.println("stepcount : " + stepCount);
        if(stepCount < tempArr.length - 1) {
            if (tempArr[stepCount] > tempArr[stepCount + 1]) {
                tempLow = tempArr[stepCount];
                tempHigh = tempArr[stepCount + 1];
                tempArr[stepCount] = tempHigh;
                tempArr[stepCount + 1] = tempLow;
                arr = Arrays.copyOf(tempArr, tempArr.length);
                System.out.println("Switched : " + tempLow + " With : " + tempHigh);
                stepCount = 0;
            }
            else
            {
                stepCount++;
            }
        }
        if(stepCount >= arr.length - 1)
            stepCount = 0;
        init(primaryStage);
    }

    private void QuickSort()
    {
        quickSortLength = arr.length;
        QuickSortRecursive(0, arr.length - 1);
    }

    private void QuickSortRecursive(int low, int high) {
        System.out.println("the array is : " + Arrays.toString(arr));
        int i = low, j = high;
        int pivot = arr[low + (high-low)/2];

        System.out.println("The pivot is : " + pivot);
        while (i <= j) {
            while (arr[i] < pivot){
                i++;
            }
            while (arr[j] > pivot){
                j--;
            }
            if (i <= j) {
                exchange(i, j);
                System.out.println("Found 2 value's to switch. Switching : " + arr[i] + " with : " + arr[j]);
                i++;
                j--;
            }
        }

        if (low < j) {
            QuickSortRecursive(low, j);
        }
        if (i < high) {
            QuickSortRecursive(i, high);
        }
        init(primaryStage);
    }

    public void DivideArray(int i, int j, int pivot)
    {


    }

    private void exchange(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
