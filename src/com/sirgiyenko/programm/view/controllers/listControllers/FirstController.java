package com.sirgiyenko.programm.view.controllers.listControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FirstController {

    @FXML
    private Button runListButton;

    @FXML
    private Button runDbButton;

    @FXML
    public void setRunListButton(){
        Stage stage = (Stage) runListButton.getScene().getWindow();
        stage.close();

        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/sirgiyenko/programm/view/scenes/listScenes/mainSceneList.fxml"));
        Parent root = fxmlLoader.load();
        stage = new Stage();
        stage.setTitle("Contact Book");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
