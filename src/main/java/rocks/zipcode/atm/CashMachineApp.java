package rocks.zipcode.atm;

import javafx.scene.paint.Color;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {


    private TextField accountIdField = new TextField();
    private TextField depositField = new TextField();
    private TextField withdrawField = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());


    private Parent createContent() {
        VBox vbox = new VBox(10);                               // gray spacing around the buttons
        vbox.setPrefSize(500, 300);                 // window size 600x600 default

        TextArea areaInfo = new TextArea();

        // default text appearing in the text fields
        accountIdField.setPromptText("Enter Account ID");
        depositField.setPromptText(("Enter deposit amount"));
        withdrawField.setPromptText("Enter withdraw amount");

        // button creations
        Button btnLogin = new Button("Enter Account ID");
        Button btnDeposit = new Button("Deposit");
        Button btnWithdraw = new Button("Withdraw");
        Button btnExit = new Button("Logout");

        // setting initial states for buttons
        btnLogin.setDisable(false);
        btnDeposit.setDisable(true);
        btnWithdraw.setDisable(true);
        btnExit.setDisable(true);


        // account ID button    // button disabled on startup
        btnLogin.setOnAction(e -> {
            int id = Integer.parseInt(accountIdField.getText());
            cashMachine.login(id);
            btnLogin.setDisable(true);
            btnDeposit.setDisable(false);
            btnWithdraw.setDisable(false);
            btnExit.setDisable(false);


            areaInfo.setText(cashMachine.toString());
        });

        // deposit button   // button disabled on startup
        btnDeposit.setOnAction(e -> {
            Float amount = Float.parseFloat(depositField.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        // withdraw button  // button disabled on startup
        btnWithdraw.setOnAction(e -> {
            Float amount = Float.parseFloat(withdrawField.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        // Logout button    // button disabled on startup
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            btnLogin.setDisable(false);
            btnDeposit.setDisable(true);
            btnWithdraw.setDisable(true);
            btnExit.setDisable(true);
            clearTextFields();
//            disableButtons();

            areaInfo.setText(cashMachine.toString());
        });

        // creation of flowpane
        FlowPane flowpane = new FlowPane();

        // adding buttons to flowpane
        flowpane.getChildren().add(btnLogin);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);

        // these flowpanes place the text fields and buttons at the top of the window all in succession, top to bottom
        FlowPane flowPaneAccountId = new FlowPane();
        flowPaneAccountId.getChildren().add(accountIdField);                                        // top layer
        flowPaneAccountId.getChildren().add(btnLogin);

        FlowPane flowPaneDeposit = new FlowPane();
        flowPaneDeposit.getChildren().add(depositField);                                            // middle layer
        flowPaneDeposit.getChildren().add(btnDeposit);

        FlowPane flowPaneWithdraw = new FlowPane();
        flowPaneWithdraw.getChildren().add(withdrawField);                                          // bottom layer
        flowPaneWithdraw.getChildren().add(btnWithdraw);


        // the vbox is the virtualbox, and added are the following parameters
        // this is what will be shown on the virtualbox
        vbox.getChildren().addAll(flowPaneAccountId, flowPaneDeposit, flowPaneWithdraw, flowpane, areaInfo);
        return vbox;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();                                                       // this shows the larger stage window
    }

    // method to clear all text fields--can call whenever needed
    public void clearTextFields() {
        accountIdField.clear();
        depositField.clear();
        withdrawField.clear();
    }

    public static void main(String[] args) {
        launch(args);                                                           // this launches the app
    }
}
