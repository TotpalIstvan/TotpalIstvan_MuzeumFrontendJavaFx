package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobrok;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import javafx.event.ActionEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

public class SzoborhozzaadController extends Controller {
    @FXML
    private Spinner<Integer> inputMagassag;
    @FXML
    private TextField inputEmber;
    @FXML
    private Spinner<Integer> inputAr;

    @FXML
    public void onHozzaadButtonClick(ActionEvent actionEvent) {
        String ember = inputEmber.getText().trim();
        int magassag;
        int ar;

        if (ember.isEmpty()) {
            alert("Tulajdonos megadása kötelező.");
            return;
        }
        try {
            magassag = inputMagassag.getValue();
        } catch (NullPointerException e) {
            alert("Magasság kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("A magasság csak 1 és 200 közötti szám lehet.");
            return;
        }
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException e) {
            alert("Ár kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("Az ár csak 100 és 5000 közötti szám lehet.");
            return;
        }

        try {
            Szobrok ujSzobor = new Szobrok(0, ember, magassag, ar);
            Szobrok letrehozott = MuzeumApi.szoborHozzadasa(ujSzobor);
            if (letrehozott != null) {
                alert("Szobor hozzáadása sikeres!");
                this.stage.close();
            } else {
                alert("Szobor hozzáadása sikertelen!");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}
