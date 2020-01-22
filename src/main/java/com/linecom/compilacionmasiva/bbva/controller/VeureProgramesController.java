package com.linecom.compilacionmasiva.bbva.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.linecom.compilacionmasiva.bbva.bean.VistaTicCompilacionesMasiva;
import com.linecom.compilacionmasiva.bbva.config.StageManager;
import com.linecom.compilacionmasiva.bbva.entity.TicCompilacionesMasiva;
import com.linecom.compilacionmasiva.bbva.service.CompMassService;
import com.linecom.compilacionmasiva.bbva.view.FxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.control.Button;

@SuppressWarnings("restriction")
@Controller
public class VeureProgramesController implements Initializable{
	
	private static final Integer NOMES_ERROR = 3;

	@FXML
    private Label lblResultat;
	
	@FXML
	private TableView<VistaTicCompilacionesMasiva> ticCompilacionesMasivaTable;
	
	@FXML
    private MenuItem actualitzar;
	
	@FXML
	private VBox vBoxCenter;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private CompMassService compMassService;
	
	private ObservableList<VistaTicCompilacionesMasiva> observableList = FXCollections.<VistaTicCompilacionesMasiva>observableArrayList();;
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

    @FXML
    private void loadViewCompilacionProgramas(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.OBTENIR_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewActualizarProgramas(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.ACTUALITZAR_PROGRAMES_COMPILAR);
    }
    @FXML
    private void loadViewGenerarPaquetes(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.GENERAR_PAQUETS_COMPILAR);
    }
    
    @FXML
    private void actualitzaTicCompilacionesMasiva(ActionEvent event) {
    	actualitzarTaula(null);
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ticCompilacionesMasivaTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		crearTaula();
		CheckBox checkBox1 = new CheckBox("Ver solo resultado de error");
		checkBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				if(new_val) {
					actualitzarTaula(NOMES_ERROR);
				} else {
					actualitzarTaula(null);
				}
			}
		});
		vBoxCenter.getChildren().add(checkBox1);
	}

	@SuppressWarnings("unchecked")
	private void crearTaula() {
		ticCompilacionesMasivaTable.getItems().clear();
		compMassService.actualitzarTicCompilacioMassiva();
		observableList.addAll(obtenirLlistatTaula(null));
		ticCompilacionesMasivaTable.getItems().addAll(observableList);
		TableColumn<VistaTicCompilacionesMasiva,String> colGrupoFuncional = new TableColumn<>("Grupo Funcional");
		PropertyValueFactory<VistaTicCompilacionesMasiva, String> grupoFuncionalCellValueFactory = new PropertyValueFactory<>("nombreGrupoFuncional");
		colGrupoFuncional.setCellValueFactory(grupoFuncionalCellValueFactory);
		TableColumn<VistaTicCompilacionesMasiva,BigDecimal> colEntidad = new TableColumn<>("Entidad");
		PropertyValueFactory<VistaTicCompilacionesMasiva, BigDecimal> entidadCellValueFactory = new PropertyValueFactory<>("entidad");
		colEntidad.setCellValueFactory(entidadCellValueFactory);
		TableColumn<VistaTicCompilacionesMasiva,String> colEntorno = new TableColumn<>("Entorno");
		PropertyValueFactory<VistaTicCompilacionesMasiva, String> entornoCellValueFactory = new PropertyValueFactory<>("entorno");
		colEntorno.setCellValueFactory(entornoCellValueFactory);
		TableColumn<VistaTicCompilacionesMasiva,BigDecimal> colResultadoCompilacion = new TableColumn<>("Resultado compilación");
		PropertyValueFactory<VistaTicCompilacionesMasiva, BigDecimal> resultadoCompilacionCellValueFactory = new PropertyValueFactory<>("resultadoCompilacion");
		colResultadoCompilacion.setCellValueFactory(resultadoCompilacionCellValueFactory);
		TableColumn<VistaTicCompilacionesMasiva,Boolean> colDownload = new TableColumn<>("Mas datos");
//		ticCompilacionesMasivaTable.get
//		colDownload.setCellValueFactory(cellData -> new SimpleObjectProperty<VistaTicCompilacionesMasiva>(cellData.getValue()));
		colDownload.setCellFactory(cellFactory);
		ticCompilacionesMasivaTable.getColumns().addAll(colGrupoFuncional,colEntidad, colEntorno, colResultadoCompilacion, colDownload);
	}
	
	Callback<TableColumn<VistaTicCompilacionesMasiva, Boolean>, TableCell<VistaTicCompilacionesMasiva, Boolean>> cellFactory = 
			new Callback<TableColumn<VistaTicCompilacionesMasiva, Boolean>, TableCell<VistaTicCompilacionesMasiva, Boolean>>()	{
		@Override
		public TableCell<VistaTicCompilacionesMasiva, Boolean> call( final TableColumn<VistaTicCompilacionesMasiva, Boolean> param) {
			final TableCell<VistaTicCompilacionesMasiva, Boolean> cell = new TableCell<VistaTicCompilacionesMasiva, Boolean>() {
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/open-doc.png"));
				final Button btnEdit = new Button();
				
				@Override
				public void updateItem(Boolean check, boolean empty) {
					super.updateItem(check, empty);
					if(empty) {
						setGraphic(null);
						setText(null);
					} else{
						VistaTicCompilacionesMasiva row = getTableView().getItems().get(getIndex());
						btnEdit.setOnAction(e ->{
							guardaFitxer(row);
						});
						if (row.getFichero() != null) {
							btnEdit.setStyle("-fx-background-color: transparent;");
							ImageView iv = new ImageView();
					        iv.setImage(imgEdit);
					        iv.setPreserveRatio(true);
					        iv.setSmooth(true);
					        iv.setCache(true);
							btnEdit.setGraphic(iv);
							
							setGraphic(btnEdit);	
						} else {
							setGraphic(null);
						}
						
						setText(null);
					}
				}

				private void guardaFitxer(VistaTicCompilacionesMasiva row) {
					if (row.getFichero() != null) {
						lblResultat.setText("Guardando el fichero...");
						String nomFitxer = row.getNombreGrupoFuncional() + "." + row.getTipoFuente();
				        try {
							saveBytesToFile(nomFitxer, row.getFichero());
							lblResultat.setText("Fichero guardado en ./" + nomFitxer);
						} catch (IOException e) {
							lblResultat.setText("Error al guardar el fichero" + e);
						}	
					}
				}

				private void saveBytesToFile(String nomFitxer, byte[] fichero) throws IOException {
					FileOutputStream outputStream = new FileOutputStream(nomFitxer);
			        outputStream.write(fichero);
			        outputStream.close();
				}
			};
			return cell;
		}
	};
	
	private void actualitzarTaula(Integer resultatCompilacio) {
		ticCompilacionesMasivaTable.getItems().clear();
		observableList.clear();
		observableList.addAll(obtenirLlistatTaula(resultatCompilacio));
		ticCompilacionesMasivaTable.getItems().addAll(observableList);
	}
	
	private ObservableList<VistaTicCompilacionesMasiva> obtenirLlistatTaula(Integer resultatCompilacio) {
		List<TicCompilacionesMasiva> resultats = compMassService.veureTicCompilacioMassivaPerResultatCompilacio(resultatCompilacio);
		List<VistaTicCompilacionesMasiva> resultatsVista = new ArrayList<VistaTicCompilacionesMasiva>();
		for (TicCompilacionesMasiva resultat : resultats) {
			VistaTicCompilacionesMasiva resultatVista = new VistaTicCompilacionesMasiva(resultat.getTicCompilacionesId().getNombreGrupoFuncional(), 
				resultat.getTicCompilacionesId().getEntidad(), resultat.getTicCompilacionesId().getEntorno() , resultat.getResultadoCompilacion(), resultat.getFicheroFuente(),
				resultat.getTipoFuente());
			resultatsVista.add(resultatVista);
		}
		ObservableList<VistaTicCompilacionesMasiva> observableListTaula = FXCollections.<VistaTicCompilacionesMasiva>observableArrayList(resultatsVista);
		return observableListTaula;
	}
	
	@FXML
	private void descarregaDocument(ActionEvent event) {
		
	}
}
