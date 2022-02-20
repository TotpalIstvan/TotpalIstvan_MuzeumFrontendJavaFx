package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobrok;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;


public class MuzeumController extends Controller{

    @FXML private Button festmenyTorolBtn;
    @FXML private Button festmenyEditBtn;
    @FXML private Button szoborTorolBtn;
    @FXML private TableView<Szobrok> szoborTable;
    @FXML private TableColumn<Szobrok,String> colSzoborTulaj;
    @FXML private TableColumn<Szobrok,Integer> colSzoborMag;
    @FXML private TableColumn<Szobrok,Integer> colSzoborAr;
    @FXML private Button festmenyUjBtn;
    @FXML private Button szoborEditBtn;
    @FXML private Button szoborUjBtn;
    @FXML private TableView<Festmeny> festmenyTable;
    @FXML private TableColumn<Festmeny,String>  colFestmenyCim;
    @FXML private TableColumn<Festmeny, String>  colFestmenyKiallitott;
    @FXML private TableColumn<Festmeny,Integer>  colfestmenyEv;



    public void initialize() {
        colSzoborTulaj.setCellValueFactory(new PropertyValueFactory<Szobrok, String>("person"));
        colSzoborMag.setCellValueFactory(new PropertyValueFactory<>("height"));
        colSzoborAr.setCellValueFactory(new PropertyValueFactory<>("price"));
        szoborFeltolt();

        colFestmenyCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        colfestmenyEv.setCellValueFactory(new PropertyValueFactory<>("year"));
       colFestmenyKiallitott.setCellValueFactory(new PropertyValueFactory<>("on_display"));
       festmenyFeltolt();

    }



    @FXML
    public void szoborTorol(ActionEvent actionEvent) {
        int selectedIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A törléshez előbb válasszon ki egy szobrot a táblázatból!");
            return;
        }
        Szobrok torlendoSzobor = szoborTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos, hogy törölni szeretné az alábbi számú szobrot: " + torlendoSzobor.getId() + "?")) {
            return;
        } else {
            try {
                boolean sikeres = MuzeumApi.szoborTorlese(torlendoSzobor.getId());
                alert(sikeres ? "Sikeres törlés" : "Sikertelen törlés");
                szoborFeltolt();
            } catch (IOException e) {
                hibaKiir(e);
            }
        }
    }

    private void szoborFeltolt() {
        try {
            List<Szobrok> szoborLista = MuzeumApi.getSzobrok();
            szoborTable.getItems().clear();
            for (Szobrok szobor: szoborLista) {
                szoborTable.getItems().add(szobor);
            }
        }
        catch (IOException e){
            hibaKiir(e);
        }
    }


    @FXML
    public void editSzobor(ActionEvent actionEvent)  {
        int selectedIndex = szoborTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A módosításhoz előbb válasszon ki egy szobrot a táblázatból!");
            return;
        }
        Szobrok modositandoSzobor = szoborTable.getSelectionModel().getSelectedItem();
        try {
            SzobormodositController szoborModositas = (SzobormodositController) ujAblak("szobormodosit-view.fxml", "Szobor módosítása", 320, 400);
            szoborModositas.setModositando(modositandoSzobor);
            szoborModositas.getStage().setResizable(false);
            szoborModositas.getStage().setOnHiding(event -> szoborTable.refresh());
            szoborModositas.getStage().setOnCloseRequest(event -> szoborFeltolt());
            szoborModositas.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void ujSzobor(ActionEvent actionEvent) {
        try {
            Controller felvetel = ujAblak("szoborhozzaad-view.fxml", "Szobor hozzáadása", 320, 400);
            felvetel.getStage().setResizable(false);
            felvetel.getStage().show();
        }catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void ujFestmeny(ActionEvent actionEvent) {
        try {
            Controller felvetel = ujAblak("festmenyhozzaad-view.fxml", "Szobor hozzáadása", 320, 400);
            felvetel.getStage().setResizable(false);
            felvetel.getStage().show();
        }catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void editFestmeny(ActionEvent actionEvent) {
        int selectedIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A módosításhoz előbb válasszon ki egy festményt a táblázatból!");
            return;
        }
        Festmeny modositandoFestmeny = festmenyTable.getSelectionModel().getSelectedItem();
        try {
            FestmenymodositController festmenyModositas = (FestmenymodositController) ujAblak("festmeny-edit-view.fxml", "Festmény módosítása", 320, 400);
            festmenyModositas.setModositando(modositandoFestmeny);
            festmenyModositas.getStage().setResizable(false);
            festmenyModositas.getStage().setOnHiding(event -> festmenyTable.refresh());
            festmenyModositas.getStage().setOnCloseRequest(event -> festmenyFeltolt());
            festmenyModositas.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void festmenyTorol(ActionEvent actionEvent) {
        int selectedIndex = festmenyTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A törléshez előbb válasszon ki egy festményt a táblázatból!");
            return;
        }
        Festmeny torlendoFestmeny = festmenyTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos, hogy törölni szeretné az alábbi című festményt: " + torlendoFestmeny.getcim() + "?")) {
            return;
        } else {
            try {
                boolean sikeres = MuzeumApi.festmenyTorlese(torlendoFestmeny.getId());
                alert(sikeres ? "Sikeres törlés" : "Sikertelen törlés");
                festmenyFeltolt();
            } catch (IOException e) {
                hibaKiir(e);
            }
    }


}

    private void festmenyFeltolt() {
        try {
            List<Festmeny> festmenyLista = MuzeumApi.getFestmeny();
            festmenyTable.getItems().clear();
            for (Festmeny festmeny: festmenyLista) {
                festmenyTable.getItems().add(festmeny);
            }
        }
        catch (IOException e){
            hibaKiir(e);
        }
    }
    }

