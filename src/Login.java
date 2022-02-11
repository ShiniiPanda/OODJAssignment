import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Login {
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField nameInput;
    private JTextField passInput;
    private JLabel userLabel;
    private JLabel passLabel;
    private JPanel titlePanel;
    private JPanel loginPanel;
    private JPanel inputPanel;

    public Login() {
        JFrame frame = new JFrame();
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);
        frame.getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(e -> {
            String user = nameInput.getText(),
                    pass = passInput.getText();
            LoginResponse res = User.validateLogin(user, pass);
            switch (res.result) {
                case 0:
                    if (res.user.getRole().equals("Denied")) {
                        JOptionPane.showMessageDialog(null, "This account has been denied from accessing the system.",
                                "Login Failed", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Successfully Logged In", "Login Success", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        relayUser(res.user);
                    }
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Unable to login, invalid ID.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Unable to login, Password Incorrect.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    break;
            }
            logAttempt(res);
        });
    }

    private static void logAttempt(LoginResponse res) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime date = LocalDateTime.now();
        String logDate = "[" + formatter.format(date) + "] ";
        try {
            FileWriter fileWriter = new FileWriter("./TextFiles/AuditLogs.txt", true);
            BufferedWriter file = new BufferedWriter(fileWriter);
            switch (res.result) {
                case (0):
                    file.write(logDate + "Successful Login Attempt by " + res.user.getName() + "\n");
                    break;
                case (1):
                    file.write(logDate + "Failed Login Attempt | Error: Username not found.\n");
                    break;
                case (2):
                    file.write(logDate + "Failed Login Attempt | Error: Password not found.\n");
                    break;
            }
            file.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void relayUser(User user) {
        String role = user.getRole();
        if (role.equals("Technician")) {
            new TechnicianMenu(new Technician(user));
        } else if (role.equals("Manager")) {
            new ManagerMenu(new Manager(user));
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-15000805)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.setBackground(new Color(-4605511));
        titlePanel.setPreferredSize(new Dimension(0, 80));
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-1));
        Font label1Font = this.$$$getFont$$$("Serif", Font.BOLD, 36, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setHorizontalAlignment(0);
        label1.setText("System Login");
        titlePanel.add(label1, BorderLayout.CENTER);
        loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        loginPanel.setBackground(new Color(-1));
        loginPanel.setPreferredSize(new Dimension(0, 80));
        mainPanel.add(loginPanel, BorderLayout.SOUTH);
        loginButton = new JButton();
        loginButton.setBackground(new Color(-1));
        loginButton.setBorderPainted(true);
        loginButton.setFocusPainted(false);
        Font loginButtonFont = this.$$$getFont$$$("Arial", Font.BOLD, 14, loginButton.getFont());
        if (loginButtonFont != null) loginButton.setFont(loginButtonFont);
        loginButton.setForeground(new Color(-16777216));
        loginButton.setPreferredSize(new Dimension(140, 40));
        loginButton.setText("Login");
        loginButton.setVisible(true);
        loginPanel.add(loginButton);
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(new Color(-1));
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        userLabel = new JLabel();
        Font userLabelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, userLabel.getFont());
        if (userLabelFont != null) userLabel.setFont(userLabelFont);
        userLabel.setForeground(new Color(-16777216));
        userLabel.setText("Username");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 20, 30);
        inputPanel.add(userLabel, gbc);
        passLabel = new JLabel();
        Font passLabelFont = this.$$$getFont$$$("Arial", Font.BOLD, 18, passLabel.getFont());
        if (passLabelFont != null) passLabel.setFont(passLabelFont);
        passLabel.setForeground(new Color(-16777216));
        passLabel.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(passLabel, gbc);
        nameInput = new JTextField();
        nameInput.setMinimumSize(new Dimension(25, 14));
        nameInput.setPreferredSize(new Dimension(150, 30));
        nameInput.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);
        inputPanel.add(nameInput, gbc);
        passInput = new JTextField();
        passInput.setMinimumSize(new Dimension(25, 14));
        passInput.setPreferredSize(new Dimension(150, 30));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(passInput, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
