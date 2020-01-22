package com.linecom.compilacionmasiva.bbva.view;

public enum FxmlView {

    OBTENIR_PROGRAMES_COMPILAR {
        @Override
		public String getTitle() {
            return "Generación tabla programas a compilar";
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/ObtenirProgrames.fxml";
        }
    }, 
    ACTUALITZAR_PROGRAMES_COMPILAR {
        @Override
		public String getTitle() {
            return "Compilación y control programas";
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/ActualitzarProgrames.fxml";
        }
    },
    VEURE_PROGRAMES_COMPILAR {
    	@Override
		public String getTitle() {
            return "Visualizar programas";
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/VeureProgrames.fxml";
        }
    },
    GENERAR_PAQUETS_COMPILAR {
    	@Override
		public String getTitle() {
            return "Generar paquetes con programas a traspasar";
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/GenerarPaquets.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();

}
