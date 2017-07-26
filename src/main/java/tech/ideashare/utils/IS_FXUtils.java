package tech.ideashare.utils;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by lixiang on 11/07/2017.
 */
public class IS_FXUtils {


    /**
     * 绑定List数据和tableView
     * @param listData list数据
     * @param tableView tableView
     * @param pairMap  list数据和tableView的对应关系
     */
    public static <T> void bindListToTableView(ObservableList<T> listData , TableView<T> tableView , Map pairMap){


        for (TableColumn<T,?> tableColumn : tableView.getColumns()) {
            String columnId = tableColumn.getId();
            String listProperty = (String) pairMap.get(columnId);
            tableColumn.setCellValueFactory(new PropertyValueFactory(listProperty));
        }

        tableView.setItems(listData);
    }

    /**
     * 绑定List<String>数据到ListView中
     * @param listData list数据
     * @param tableColumn tableColumn列
     */
    public static <T> void bindListToListView(List<String> listData , TableColumn<T,T> tableColumn,String propertyName ){

        ObservableList<String> stringValues = FXCollections.observableArrayList();
        stringValues.addAll(listData);
        tableColumn.setCellValueFactory(new PropertyValueFactory(propertyName));


    }


    /**
     * 打开文件选择窗口
     * @param stage
     * @return
     */
    public static File openFileChooser(Stage stage){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("all files (*.*)", "*.*");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }


    public static void showTimedDialog(long time, String message) {
        Stage popup = new Stage();
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.APPLICATION_MODAL);
        Button closeBtn = new Button("知道了");
        closeBtn.setOnAction(e -> {
            popup.close();
        });
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setSpacing(20);
        root.getChildren().addAll(new Label(message), closeBtn);
        Scene scene = new Scene(root);
        popup.setScene(scene);
        popup.setTitle("提示信息");
        popup.show();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(time);
                if (popup.isShowing()) {
                    Platform.runLater(() -> popup.close());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }





}
