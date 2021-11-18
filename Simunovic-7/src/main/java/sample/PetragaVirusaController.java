package sample;

import covidportal.main.GlavnaDatoteke;
import covidportal.model.Bolest;
import covidportal.model.Simptom;
import covidportal.model.Virus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetragaVirusaController {
    @FXML
    public TextField nazivVirusa;
    @FXML
    private TableView virusiTableView;
    List<Bolest> listaVirusa;
    @FXML
    private TableColumn<Bolest, Long> tableColumnIdVirusa;
    @FXML
    private TableColumn<Bolest, String> tableColumnNazivVirusa;
    @FXML
    private TableColumn<Bolest, Simptom> tableColumnSimptomiVirusa;


    @FXML
    public void initialize() throws IOException {
        tableColumnIdVirusa.setCellValueFactory(new PropertyValueFactory<Bolest, Long>("id"));
        tableColumnNazivVirusa.setCellValueFactory(new PropertyValueFactory<Bolest, String>("naziv"));
        tableColumnSimptomiVirusa.setCellValueFactory(new PropertyValueFactory<Bolest, Simptom>("simptomi"));
        listaVirusa = GlavnaDatoteke.dohvatiViruse();
        for(int i =0; i<listaVirusa.size();i++) {
            System.out.println("Lista automobila:" + listaVirusa.get(i).getNaziv().toString());
        }
        filtrirajViruse();
    }
    @FXML
    public void filtrirajViruse() {
        List<Bolest> filterVirusa = new ArrayList<>();
        if (nazivVirusa.getText().isEmpty() == false) {
            filterVirusa= listaVirusa.stream()
                    .filter(p -> p.getNaziv().contains(nazivVirusa.getText())).collect(Collectors.toList());
        }
        else {
            filterVirusa = listaVirusa;
        }
        ObservableList<Bolest> listaVirusa = FXCollections.observableArrayList(filterVirusa);
        virusiTableView.setItems(listaVirusa);

    }




}
