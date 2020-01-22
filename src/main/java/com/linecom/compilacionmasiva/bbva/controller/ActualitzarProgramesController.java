package com.linecom.compilacionmasiva.bbva.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.linecom.compilacionmasiva.bbva.bean.ResumTicCompilacionesMasiva;
import com.linecom.compilacionmasiva.bbva.config.StageManager;
import com.linecom.compilacionmasiva.bbva.service.CompMassService;
import com.linecom.compilacionmasiva.bbva.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Animation.Status;

@SuppressWarnings("restriction")
@Controller
public class ActualitzarProgramesController implements Initializable{
	@FXML
    private Label lblResultat;
	
	@FXML
    private Label lblElimina;
	
	@FXML
	private TableView<ResumTicCompilacionesMasiva> ticCompilacionesMasivaResumTable;
	
	@FXML
    private MenuItem actualitzar;
		
	@FXML
	private VBox vBoxCenter;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private CompMassService compMassService;
	
	private Timeline timeline;
	
	private ObservableList<ResumTicCompilacionesMasiva> observableList = FXCollections.<ResumTicCompilacionesMasiva>observableArrayList();
	
	@Autowired
	@Value("${usuario.filtro}")
	private String usuari;
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

    @FXML
    private void loadViewCompilacionProgramas(ActionEvent event) throws IOException {
    	if (!timeline.getStatus().equals(Status.STOPPED)) timeline.stop();
    	stageManager.switchScene(FxmlView.OBTENIR_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewVerProgramas(ActionEvent event) throws IOException {
    	if (!timeline.getStatus().equals(Status.STOPPED)) timeline.stop();
    	stageManager.switchScene(FxmlView.VEURE_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewGenerarPaquetes(ActionEvent event) throws IOException {
    	if (!timeline.getStatus().equals(Status.STOPPED)) timeline.stop();
    	stageManager.switchScene(FxmlView.GENERAR_PAQUETS_COMPILAR);
    }
    
    @FXML
    private void obtenirProgramesCompilar(ActionEvent event) {
    	try {
    		long numResultats = compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null);
    		if (numResultats > 0) {
    			compMassService.carregarPaquetsACompilar();
    			numResultats = compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null);
    			lblResultat.setText("Se han copiado correctamente "+ numResultats + " registros.");
    		} else {
    			lblResultat.setText("Error:Existen datos en la tabla TIC_COMPILACIONES_MASIVA");
    		}
    	} catch (Exception ex) {
    		lblResultat.setText("Se ha producido un error:" + ex);
    	}
    }
    
    @FXML
    private void actualitzaTicCompilacionesMasiva(ActionEvent event) {
    	compMassService.actualitzarTicCompilacioMassiva();
    	actualitzarTaula();
    }
    
    @FXML
    private void actualitzaTicCompilaciones(ActionEvent event) {
    	lblResultat.setText("Copiando datos...");
    	try {
	    	compMassService.carregarACompilacions();
	    	lblResultat.setText("Se ha actualizado correctamente TIC_COMPILACIONES");
    	} catch (Exception ex) {
        	//LOG
    		lblResultat.setText("Se han producido errores durante la actualización de TIC_COMPILACIONES");
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ticCompilacionesMasivaResumTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//Primer el total +
		//Hi ha 3 estats:
		//0 --> OK
		//1 --> Pendent
		//2 --> KO
		//TODO: ASSEGURAR!!
		crearTaula();
		
		CheckBox checkBox1 = new CheckBox("Actualizar siempre");
		checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				if(new_val) {
					executarTimerUpdate();
				} else {
					timeline.stop();
				}
			}

			private void executarTimerUpdate() {
				timeline = new Timeline(new KeyFrame(Duration.seconds(30), "Update taula", new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						compMassService.actualitzarTicCompilacioMassiva();
						actualitzarTaula();
					}
				}));
				timeline.setCycleCount(Timeline.INDEFINITE);
				timeline.play();
			}
		});
		vBoxCenter.getChildren().add(checkBox1);
		
		lblElimina.setText("con usuario=" + usuari + " y pendientes");
		
	}

	@SuppressWarnings("unchecked")
	private void crearTaula() {
		ticCompilacionesMasivaResumTable.getItems().clear();
		observableList.clear();
		compMassService.actualitzarTicCompilacioMassiva();
		observableList.addAll(obtenirLlistatTaula());
		ticCompilacionesMasivaResumTable.getItems().addAll(observableList);
		TableColumn<ResumTicCompilacionesMasiva,Long> colNumResultats = new TableColumn<>("num.Resultats");
		PropertyValueFactory<ResumTicCompilacionesMasiva, Long> numResultatsCellValueFactory = new PropertyValueFactory<>("numResultats");
		colNumResultats.setCellValueFactory(numResultatsCellValueFactory);
		TableColumn<ResumTicCompilacionesMasiva,String> colDescripcioResultats = new TableColumn<>("Resultados TIC_COMPILACIONES_MASIVA");
		PropertyValueFactory<ResumTicCompilacionesMasiva, String> descripcioResultatsCellValueFactory = new PropertyValueFactory<>("descripcioResultat");
		colDescripcioResultats.setCellValueFactory(descripcioResultatsCellValueFactory);
		ticCompilacionesMasivaResumTable.getColumns().addAll(colNumResultats,colDescripcioResultats);
	}
	
	private void actualitzarTaula() {
		ticCompilacionesMasivaResumTable.getItems().clear();
		observableList.clear();
		observableList.addAll(obtenirLlistatTaula());
		ticCompilacionesMasivaResumTable.getItems().addAll(observableList);
	}
	
	private ObservableList<ResumTicCompilacionesMasiva> obtenirLlistatTaula() {
		ResumTicCompilacionesMasiva resumTotal = new ResumTicCompilacionesMasiva(compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(null), "Total programas");
		ResumTicCompilacionesMasiva resumOK = new ResumTicCompilacionesMasiva(compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(0), "Total programas a compilar");
		ResumTicCompilacionesMasiva resumPendent = new ResumTicCompilacionesMasiva(compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(2), "Total programas compilados correctamente");
		ResumTicCompilacionesMasiva resumKO = new ResumTicCompilacionesMasiva(compMassService.obtenirTotalTicCompilacioMassivaPerResultatCompilacio(3), "Total programas con error");
		ObservableList<ResumTicCompilacionesMasiva> observableListTaula = FXCollections.<ResumTicCompilacionesMasiva>observableArrayList(resumTotal,resumOK,resumPendent,resumKO);
		return observableListTaula;
	}
	
	@FXML
	private void eliminaTicCompilaciones(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Seguro que quieres eliminar los registros de TIC_COMPILACIONES con usuario=" + usuari + "y resultado_compilacion=0?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) {
			compMassService.elimiarTicCompilacionesPerUsuari(usuari);
			lblResultat.setText("Se han eliminado los registros");
			actualitzarTaula();
		}
	}
	
}
