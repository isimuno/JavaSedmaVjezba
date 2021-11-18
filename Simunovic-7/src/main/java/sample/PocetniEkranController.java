package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class PocetniEkranController {
    @FXML
    public void prikaziEkranZaPretraguZupanija() throws IOException {
        Parent pretragaZupanijaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaZupanija.fxml"));
        Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaZupanijaScene);
    }

    @FXML
    public void prikaziEkranZaPretraguSimptoma() throws IOException {
        Parent pretragaSimptomaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaSimptoma.fxml"));
        Scene pretragaSimptomaScene = new Scene(pretragaSimptomaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaSimptomaScene);
    }
    @FXML
    public void prikaziEkranZaPretraguOsoba() throws IOException {
        Parent pretragaOsobaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaOsoba.fxml"));
        Scene pretragaOsobaScene = new Scene(pretragaOsobaFrame, 800, 650);
        Main.getMainStage().setScene(pretragaOsobaScene);
    }
    @FXML
    public void prikaziEkranZaPretraguBolesti() throws IOException {
        Parent pretragaBolestiFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaBolesti.fxml"));
        Scene pretragaBolestiScene = new Scene(pretragaBolestiFrame, 600, 400);
        Main.getMainStage().setScene(pretragaBolestiScene);
    }

    @FXML
    public void prikaziEkranZaPretraguVirusa() throws IOException {
        Parent pretragaVirusaFrame =
                FXMLLoader.load(getClass().getClassLoader().getResource(
                        "pretragaVirusa.fxml"));
        Scene pretragaVirusaScene = new Scene(pretragaVirusaFrame, 600, 400);
        Main.getMainStage().setScene(pretragaVirusaScene);
    }

}
