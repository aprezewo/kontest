module konzentrationstest {
	exports tech;
	exports menus;
	exports graphic;
	exports general;
	exports events;

	requires javafx.fxml;

    opens menus to javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
}