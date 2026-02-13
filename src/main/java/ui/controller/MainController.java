package main.java.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import main.java.backend.*;
import javafx.scene.control.TableColumn;
import main.java.ui.Item;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;


public class MainController {

    @FXML private VBox mainVbox;
    @FXML private TextField widthIn;
    @FXML private TextField heightIn;
    @FXML private TextField addedCostsIn;
    @FXML private Label result;
    @FXML private CheckBox frame;
    @FXML private CheckBox edgeSealing;
    @FXML private CheckBox grommet;
    @FXML private CheckBox installation;
    @FXML private CheckBox design;
    @FXML private CheckBox addedCosts;
    @FXML private final int designPrice = 5000;
    @FXML private int sum;
    @FXML private TableView<Item> table;
    @FXML private TableColumn<Item, String> positionCol;
    @FXML private TableColumn<Item, Double> valueCol;
    private final ObservableList<Item> data = FXCollections.observableArrayList();
    private double width;
    private double height;
    private double addedCostsSum;

    @FXML private void onButton(){
        tableNull();
        onCalculate();

    }


    @FXML public void initialize() {
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        data.add(new Item("Площадь: ", 0.0));
        data.add(new Item("Цена за печать: ", 0.0));
        data.add(new Item("Количество бруса: ", 0.0));
        data.add(new Item("Цена каркаса: ", 0.0));
        data.add(new Item("Длина проклейки: ", 0.0));
        data.add(new Item("Цена за проклейку: ", 0.0));
        data.add(new Item("Количество люверсов: ", 0.0));
        data.add(new Item("Цена за люверсы: ", 0.0));
        data.add(new Item("Монтаж: ", 0.0));
        data.add(new Item("Дизайн: ", 0.0));
        data.add(new Item("Доп. расходы: ", 0.0));
        data.add(new Item("Сумма: ", 0.0));

        table.setItems(data);
        table.refresh();



        grommet.disableProperty().bind(edgeSealing.selectedProperty().not());

        edgeSealing.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                grommet.setSelected(false);
            }
        });
        addedCostsIn.disableProperty().bind(addedCosts.selectedProperty().not());

        mainVbox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onCalculate();
            }
        });

        // Чтобы VBox мог ловить клавиши
        mainVbox.setFocusTraversable(true);
        mainVbox.requestFocus();
    }




    private void tableNull(){
        data.get(0).setValue(0);
        data.get(1).setValue(0);
        data.get(2).setValue(0);
        data.get(3).setValue(0);
        data.get(4).setValue(0);
        data.get(5).setValue(0);
        data.get(6).setValue(0);
        data.get(7).setValue(0);
        data.get(8).setValue(0);
        data.get(9).setValue(0);
        data.get(10).setValue(0);
        data.get(11).setValue(0);
        table.refresh();
    }



    @FXML
    public void onCalculate(){
        try {
            boolean isFrame = frame.isSelected();
            boolean isEdgeSealing = edgeSealing.isSelected();
            boolean isGrommet = grommet.isSelected();
            boolean isInstallation = installation.isSelected();
            boolean isDesign = design.isSelected();
            boolean isAddedCosts = addedCosts.isSelected();



            String widthS = widthIn.getText().replaceAll(",", ".");
            String heightS = heightIn.getText().replaceAll(",", ".");
            String addedCostsSumS = addedCostsIn.getText().replaceAll(",", ".");
            width = Double.valueOf(widthS);
            height = Double.valueOf(heightS);
            if (isAddedCosts){
            addedCostsSum = Double.parseDouble(addedCostsSumS);}




            PrintPrice printPrice = new PrintPrice(width, height);
            FramePrice framePrice = new FramePrice(width, height);
            EdgeSealingPrice sealingPrice = new EdgeSealingPrice(width, height);
            GrommetPrice grommetPrice = new GrommetPrice(sealingPrice.getPerimeter());
            InstallationPrice installationPrice = new InstallationPrice(framePrice.getNumberOfBeams(), printPrice.getS());


            sum = 0;
            sum += printPrice.priceCalculation();

            if (isAddedCosts){
                sum += addedCostsSum;
                data.get(10).setValue(addedCostsSum);
            }

            if (isEdgeSealing && !isFrame) {
                sum += sealingPrice.getEdgeSealingPrice();
                data.get(5).setValue(sealingPrice.getEdgeSealingPrice());
                data.get(4).setValue(sealingPrice.getPerimeter());

                if (isGrommet) {
                    sum += grommetPrice.getGrommetAmountSum();
                    data.get(6).setValue(grommetPrice.getGrommetAmount());
                    data.get(7).setValue(grommetPrice.getGrommetAmountSum());
                }

                if (isInstallation) {
                    sum += installationPrice.installationPriceSealing();
                    data.get(8).setValue(installationPrice.installationPriceSealing());
                }
            }
            if (!isEdgeSealing && isFrame) {
                sum += framePrice.getFramePrice();
                data.get(2).setValue(framePrice.getNumberOfBeams());
                data.get(3).setValue(framePrice.getFramePrice());
                if (isInstallation) {
                    sum += installationPrice.installationPriceFrame();
                    data.get(8).setValue(installationPrice.installationPriceFrame());
                }
            }
            if (isEdgeSealing && isFrame && isInstallation) {
                sum += installationPrice.installationPriceFrameAndSealing();
                sum += framePrice.getPriceOfBeamsSealing();
                data.get(3).setValue(framePrice.getPriceOfBeamsSealing());
                data.get(2).setValue(framePrice.getNumberOfBeamsSealing());
                data.get(8).setValue(installationPrice.installationPriceFrameAndSealing());
                if (isEdgeSealing) {
                    sum += sealingPrice.getEdgeSealingPrice();
                    data.get(5).setValue(sealingPrice.getEdgeSealingPrice());
                    data.get(4).setValue(sealingPrice.getPerimeter());

                    if (isGrommet) {
                        sum += grommetPrice.getGrommetAmountSum();
                        data.get(6).setValue(grommetPrice.getGrommetAmount());
                        data.get(7).setValue(grommetPrice.getGrommetAmountSum());
                    }
                }
            } else if (isEdgeSealing && isFrame && !isInstallation) {
                result.setText("Каркас и проклейка одновременно без монтажа не делаются");
                return;
            }

            if (isDesign) {
                sum += designPrice;
                data.get(9).setValue(designPrice);
            }
            if (!isFrame && !isEdgeSealing && isInstallation){
                sum += installationPrice.installationPriceSealing();
                data.get(8).setValue(installationPrice.installationPriceSealing());
            }

            data.get(0).setValue(printPrice.getS());
            data.get(1).setValue(printPrice.priceCalculation());
            data.get(11).setValue(sum);
            result.setText("");

            table.refresh();
            sum = 0;}
        catch (NumberFormatException e) {
            result.setText("Введите корректные числа!");













        }
    }

}
