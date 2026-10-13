package com.example.proyectofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<String> tareas = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestor de tareas endientes");

        Label titulo = new Label("Lista de tareas pendientes");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        TextField campoTexto = new TextField();
        campoTexto.setPromptText("Escribe una nueva tarea");
        HBox.setHgrow(campoTexto, Priority.ALWAYS);

        Button botonAgregar = new Button("Añadir");

        ListView<String> listaTareas = new ListView<>(tareas);
        VBox.setVgrow(listaTareas, Priority.ALWAYS);

        Runnable accionGuardar = () -> {
            String texto = campoTexto.getText().trim();

            if (texto.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Aviso");
                alerta.setHeaderText(null);
                alerta.setContentText("No puedes añadir una vacia.");
                alerta.showAndWait();
            } else {
                tareas.add(texto);
                campoTexto.clear();
            }
            campoTexto.requestFocus();
        };

        botonAgregar.setOnAction(e -> accionGuardar.run());
        campoTexto.setOnAction(e -> accionGuardar.run());

        HBox panelEntrada = new HBox(10, campoTexto, botonAgregar);
        panelEntrada.setAlignment(Pos.CENTER);

        VBox contenedorPrincipal = new VBox(15, titulo, panelEntrada, listaTareas);
        contenedorPrincipal.setPadding(new Insets(15));

        Scene escena = new Scene(contenedorPrincipal, 400, 500);
        primaryStage.setScene(escena);
        primaryStage.show();

        campoTexto.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}