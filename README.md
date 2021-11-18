# JavaSedmaVjezba


7.Sedma laboratorijska vježba
7.1. TEMA VJEŽBE
Svrha laboratorijske vježbe je implementacija grafičkog sučelja
temeljena na JavaFX tehnologiji te korištenje postojećih podataka u
datotekama te domenskih klasa kreiranih u prošlim laboratorijskim
vježbama.
7.2. ZADATAK ZA PRIPREMU
JavaFX aplikaciju je potrebno implementirati na način opisan u
sljedećim koracima:
1. Korištenjem IntelliJ-a kreirati novi „JavaFX Application“ projekt s
nazivom koji nosi Vaše prezime i redni broj „7“, npr. „Horvat-7“. Kod
kreiranja „JavaFX“ projekta koristiti upute sa sljedeće poveznice:
https://www.jetbrains.com/help/idea/javafx.html. Nakon toga
preuzeti JavaFX-SDK (verzije 15.0.1) kako je prikazano na istoj
poveznici te ga konfigurirati na opisani način.
2. Dodati „Maven“ strukturu projektu kao u trećoj laboratorijskoj vježbi
te „pom.xml“ datoteku proširiti s novim „dependency“-jem:
<dependency>
 <groupId>org.openjfx</groupId>
 <artifactId>javafx-controls</artifactId>
 <version>15.0.1</version>
</dependency>
3. Datoteku „sample.fxml“ prebaciti u mapu „resources“ kako je
prikazano na slici:
4. Pakete koji se koriste u Java klasama i „fxml“ datoteci postaviti na
„main.java.sample“ te metodu „start“ unutar klase „Main“ ažurirati
na sljedeći način:
public class Main extends Application {
 private static Stage mainStage;
 @Override
 public void start(Stage primaryStage) throws Exception{
 Parent root =
FXMLLoader.load(getClass().getClassLoader().getResource(
"sample.fxml"));
 primaryStage.setTitle("Hello World");
 primaryStage.setScene(new Scene(root, 300, 275));
 primaryStage.show();
 }
 public static Stage getMainStage() {
 return mainStage;
 }
 public static void main(String[] args) {
 launch(args);
 }
}
5. Unutar „Run Configuration“ konfiguraciji postavili „VM options“ na
„--module-path "C:\DEV\JavaFX SDK\javafx-sdk-15.0.1\lib" --addmodules javafx.controls,javafx.fxml --add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED“, pri čemu je
crvenim fontom označena putanja do JavaFX SDK-a instaliranog u
prvom koraku vježbe.
6. Sve klase iz rješenja šeste laboratorijske vježbe prebaciti u istoimene
pakete u ovoj laboratorijskoj vježbi.
7. Datoteku „sample.fxml“ preimenovati u „pocetniEkran.fxml“ (te
ažurirati klasu „Main“ s tim novim imenom), a klasu „Controller“
preimenovati u „PocetniEkranController“ (te ažurirati datoteku
„pocetniEkran.fxml“).
8. Sa stranica https://gluonhq.com/products/scene-builder/ preuzeti
posljednju verziju Scene Builder aplikacije i instalirati je na računalo.
9. Korištenjem opcije „Open in Scene Builder“ otvoriti datoteku
„pocetniEkran.fxml“ i dizajnirati izbornik za otvaranje ekrana pomoću
kojeg se pretražuju podaci o županijama, simptomima, bolestima,
virusima i osobama kao što je prikazani na sljedećoj slici. Svaki
izbornik prema slici mora sadržavati opciju „Pretraga“.
10. U klasa „PocetniEkranController“ dodati logiku koja će
omogućavati otvaranje novog ekrana za pretragu županija na sljedeći
način:
public class PocetniEkranController implements Initializable {
 @FXML
 public void prikaziEkranZaPretraguZupanija() throws IOException {
 Parent pretragaZupanijaFrame =
 FXMLLoader.load(getClass().getClassLoader().getResource(
 "pretragaZupanija.fxml"));
 Scene pretragaZupanijaScene = new Scene(pretragaZupanijaFrame, 600, 400);
 Main.getMainStage().setScene(pretragaZupanijaScene);
 }
 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {
 }
}
11. Kreirati novu FXML datoteku pod nazivom „pretragaZupanija“ i
korištenjem Scene Builder aplikacije dizajnirati grafičko sučelje kao
što je prikazano na slici: 
12. Izbornik „Pretraga“ u glavnom izborniku „Županije“ potrebno je
konfigurirati na način da otvara ekran na prethodnoj slici.
13. Kreirati novu klasu „PetragaZupanijaController“ koji će biti
povezan s ekranom prikazanim na prethodnoj slici te implementirati
metode koje će omogućavati pretragu podataka o županijama prema
zadanoj ključnoj riječi.
14. Kreirati sve ostale ekrane i „Controller“ klase koje su potrebne za
pretraživanje podataka o simptomima, bolestima, virusima i
osobama te ih povezati s izbornikom i opcijom „Pretraga“ koji se
nalazi na glavnom ekranu.
Primjer izvođenja programa za pretragu podataka o županijama je
prikazan na sljedećem videu:
https://www.youtube.com/watch?v=a0j652gKTY0.
NAPOMENE:
1. Osim implementacija vježbe prema uputama, dozvoljeno je uvoditi i
promjene ako su opravdane i ne narušavaju koncepte objektnoorijentiranog programiranja.
