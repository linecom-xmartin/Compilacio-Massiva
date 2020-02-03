package com.linecom.compilacionmasiva.bbva.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.linecom.compilacionmasiva.bbva.config.StageManager;
import com.linecom.compilacionmasiva.bbva.service.CompMassService;
import com.linecom.compilacionmasiva.bbva.view.FxmlView;

@SuppressWarnings("restriction")
@Controller
public class ObtenirProgramesController implements Initializable{
	@FXML
    private Label lblResultat;
	
	@FXML
	private Button btnIniciar;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private CompMassService compMassService;
	
	private ProgressIndicator progress;
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }
	
	@FXML
	private VBox vBoxCenter;

    @FXML
    private void loadViewVerProgramas(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.VEURE_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewGenerarPaquetes(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.GENERAR_PAQUETS_COMPILAR);
    }
    @FXML
    private void loadViewActualitzarProgrames(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.ACTUALITZAR_PROGRAMES_COMPILAR);
    }
    
    @FXML
    private void obtenirProgramesCompilar(ActionEvent event) {
    	try {
    		long numResultats = compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null);
    		lblResultat.setText("Copiando datos...");
    		Task<Void> tasca = new Task<Void>() { 
				@Override
				protected Void call() throws Exception {
					compMassService.carregarPaquetsACompilar();
					return null;
				}	
			};
			tasca.setOnRunning((e) -> vBoxCenter.getChildren().add(progress));
			tasca.setOnSucceeded((e) -> {
				long numResultatsSucceded = compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null);
				lblResultat.setText("Se han copiado correctamente "+ numResultatsSucceded + " registros.");
				vBoxCenter.getChildren().remove(progress);
			});
			tasca.setOnFailed((e) ->lblResultat.setText("Error:Existen datos en la tabla TIC_COMPILACIONES_MASIVA"));
			progress.setMaxSize(50, 50);
    		if (numResultats == 0) {
    			new Thread(tasca).start();
    		} else {
    			lblResultat.setText("Error:Existen datos en la tabla TIC_COMPILACIONES_MASIVA");
    		}
    	} catch (Exception ex) {
    		lblResultat.setText("Se ha producido un error:" + ex);
    		compMassService.eliminarTicCompilacioMassiva();
    	}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		progress = new ProgressIndicator();
		long numResultats = compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null);
		if (numResultats > 0) {
			lblResultat.setText("Error:Existen datos en la tabla TIC_COMPILACIONES_MASIVA");
		}
	}
	
}
