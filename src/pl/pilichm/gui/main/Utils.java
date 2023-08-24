package pl.pilichm.gui.main;

import pl.pilichm.ciphers.AbstractCipher;
import pl.pilichm.ciphers.substitution.AutoKeyCipher;
import pl.pilichm.ciphers.substitution.CaesarCipher;
import pl.pilichm.ciphers.substitution.ROT13Cipher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Utils {

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
            int currentOffset;

            try {
                currentOffset = Integer.parseInt(offsetIn.getText());
            } catch (Exception exc){
                System.out.println("Invalid offset value!");
                offsetIn.setForeground(Color.RED);
                labelOffset.setForeground(Color.RED);
                return;
            }

            String result;
            cc.setOffset(currentOffset);


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
}
