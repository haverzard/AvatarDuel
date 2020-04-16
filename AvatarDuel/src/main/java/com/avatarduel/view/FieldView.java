package com.avatarduel.view;

import com.avatarduel.components.Basic;
import com.avatarduel.components.CustomBox;
import com.avatarduel.components.Space;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * FieldView describes the field view on the GUI that extends HBox class
 * that have fieldBox attribute that can be filled with GameCards.
 * 
 * @author Kelompok 2
 */
public class FieldView extends HBox {
    private static String FIELD_URL = "com/avatarduel/assets/image/fieldBackground.png";
    private static int fieldBoxCounts = 12;
    private List<HBox> fieldBoxes;

    /**
     * Create the field view on the GUI.
     */
    public FieldView() {
        super();
        fieldBoxes = new ArrayList<>();
        VBox fieldInside = new VBox();
        fieldInside.setAlignment(Pos.CENTER);
        fieldInside.setBorder(Basic.getBorder(1));
        fieldInside.getChildren().add(genBoxField());
        fieldInside.getChildren().add(new Space(10));
        fieldInside.getChildren().add(genBoxField());

        setMinWidth(800);
        setMinHeight(240);
        setBackground(Basic.getBackground(FIELD_URL));
        setAlignment(Pos.CENTER);
        getChildren().add(fieldInside);
    }

    /**
     * Create the boxes in the field GUI.
     */
    private HBox genBoxField() {
        int counts = fieldBoxCounts/2;
        double size = 110;
        double pad = 10;
        HBox boxField = new HBox();
        boxField.setMinWidth((size+pad)*counts+pad);
        boxField.setAlignment(Pos.CENTER);
        for (int i=0; i<counts-1; i++) {
            addBox(new CustomBox(size));
            boxField.getChildren().add(getLastBox());
            boxField.getChildren().add(new Space(10));
        }
        addBox(new CustomBox(size));
        boxField.getChildren().add(getLastBox());
        return boxField;
    }

    public List<HBox> getFieldBoxes() {
        return fieldBoxes;
    }

    public HBox getLastBox() {
        return fieldBoxes.get(fieldBoxes.size()-1);
    }

    // Pre-condition idx is valid
    public HBox getBox(int idx) {
        return fieldBoxes.get(idx);
    }

    public void addBox(HBox box) {
        fieldBoxes.add(box);
    }

    public void clearBox(int idx) {
        fieldBoxes.get(idx).getChildren().clear();
    }

}
