package sample;

import covidportal.main.GlavnaDatoteke;
import covidportal.model.Bolest;
import covidportal.model.Osoba;
import covidportal.model.Zupanija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PetragaOsobaController {
    @FXML
    public TextField imeOsobe;
    @FXML
    public TextField prezimeOsobe;
    @FXML
    public TextField godineOsobe;
    @FXML
    public ComboBox comboBoxBolestOsobe = new ComboBox();
    @FXML
    public ComboBox comboBoxZupanijaOsobe = new ComboBox();
    @FXML
    private TableView osobeTableView;
    @FXML
    List<Osoba> listaOsoba;
    @FXML
    private TableColumn<Osoba, Long> tableColumnIdOsobe;
    @FXML
    private TableColumn<Osoba, String> tableColumnImeOsobe;
    @FXML
    private TableColumn<Osoba, String> tableColumnPrezimeOsobe;
    @FXML
    private TableColumn<Osoba, Integer> tableColumnGodineOsobe;
    @FXML
    private TableColumn<Osoba, String> tableColumnZupanijaOsobe;
    @FXML
    private TableColumn<Osoba, String> tableColumnBolestOsobe;
    @FXML
    private TableColumn<Osoba, String> tableColumnKontaktOsobeOsobe;


    @FXML
    public void initialize() throws IOException {
        tableColumnIdOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, Long>("id"));
        tableColumnImeOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, String>("ime"));
        tableColumnPrezimeOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, String>("prezime"));
        tableColumnGodineOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, Integer>("starost"));
        tableColumnZupanijaOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, String>("zupanija"));
        tableColumnBolestOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, String>("zarazenBolescu"));
        tableColumnKontaktOsobeOsobe.setCellValueFactory(new PropertyValueFactory<Osoba, String>("kontaktiraneOsobe"));
        listaOsoba = FXCollections.observableArrayList(GlavnaDatoteke.dohvatiOsobe());
        for(int i =0; i<listaOsoba.size();i++) {
            System.out.println("Lista osoba:" + listaOsoba.get(i).getIme());
        }
        List<String> listaBolesti = new ArrayList<>();
        for(Osoba o : listaOsoba){
            listaBolesti.add(o.getZarazenBolescu().getNaziv());
        }
        Set<String> namesAlreadySeen = new HashSet<>();
        listaBolesti.removeIf(p -> !namesAlreadySeen.add(p));
        comboBoxBolestOsobe.getItems().add(" ");
        comboBoxBolestOsobe.getItems().addAll(listaBolesti);
        comboBoxBolestOsobe.setEditable(true);

        List<Zupanija> listaZupanija = GlavnaDatoteke.dohvatiZupanije();
        List<String> naziviZupanija= new ArrayList<>();
        for(Zupanija z : listaZupanija){
            naziviZupanija.add(z.getNaziv());
        }
        Set<String> dupliNazivi = new HashSet<>();
        naziviZupanija.removeIf(z -> !dupliNazivi.add(z));
        comboBoxZupanijaOsobe.getItems().add(" ");
        comboBoxZupanijaOsobe.getItems().addAll(naziviZupanija);
        comboBoxZupanijaOsobe.setEditable(true);
        comboBoxZupanijaOsobe.setValue(" ");
        comboBoxBolestOsobe.setValue(" ");
        filtrirajOsobe();
    }
    @FXML
    public void filtrirajOsobe() {
        List<Osoba> filterOsobe = new ArrayList<>();

        if (imeOsobe.getText().isEmpty() == false) {
            filterOsobe= listaOsoba.stream()
                    .filter(p -> p.getIme().startsWith(imeOsobe.getText())).collect(Collectors.toList());
        }
        else if (prezimeOsobe.getText().isEmpty() == false) {
            filterOsobe= listaOsoba.stream()
                    .filter(p -> p.getPrezime().startsWith(prezimeOsobe.getText())).collect(Collectors.toList());
        }
        else if (godineOsobe.getText().isEmpty() == false) {
            filterOsobe= listaOsoba.stream()
                    .filter(p -> p.getStarost().toString().equals(godineOsobe.getText())).collect(Collectors.toList());
        }
        else if (comboBoxZupanijaOsobe.getValue().toString().equals(" ") ==false) {
            filterOsobe= listaOsoba.stream()
                    .filter(z -> z.getZupanija().getNaziv().equals(comboBoxZupanijaOsobe.getValue().toString())).collect(Collectors.toList());
        }
        else if (comboBoxBolestOsobe.getValue().toString().equals(" ")==false) {
            filterOsobe= listaOsoba.stream()
                    .filter(b -> b.getZarazenBolescu().getNaziv().equals(comboBoxBolestOsobe.getValue().toString())).collect(Collectors.toList());
        }
        else {
            filterOsobe = listaOsoba;
        }
        ObservableList<Osoba> listaOsoba = FXCollections.observableArrayList(filterOsobe);
        osobeTableView.setItems(listaOsoba);

    }




}
