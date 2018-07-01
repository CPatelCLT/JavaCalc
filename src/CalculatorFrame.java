import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame {
    private JButton a0Button;
    private JButton cButton;
    private JButton addButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton subButton;
    private JButton multButton;
    private JButton divButton;
    private JButton decButton;
    private JTextField resultTxt;
    private JButton eqButton;
    private JPanel calculatorView;
    private Operation calcOp;
    private Double leftOperand;
    private Double rightOperand;

    public CalculatorFrame() {
        a0Button.addActionListener(new NumberBtnClicked(a0Button.getText()));
        a1Button.addActionListener(new NumberBtnClicked(a1Button.getText()));
        a2Button.addActionListener(new NumberBtnClicked(a2Button.getText()));
        a3Button.addActionListener(new NumberBtnClicked(a3Button.getText()));
        a4Button.addActionListener(new NumberBtnClicked(a4Button.getText()));
        a5Button.addActionListener(new NumberBtnClicked(a5Button.getText()));
        a6Button.addActionListener(new NumberBtnClicked(a6Button.getText()));
        a7Button.addActionListener(new NumberBtnClicked(a7Button.getText()));
        a8Button.addActionListener(new NumberBtnClicked(a8Button.getText()));
        a9Button.addActionListener(new NumberBtnClicked(a9Button.getText()));

        multButton.addActionListener(new OpBtnClicked(Operation.MULTIPLICATION));
        divButton.addActionListener(new OpBtnClicked(Operation.DIVISION));
        subButton.addActionListener(new OpBtnClicked(Operation.SUBTRACTION));
        addButton.addActionListener(new OpBtnClicked(Operation.ADDITION));
        cButton.addActionListener(new ClearBtnClicked());
        eqButton.addActionListener(new EqualBtnClicked());
        decButton.addActionListener(new DecBtnClicked());
    }

    private class NumberBtnClicked implements ActionListener {

        private String value;

        public NumberBtnClicked(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(leftOperand == null || leftOperand == 0.0) {
                value = resultTxt.getText() + value;
            }else{
                rightOperand = Double.valueOf(value);
            }
            resultTxt.setText(value);

        }
    }

    private class OpBtnClicked implements ActionListener {

        private Operation operation;

        public OpBtnClicked(Operation operation) {
            this.operation = operation;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            calcOp = operation;
            leftOperand = Double.valueOf(resultTxt.getText());
        }
    }

    private class ClearBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultTxt.setText("");
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    private class DecBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            resultTxt.setText(resultTxt.getText() + ".");

        }
    }

    private class EqualBtnClicked implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Double output = calcOp.getOperator().applyAsDouble(leftOperand, rightOperand);
            resultTxt.setText(output%1==0?String.valueOf(output.intValue()):String.valueOf(output));
            leftOperand = 0.0;
            rightOperand = 0.0;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CalculatorFrame");
        frame.setContentPane(new CalculatorFrame().calculatorView);
        frame.setSize(300,550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
