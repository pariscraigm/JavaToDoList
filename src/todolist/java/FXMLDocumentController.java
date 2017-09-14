/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist.java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;

/**
 *
 * @author craig
 */
public class FXMLDocumentController implements Initializable {  
    
    @FXML
    private VBox itemVBox;
    
    @FXML
    private ScrollPane itemScrollPane;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        itemVBox.getChildren().add(addItem("name", "description"));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        itemVBox.setMinWidth(200);
        itemScrollPane.setFitToWidth(true);  
    }    
    
    public BorderPane addItem(String name, String description) {
        Label nameLabel = new Label(name);
        TextField nameTextField = new TextField();
        Text descriptionText = new Text(description);
        descriptionText.setWrappingWidth(200);
        TextField descriptionTextField = new TextField();
        CheckBox checkBox = new CheckBox();
        checkBox.setPadding(new Insets(10));
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(5,10,0,10));
        
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(nameLabel, descriptionText);
        borderPane.setCenter(vBox);
        borderPane.setRight(checkBox);
        
        double vBoxWidth = vBox.getWidth();
        nameLabel.setMaxWidth(Double.MAX_VALUE);
        descriptionText.setWrappingWidth(vBoxWidth);
        
        nameLabel.setOnMouseClicked(e -> {
            
            if (descriptionTextField.getParent() == vBox) {
                vBox.getChildren().remove(descriptionTextField);
                descriptionTextField.clear();
                vBox.getChildren().add(1,descriptionText);
            }
            String current = nameLabel.getText();
            vBox.getChildren().remove(nameLabel);
            nameTextField.setText(current);
            vBox.getChildren().add(0,nameTextField);
            nameTextField.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER)  {
                String text = nameTextField.getText();
                nameLabel.setText(text);
                nameTextField.clear();
                vBox.getChildren().remove(nameTextField);
                vBox.getChildren().add(0,nameLabel);
                }
            });
        });
        
        descriptionText.setOnMouseClicked(e -> {
            if (nameTextField.getParent() == vBox) {
                vBox.getChildren().remove(nameTextField);
                nameTextField.clear();
                vBox.getChildren().add(0,nameLabel);
            }
            
            String current = descriptionText.getText();
            descriptionTextField.setText(current);
            vBox.getChildren().remove(descriptionText);
            vBox.getChildren().add(1,descriptionTextField);
            descriptionTextField.setOnKeyPressed(keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    String text = descriptionTextField.getText();
                    descriptionText.setText(text);
                    descriptionTextField.clear();
                    vBox.getChildren().remove(descriptionTextField);
                    vBox.getChildren().add(1,descriptionText);
                    
                }
            });
        });
        
        checkBox.setOnAction(e -> {
            itemVBox.getChildren().remove(borderPane);
        });
        
        return borderPane;
    }
    
}
