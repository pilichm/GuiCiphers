package pl.pilichm.gui.main;

import pl.pilichm.ciphers.substitution.*;
import pl.pilichm.ciphers.transposition.ColumnarTranspositionCipher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Arrays;

public class Utils {

    private static int getIntValueFromTextFiled(JTextField textField, int defaultValue){
        try {
            return Integer.parseInt(textField.getText());
        } catch (Exception exc){
            System.out.println("Invalid offset value!");
            textField.setForeground(Color.RED);
            textField.setForeground(Color.RED);
            return defaultValue;
        }
    }

    private static JPanel [] getPanelsCommonForCaesarAndROT13(){
        JPanel [] panels = new JPanel[2];

        JTextField txtTextIn = new JTextField("Text for processing");

        JLabel lblResult = new JLabel("Result: ");


        JCheckBox encryption = new JCheckBox("Encryption");
        JCheckBox decryption = new JCheckBox("Decryption");
        encryption.setSelected(true);

        txtTextIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        lblResult.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelEncryptionDecryption = new JPanel();
        panelEncryptionDecryption.setLayout(new BoxLayout(panelEncryptionDecryption, BoxLayout.X_AXIS));
        panelEncryptionDecryption.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelTextInOut = new JPanel();
        panelTextInOut.setLayout(new BoxLayout(panelTextInOut, BoxLayout.X_AXIS));
        panelTextInOut.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelEncryptionDecryption.add(encryption);
        panelEncryptionDecryption.add(decryption);

        encryption.addItemListener(e -> decryption.setSelected(!encryption.isSelected()));
        decryption.addItemListener(e -> encryption.setSelected(!decryption.isSelected()));

        panelTextInOut.add(txtTextIn);
        panelTextInOut.add(lblResult);

        panels[0] = panelTextInOut;
        panels[1] = panelEncryptionDecryption;

        return panels;
    }

    private static JPanel getPanelForKey(String labelName, String defaultValue){
        JTextField keyIn = new JTextField(defaultValue);
        JLabel labelKey = new JLabel(labelName);

        keyIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelKey = new JPanel();
        panelKey.setLayout(new BoxLayout(panelKey, BoxLayout.X_AXIS));
        panelKey.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelKey.add(labelKey);
        panelKey.add(keyIn);

        return panelKey;
    }

