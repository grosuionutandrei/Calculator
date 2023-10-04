package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Stack;


public class CalculatorController {

    @FXML
    private TextField screen;
    private String operation = "";
    private boolean isOperandPressed = false;
    private boolean illegleOperation = false;
    private boolean getAnotherValue = false;
    private boolean reversingNumber = false;
    private String reversedInput = "";
    private Stack<String> inputs = new Stack<String>();


    private char[] operations = {'+', '-', '/', '*', '%'};

    @FXML
    public void cancelOperations(ActionEvent event) {
        screen.setText("");
        operation = "";

        isOperandPressed = false;

    }

    @FXML
    public void reverseNumber(ActionEvent event) {
        if (screen.getText().isEmpty()) {
            illegleOperation = true;
            screen.setText("Illegal operation");
            return;
        }
        String doubleT = replaceComma(screen.getText());
        reversingNumber=true;
        double reverse = Double.parseDouble(doubleT);
        reversedInput=String.valueOf(reverse*(-1));
        screen.setText(reversedInput);
    }

    public double calculatePercentage(double first, double second) {
        double operand = first / 100;
        return second * operand;
    }

    public double calculateDivision(double first, double second) {
        return first / second;
    }

    @FXML
    public void displayNumber(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
        checkForOperand(pressedButton);

        if (illegleOperation) {
            screen.setText("");
            operation = "";
            //inputs.clear();
            illegleOperation = false;
        } else if (isOperandPressed) {
            inputs.push(screen.getText());
            screen.setText(pressedButton.getText());
            inputs.push(pressedButton.getText());
            operation += pressedButton.getText();

            isOperandPressed = false;
            getAnotherValue = true;

        } else if(reversingNumber){
               System.out.println(operation);
               if(inputs.size()>0){
                   System.out.println(inputs.pop()+"popped");
                   inputs.push(screen.getText());
               }
               reversingNumber=false;
               screen.setText(pressedButton.getText());
        }else {
            if (getAnotherValue) {
                screen.setText("");
            }
            operation += pressedButton.getText();
            //inputs.add(pressedButton.getText());
            String previous = screen.getText();
            screen.setText(previous += pressedButton.getText());
            getAnotherValue = false;
        }



    }


    public double calculateMultiply(double first, double second) {
        return first * second;
    }

    private double calculateSubstraction(double first, double second) {
        return first - second;
    }

    private double calculateAddition(double first, double second) {
        return first + second;
    }


    private void checkForOperand(Button btn) {

        if (btn.getId().equals("btnPlus")) {
            isOperandPressed = true;
        }
        if (btn.getId().equals("btnMinus")) {
            isOperandPressed = true;
        }
        if (btn.getId().equals("btnMultiply")) {
            isOperandPressed = true;
        }
        if (btn.getId().equals("btnDivision")) {
            isOperandPressed = true;
        }
        if (btn.getId().equals("btnPercentage")) {
            isOperandPressed = true;
        }
    }

    public void executeCalculus(ActionEvent event) {
        System.out.println(operation);
        inputs.push(screen.getText());
        for(String l :inputs){
            System.out.println(l);
        }
        screen.setText("");
        inputs.clear();



    }


    private boolean convertToInt(double number) {
        return (number - Math.floor(number)) == 0.0;
    }

    private String replaceComma(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ',') {
                chars[i] = '.';
            }
        }
        return new String(chars);
    }

}
