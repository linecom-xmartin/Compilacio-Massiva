package com.linecom.compilacionmasiva.bbva.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
	
	@FXML
	private TextField txtNomProjecte;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private CompMassService compMassService;
	
	@FXML
	private VBox vBoxCenter;
	
	private ProgressIndicator progress;
	
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
    	if (nomProjecteValid()) {
    		if (compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2) == compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)) {
    	    	try {
    	    		lblResultat.setText("Generando paquetes...");
    	    		Task<Integer> tasca = new Task<Integer>() { 
    					@Override
    					protected Integer call() throws Exception {
    						int numPaquets = compMassService.compilarPaquets(txtNomProjecte.getText());
    						return numPaquets;
    					}	
    				};
    				tasca.setOnRunning((e) -> vBoxCenter.getChildren().add(progress));
    				tasca.setOnSucceeded((e) -> {
    					lblResultat.setText("Se han procesado correctamente "+ tasca.getValue() + " paquetes.");
    					vBoxCenter.getChildren().remove(progress);
    				});
    				tasca.setOnFailed((e) ->lblResultat.setText("Error:Al ejecutar la tarea de generar paquetes"));
    				progress.setMaxSize(50, 50);
    	    		new Thread(tasca).start();
    	    		
    	    	} catch (IllegalArgumentException ex) {
    	    		lblResultat.setText("Error: El nombre de proyecte esta vacío o es incorrecto");
    	    	}
    	    	catch (Exception ex) {
    	    		lblResultat.setText("Se ha producido un error:" + ex);
    	    	}
        	} else {
        		lblResultat.setText("Los valores de TIC_COMPILACIONES_MASIVA son incorrectos");
        	}
    	} else {
    		lblResultat.setText("Error: El nombre de proyecte esta vacío o es incorrecto");
    	}
    }
    
	private boolean nomProjecteValid() {
		String nomProjecte = txtNomProjecte.getText();
		return nomProjecte != null && !nomProjecte.isEmpty();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progress = new ProgressIndicator();
		if (compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2) != compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null)) {
			lblResultat.setText("Los valores de TIC_COMPILACIONES_MASIVA son incorrectos");
		}
	}
}
