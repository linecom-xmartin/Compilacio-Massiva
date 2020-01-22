package com.linecom.compilacionmasiva.bbva.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.linecom.compilacionmasiva.bbva.config.StageManager;
import com.linecom.compilacionmasiva.bbva.service.CompMassService;
import com.linecom.compilacionmasiva.bbva.view.FxmlView;

@SuppressWarnings("restriction")
@Controller
public class GenerarPaquetsController implements Initializable{
	@FXML
    private Label lblResultat;
	
//	@FXML
//    private Button btnCompiProgramas;
	
	@FXML
	private TextField txtNomProjecte;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private CompMassService compMassService;
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

    @FXML
    private void loadViewVerProgramas(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.VEURE_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewObtenirProgrames(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.OBTENIR_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewActualitzarProgrames(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.ACTUALITZAR_PROGRAMES_COMPILAR);
    }
    
    @FXML
    private void generarPaquets(ActionEvent event) {
    	if (compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2) == compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)) {
	    	try {
	    		int numPaquets = compMassService.compilarPaquets(txtNomProjecte.getText());
	    		lblResultat.setText("Se han procesado correctamente "+ numPaquets + " paquetes.");
	    	} catch (IllegalArgumentException ex) {
	    		lblResultat.setText("Error: El nombre de proyecte esta vacío o es incorrecto");
	    	}
	    	catch (Exception ex) {
	    		lblResultat.setText("Se ha producido un error:" + ex);
	    	}
    	} else {
    		lblResultat.setText("Los valores de TIC_COMPILACIONES_MASIVA son incorrectos");
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2) != compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)) {
			lblResultat.setText("Los valores de TIC_COMPILACIONES_MASIVA son incorrectos");
		}
	}
	
}
