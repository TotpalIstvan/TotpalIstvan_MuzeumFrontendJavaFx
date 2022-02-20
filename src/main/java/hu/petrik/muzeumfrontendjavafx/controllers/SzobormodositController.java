package hu.petrik.muzeumfrontendjavafx.controllers;

import hu.petrik.muzeumfrontendjavafx.Controller;
import hu.petrik.muzeumfrontendjavafx.Szobrok;
import hu.petrik.muzeumfrontendjavafx.api.MuzeumApi;
import hu.petrik.muzeumfrontendjavafx.Festmeny;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SzobormodositController extends Controller {
    @FXML
    private Spinner<Integer> inputMagassag;
    @FXML
    private TextField inputEmber;
    @FXML
    private Spinner<Integer> inputAr;

    private Szobrok modositando;

    public Szobrok getModositando() {
        return modositando;
    }

    public void setModositando(Szobrok modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputEmber.setText(modositando.getSzemely());
        inputMagassag.getValueFactory().setValue(modositando.getMagassag());
        inputAr.getValueFactory().setValue(modositando.getAr());
    }

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String tulaj = inputEmber.getText().trim();
        int magassag;
        int ar;
        if (tulaj.isEmpty()) {
            alert("Tulajdonos megadása kötelező.");
            return;
        }
        try {
            magassag = inputMagassag.getValue();
        } catch (NullPointerException e) {
            alert("Magasság kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("A magasság csak 1 és 200 cm közötti érték lehet.");
            return;
        }
        try {
            ar = inputAr.getValue();
        } catch (NullPointerException e) {
            alert("Ár kiválasztása kötelező.");
            return;
        } catch (Exception e) {
            alert("Az ár csak 100 és 5000 Ft közötti pénzérték lehet.");
            return;
        }

        modositando.setSzemely(tulaj);
        modositando.setMagassag(magassag);
        modositando.setAr(ar);

        try {
            Szobrok modositott = MuzeumApi.szoborModositasa(modositando);
            if (modositott != null) {
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
}
