package com.sirgiyenko.programm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*Start of graphic application.*/

public class Programm extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            /*Variant 1 - graphic realization for List.*/
            Parent root = FXMLLoader.load(getClass().getResource("view/scenes/listScenes/firstScene.fxml"));

//        /*Variant 2 - graphic realization for DB.*/
//        Parent root = FXMLLoader.load(getClass().getResource("view/scenes/mainScene.fxml"));

            primaryStage.setTitle("Contact Book");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
