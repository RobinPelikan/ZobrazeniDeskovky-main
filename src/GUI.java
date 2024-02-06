import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {
    private JCheckBox checkBox;
    private JRadioButton radBtn1;
    private JRadioButton radBtn2;
    private JRadioButton radBtn3;
    private JTextField textField;
    private JPanel mainPanel;
    private JButton btnPrevious;
    private JButton btnSave;
    private JButton btnNext;

    private int indexAktualniDeskovky = 0;
    private final SpravceDeskovek spravceDeskovek;

    public GUI(SpravceDeskovek spravceDeskovek) {
        this.spravceDeskovek = spravceDeskovek;
        initComponents();
        updateGUI();
        btnNext.addActionListener(e -> dalsiDeskovka());
        btnPrevious.addActionListener(e -> predchoziDeskovka());
        btnSave.addActionListener(e -> ulozDeskovku());
    }

    //metoda pro nastaveni toho jak bude okno vypadat + chovani okna pri zavreni
    private void initComponents() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Deskové hry");
        //pack();2
        setSize(450, 240);
        btnNext.setBackground(Color.lightGray);
        btnPrevious.setBackground(Color.lightGray);
        btnSave.setBackground(Color.green);
        textField.setEditable(true);
        //mainPanel.setBackground(Color.cyan); to je hnus ta tyrkysova
    }

    //metoda pro ulozeni aktualni deskovky + informaci o ni (nebo pri uprave deskovky)
    private void ulozDeskovku() {
        String nazevHry = textField.getText();
        boolean zakoupeno = checkBox.isSelected();
        int oblibenost = 1;
        if (radBtn2.isSelected()) {
            oblibenost = 2;
        } else if (radBtn3.isSelected()) {
            oblibenost = 3;
        }
        Deskovka aktualniDeskovka = spravceDeskovek.getDeskovka(indexAktualniDeskovky);
        aktualniDeskovka.setNazevHry(nazevHry);
        aktualniDeskovka.setZakoupeno(zakoupeno);
        aktualniDeskovka.setOblibenost(oblibenost);
    }

    //metoda pro nacteni predchozi deskovky v seznamu
    private void predchoziDeskovka() {
        btnNext.setEnabled(true);
        if (indexAktualniDeskovky > 0) {
            indexAktualniDeskovky--;
            updateGUI();
        }else{
            btnPrevious.setEnabled(false);
            System.err.println("Jste na začátku seznamu");
        }
    }

    //metoda pro nacteni dalsi deskovky v seznamu
    private void dalsiDeskovka() {
        btnPrevious.setEnabled(true);
        if (indexAktualniDeskovky < spravceDeskovek.getPocetDeskovek() - 1) {
            indexAktualniDeskovky++;
            updateGUI();
        }else {
            btnNext.setEnabled(true);
            System.err.println("Vytvořena položka pro novou deskovou hru " +indexAktualniDeskovky);
            spravceDeskovek.pridejDeskovku(new Deskovka("název nové deskovky", false, 1));
            indexAktualniDeskovky++;
            updateGUI();
        }
    }

    //metoda pro znovu-načtení GUI
    private void updateGUI() {
        if (spravceDeskovek.getPocetDeskovek() == 0) {
            textField.setText("");
            checkBox.setSelected(false);
            radBtn1.setSelected(true);
        }else {
                Deskovka aktualniDeskovka = spravceDeskovek.getDeskovka(indexAktualniDeskovky);
                textField.setText(aktualniDeskovka.getNazevHry());
                checkBox.setSelected(aktualniDeskovka.isZakoupeno());
                switch (aktualniDeskovka.getOblibenost()) {
                    case 1:
                        radBtn1.setSelected(true);
                        break;
                    case 2:
                        radBtn2.setSelected(true);
                        break;
                    case 3:
                        radBtn3.setSelected(true);
                        break;
                }
        }
    }
}