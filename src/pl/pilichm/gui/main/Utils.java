package pl.pilichm.gui.main;

import pl.pilichm.ciphers.substitution.CaesarCipher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Utils {
    public static JPanel [] getPanelsForCaesar(){
        JPanel [] panels = new JPanel[4];

        CaesarCipher cc = new CaesarCipher();

        JTextField txtTextIn = new JTextField("Text for processing");
        JTextField offsetIn = new JTextField("1");
        JLabel lblResult = new JLabel("Result: ");
        JLabel labelOffset = new JLabel("Offset: ");
        JButton btnRunCipher = new JButton("run");

        JCheckBox encryption = new JCheckBox("Encryption");
        JCheckBox decryption = new JCheckBox("Decryption");
        encryption.setSelected(true);

        offsetIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        txtTextIn.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        lblResult.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelEncryptionDecryption = new JPanel();
        panelEncryptionDecryption.setLayout(new BoxLayout(panelEncryptionDecryption, BoxLayout.X_AXIS));
        panelEncryptionDecryption.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelOffset = new JPanel();
        panelOffset.setLayout(new BoxLayout(panelOffset, BoxLayout.X_AXIS));
        panelOffset.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelTextInOut = new JPanel();
        panelTextInOut.setLayout(new BoxLayout(panelTextInOut, BoxLayout.X_AXIS));
        panelTextInOut.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));
        panelButton.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        panelButton.add(btnRunCipher);

        panelEncryptionDecryption.add(encryption);
        panelEncryptionDecryption.add(decryption);

        encryption.addItemListener(e -> decryption.setSelected(!encryption.isSelected()));
        decryption.addItemListener(e -> encryption.setSelected(!decryption.isSelected()));

        panelOffset.add(labelOffset);
        panelOffset.add(offsetIn);

        panelTextInOut.add(txtTextIn);
        panelTextInOut.add(lblResult);

        btnRunCipher.addActionListener(e -> {
            String textToProcess = txtTextIn.getText();
            int currentOffset = 1;

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

        panels[0] = panelTextInOut;
        panels[1] = panelEncryptionDecryption;
        panels[2] = panelOffset;
        panels[3] = panelButton;

        return panels;
    }
}
