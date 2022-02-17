package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobrok;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;



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
    @FXML private TableColumn<Festmeny,String>  colFestmenyKiallitott;
    @FXML private TableColumn<Festmeny,Integer>  colfestmenyEv;



    public void initialize() {
        colSzoborTulaj.setCellValueFactory(new PropertyValueFactory<Szobrok, String>("person"));
        colSzoborMag.setCellValueFactory(new PropertyValueFactory<>("height"));
        colSzoborAr.setCellValueFactory(new PropertyValueFactory<>("price"));

        colFestmenyCim.setCellValueFactory(new PropertyValueFactory<>("title"));
        colfestmenyEv.setCellValueFactory(new PropertyValueFactory<>("year"));
        colFestmenyKiallitott.setCellValueFactory(new PropertyValueFactory<>("on_display"));

    }



    @FXML
    public void szoborTorol(ActionEvent actionEvent) {
    }

    @FXML
    public void editSzobor(ActionEvent actionEvent)  {

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
    }

    @FXML
    public void festmenyTorol(ActionEvent actionEvent) {
    }
}