    public static JPanel [] getPanelsForCaesar(){
        JPanel [] panels = new JPanel[4];

        CaesarCipher cc = new CaesarCipher();

        JButton btnRunCipher = new JButton("run");

        JPanel panelOffset = getPanelForKey("Offset: ", "1");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelButton.add(btnRunCipher);

        JTextField offsetIn = (JTextField) panelOffset.getComponent(1);
        JLabel labelOffset = (JLabel) panelOffset.getComponent(0);

        panelOffset.add(labelOffset);
        panelOffset.add(offsetIn);

        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();

            String result;
            cc.setOffset(getIntValueFromTextFiled(offsetIn, 1));


            if (encryption.isSelected()){
                result = cc.encode(textToProcess);
            } else {
                result = cc.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = panelOffset;
        panels[3] = panelButton;

        return panels;
    }

    public static JPanel [] getPanelsForROT13() {
        JPanel [] panels = new JPanel[3];

        ROT13Cipher rot13 = new ROT13Cipher();

        JButton btnRunCipher = new JButton("run");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelButton.add(btnRunCipher);

        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            String result;

            if (encryption.isSelected()){
                result = rot13.encode(textToProcess);
            } else {
                result = rot13.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = panelButton;

        return panels;
    }

    public static JPanel [] getPanelsForAutoKey() {
        JPanel [] panels = new JPanel[4];

        AutoKeyCipher akc = new AutoKeyCipher("QUEENLY");

        JButton btnRunCipher = new JButton("run");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelButton.add(btnRunCipher);

        JPanel panelKey = getPanelForKey("Key: ", "QUEENLY");
        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        JTextField keyIn = (JTextField) panelKey.getComponent(1);
        JLabel labelKey = (JLabel) panelKey.getComponent(0);

        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            String currentKey;

            try {
                currentKey = keyIn.getText();
            } catch (Exception exc){
                System.out.println("Invalid offset value!");
                keyIn.setForeground(Color.RED);
                labelKey.setForeground(Color.RED);
                return;
            }

            String result;
            akc.setPrimer(currentKey);


            if (encryption.isSelected()){
                result = akc.encode(textToProcess);
            } else {
                result = akc.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = panelKey;
        panels[3] = panelButton;

        return panels;
    }

    public static JPanel [] getPanelsForAffine(){
        JPanel [] panels = new JPanel[5];
        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        AffineCipher ac = new AffineCipher();

        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        JPanel parameterA = getPanelForKey("Parameter a: ", "5");
        JPanel parameterB = getPanelForKey("Parameter b: ", "8");

        JTextField paramaterAValue = (JTextField) parameterA.getComponent(1);
        JTextField paramaterBValue = (JTextField) parameterB.getComponent(1);

        JButton btnRunCipher = new JButton("run");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelButton.add(btnRunCipher);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            String result;

            ac.setParameter_a(getIntValueFromTextFiled(paramaterAValue, 5));
            ac.setParameter_b(getIntValueFromTextFiled(paramaterBValue, 8));

            if (encryption.isSelected()){
                result = ac.encode(textToProcess);
            } else {
                result = ac.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = parameterA;
        panels[3] = parameterB;
        panels[4] = panelButton;

        return panels;
    }

    public static JPanel [] getPanelsForHill(){
        JPanel [] panels = new JPanel[4];
        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        HillCipher hc = new HillCipher();

        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        JButton btnRunCipher = new JButton("run");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel keyMatrixPanel = new JPanel();
        keyMatrixPanel.setLayout(new GridLayout(0, 3));

        for (int value : Arrays.asList(6, 24, 1, 13, 16, 10, 20, 17, 15)){
            JTextField matrixCell = new JTextField(String.valueOf(value));
            keyMatrixPanel.add(matrixCell);
        }

        JLabel keyLabel = new JLabel("Key: ");

        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new BoxLayout(keyPanel, BoxLayout.X_AXIS));
        keyPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        keyPanel.add(keyLabel);
        keyPanel.add(keyMatrixPanel);

        panelButton.add(btnRunCipher);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            String result;

            double [][] key = {
                    new double[]{6, 24, 1},
                    new double[]{13, 16, 10},
                    new double[]{20, 17, 15}
            };

            for (int index=0; index<9; index++){
                JTextField cell = (JTextField) keyMatrixPanel.getComponent(index);
                key[index/3][index%3] = Integer.parseInt(cell.getText());
            }

            hc.setKey(key);

            if (encryption.isSelected()){
                result = hc.encode(textToProcess);
            } else {
                result = hc.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = keyPanel;
        panels[3] = panelButton;

        return panels;
    }

    public static JPanel [] getPanelsForColumnarTransposition() {
        JPanel [] panels = new JPanel[4];
        ColumnarTranspositionCipher ctc = new ColumnarTranspositionCipher();
        JPanel [] commonPanels = getPanelsCommonForCaesarAndROT13();

        JPanel panelKey = getPanelForKey("Key: ", "ZEBRAS");
        JTextField keyIn = (JTextField) panelKey.getComponent(1);
        JCheckBox encryption = (JCheckBox) commonPanels[1].getComponent(0);
        JTextField txtTextIn = (JTextField) commonPanels[0].getComponent(0);
        JLabel lblResult = (JLabel) commonPanels[0].getComponent(1);

        JButton btnRunCipher = new JButton("run");

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        panelButton.add(btnRunCipher);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            String result;

            ctc.setKey(keyIn.getText());

            if (encryption.isSelected()){
                result = ctc.encode(textToProcess);
            } else {
                result = ctc.decode(textToProcess);
            }

            lblResult.setText("Result: " + result);
        });

        panels[0] = commonPanels[0];
        panels[1] = commonPanels[1];
        panels[2] = panelKey;
        panels[3] = panelButton;

        return panels;
    }
}
