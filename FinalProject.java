
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinalProject {

    JFrame frame = new JFrame("My First Project by Mushtaque Ali Section H BS-CS-II");
    Container c = frame.getContentPane();

    int i = 1;    // Needed for serial number

    CardLayout cardLayout;
    JPanel cardPanel;

    // For saving data
    String summary = "", Sseats, Sname, Snumber, Stype, Sdate, Stime, Spreferences, Ssource, Sdes, SAddress;

    JTextField seatField, nameField, numberField, areaField, dateField;
    JComboBox<String> sourceField, desField, timeField;
    JRadioButton single, Return;
    JCheckBox preSeatSelection, preMeal, preExtraLuggage, preWifi, jb;

    FinalProject() {

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Color.LIGHT_GRAY);

        // Create different pages
        cardPanel.add(HomePage(), "Home Page");
        cardPanel.add(BookingPage(), "Booking Page");
        cardPanel.add(SubmittedPage(), "Submitted Page");

        c.add(cardPanel);

        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    private JPanel HomePage() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(Color.WHITE);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GREEN);
        JLabel headerLabel = new JLabel("Welcome to the Bus Ticket Booking System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerPanel.add(headerLabel);

        // Info Panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.WHITE);
        JTextArea infoArea = new JTextArea();
        infoArea.setBackground(Color.WHITE);
        infoArea.setText("Thank you for choosing to travel with us!\n\n"
                + "Please click the 'New Booking' button below to start the booking process.");
        infoArea.setFont(new Font("Arial", Font.PLAIN, 18));
        infoArea.setEditable(false);
        infoArea.setWrapStyleWord(true);
        infoPanel.add(infoArea, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        JButton newBookingButton = new JButton("New Booking");
        newBookingButton.setPreferredSize(new Dimension(200, 50));
        newBookingButton.setBackground(Color.GREEN); // Button color
        newBookingButton.setForeground(Color.WHITE); // Text color
        newBookingButton.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(newBookingButton);

        JButton checkRecordButton = new JButton("Check Records");
        checkRecordButton.setPreferredSize(new Dimension(200, 50));
        checkRecordButton.setBackground(Color.BLUE);
        checkRecordButton.setForeground(Color.WHITE);
        checkRecordButton.setFont(new Font("Arial", Font.BOLD, 20));
        buttonPanel.add(checkRecordButton);

        newBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Booking Page");
            }
        });

        checkRecordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRecords();
            }
        });

        // Arrange components using GridBagLayout
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20); // Padding

        // Add components to content panel
        contentPanel.add(headerPanel, gbc);
        gbc.gridy++;
        contentPanel.add(infoPanel, gbc);
        gbc.gridy++;
        contentPanel.add(buttonPanel, gbc);

        // Add content panel to home panel
        homePanel.add(contentPanel, BorderLayout.CENTER);

        return homePanel;
    }

    private void showRecords() {

        JPanel recordsPanel = new JPanel(new BorderLayout());
        recordsPanel.setBackground(Color.WHITE);

        // Create a JTextArea to display records
        JTextArea recordArea = new JTextArea(summary);
        recordArea.setEditable(false);
        recordArea.setWrapStyleWord(true);

        // Create a JScrollPane for the record area
        JScrollPane scrollPane = new JScrollPane(recordArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        recordsPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.BLACK);

        JButton deleteButton = new JButton("Delete Records");
        JButton searchButton = new JButton("Search Records");
        JButton updateButton = new JButton("Update Records");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                summary = ""; // Clear the summary
                i = 1;
                recordArea.setText(summary); // Clear the record area
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog(frame, "Enter a unique term:");
                if (searchTerm != null) {
                    if (summary.contains(searchTerm)) {
                        JOptionPane.showMessageDialog(frame, "Record found!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Record not found.");
                    }
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // not added yet
                JOptionPane.showMessageDialog(frame, "Update Records functionality not implemented yet.");
            }
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);

        recordsPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show the records panel in a dialog
        JOptionPane.showOptionDialog(
                frame, recordsPanel,
                "Records",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, // No custom icons
                new Object[]{}, // No custom options
                null // No initial value
        );
    }

    private JPanel BookingPage() {

        JLabel topic = new JLabel("Bus Ticket Booking System");
        topic.setHorizontalAlignment(SwingConstants.CENTER);
        topic.setFont(new Font("Arial", Font.BOLD, 30));
        c.add(topic, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 40, 8, 5); // Add padding between components
        gbc.anchor = GridBagConstraints.LINE_START; // Align components to the left

        JLabel seats = new JLabel("No: of Seats ");
        seats.setFont(new Font("Arial", Font.PLAIN, 20));
        seats.setForeground(Color.WHITE);   // Font Colour
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(seats, gbc);

        seatField = new JTextField(20);
        seatField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(seatField, gbc);

        JLabel name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(name, gbc);

        nameField = new JTextField(20);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        JLabel number = new JLabel("Contact # ");
        number.setFont(new Font("Arial", Font.PLAIN, 20));
        number.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(number, gbc);

        numberField = new JTextField(20);
        numberField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(numberField, gbc);

        JLabel source = new JLabel("Source");
        source.setForeground(Color.WHITE);
        source.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(source, gbc);

        String p[] = {"   Sukkur", "   Karachi", "   Lahore", "   Quetta", "   Multan", "   Islamabad", "   Peshawar"};
        sourceField = new JComboBox<>(p);
        sourceField.setPreferredSize(new Dimension(325, 30));
        sourceField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(sourceField, gbc);

        JLabel destination = new JLabel("Destination");
        destination.setForeground(Color.WHITE);
        destination.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(destination, gbc);

        desField = new JComboBox<>(p);
        desField.setPreferredSize(new Dimension(325, 30));
        desField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(desField, gbc);

        JLabel date = new JLabel("Booking Date ");
        date.setFont(new Font("Arial", Font.PLAIN, 20));
        date.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(date, gbc);

        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(dateField, gbc);

        JLabel time = new JLabel("Time");
        time.setFont(new Font("Arial", Font.PLAIN, 20));
        time.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(time, gbc);

        String t[] = {"   12:00 p.m", "   02:00 p.m", "   06:00 p.m", "   08:00 p.m", "   10:30 p.m", "   01:00 a.m"};
        timeField = new JComboBox<>(t);
        timeField.setFont(new Font("Arial", Font.PLAIN, 20));
        timeField.setPreferredSize(new Dimension(325, 30));
        gbc.gridx = 1;
        formPanel.add(timeField, gbc);

        JLabel type = new JLabel("Type");
        type.setForeground(Color.WHITE);
        type.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(type, gbc);

        single = new JRadioButton("Single");
        single.setPreferredSize(new Dimension(157, 30));
        single.setFont(new Font("Arial", Font.PLAIN, 20));
        Return = new JRadioButton("Return");
        Return.setPreferredSize(new Dimension(157, 30));
        Return.setFont(new Font("Arial", Font.PLAIN, 20));

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(single);
        typeGroup.add(Return);

        JPanel typePanel = new JPanel();
        typePanel.add(single);
        typePanel.add(Return);
        gbc.gridx = 1;
        formPanel.add(typePanel, gbc);

        JLabel preferences = new JLabel("Preferences");
        preferences.setFont(new Font("Arial", Font.PLAIN, 20));
        preferences.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(preferences, gbc);

        preSeatSelection = new JCheckBox("Seat Selection");
        preSeatSelection.setFont(new Font("Arial", Font.PLAIN, 20));
        preMeal = new JCheckBox("Meal");
        preMeal.setFont(new Font("Arial", Font.PLAIN, 20));
        preExtraLuggage = new JCheckBox("Extra Luggage");
        preExtraLuggage.setFont(new Font("Arial", Font.PLAIN, 20));
        preWifi = new JCheckBox("Wifi Access");
        preWifi.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel prePanel = new JPanel(new GridLayout(2, 2));
        prePanel.setPreferredSize(new Dimension(325, 40));
        prePanel.add(preSeatSelection);
        prePanel.add(preMeal);
        prePanel.add(preExtraLuggage);
        prePanel.add(preWifi);
        gbc.gridx = 1;
        formPanel.add(prePanel, gbc);

        JLabel address = new JLabel("Address");
        address.setForeground(Color.WHITE);
        address.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(address, gbc);

        areaField = new JTextField(20);
        areaField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        formPanel.add(areaField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(new JLabel(" "), gbc); // Empty space for separation

        jb = new JCheckBox("Accept terms and conditions");
        jb.setFont(new Font("Arial", Font.PLAIN, 20));
        jb.setBackground(Color.lightGray);
        jb.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy++;
        formPanel.add(jb, gbc);

        gbc.gridx = 1;
        gbc.gridy++;

        JButton subButton = new JButton("Submit");
        subButton.setFont(new Font("Arial", Font.PLAIN, 20));
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jb.isSelected()) {
                    cardLayout.show(cardPanel, "Submitted Page");
                    Summary();
                    CancelData();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please Accept Terms and Conditions");
                }
            }
        });

        JButton canButton = new JButton("Cancel");
        canButton.setFont(new Font("Arial", Font.PLAIN, 20));
        canButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CancelData();
                cardLayout.show(cardPanel, "Home Page");
            }
        });

        JPanel subPanel = new JPanel();  // button panel

        subPanel.add(subButton);
        subPanel.add(new JLabel("  "));  // space between submit and cancel Button
        subPanel.add(canButton);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(subPanel, gbc);

        c.add(formPanel, BorderLayout.CENTER);

        c.setBackground(Color.RED);
        formPanel.setBackground(Color.GRAY);
        subPanel.setBackground(Color.GRAY);

        typePanel.setBackground(Color.GRAY);
        subButton.setBackground(Color.GREEN);
        canButton.setBackground(Color.GREEN);

        return formPanel;
    }

    private JPanel SubmittedPage() {

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setBackground(Color.LIGHT_GRAY);
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel l = new JLabel("Thank You!");
        l.setHorizontalAlignment(l.CENTER);
        l.setFont(new Font("Arial", Font.BOLD, 40));

        JLabel l1 = new JLabel("Your seats have been booked.");
        l1.setHorizontalAlignment(l.CENTER);
        l1.setFont(new Font("Arial", Font.PLAIN, 25));

        p.add(l, gbc);
        gbc.gridy++;
        p.add(l1, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        p.add(new JLabel(" "), gbc);

        gbc.gridy++;
        p.add(new JLabel(" "), gbc);

        JPanel jp = new JPanel();
        JButton newBooking = new JButton("New Booking");
        newBooking.setPreferredSize(new Dimension(150, 40));
        newBooking.setBackground((Color.GREEN));
        gbc.gridy++;
        p.add(jp, gbc);

        JButton home = new JButton("Home Page");
        home.setPreferredSize(new Dimension(150, 40));
        home.setBackground((Color.GREEN));
        jp.add(newBooking);
        jp.add(home);

        newBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Booking Page");
            }
        });

        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Home Page");
            }
        });

        subPanel.add(p, BorderLayout.CENTER);
        return subPanel;
    }

    public void CancelData() {

        seatField.setText(null);
        nameField.setText(null);
        numberField.setText(null);
        areaField.setText(null);
        dateField.setText(null);

        sourceField.setSelectedIndex(-1);
        desField.setSelectedIndex(-1);
        timeField.setSelectedIndex(-1);
        single.setSelected(false);
        Return.setSelected(false);
        preSeatSelection.setSelected(false);
        preMeal.setSelected(false);
        preExtraLuggage.setSelected(false);
        preWifi.setSelected(false);
        jb.setSelected(false);

    }

    public void Summary() {
        Sseats = seatField.getText();
        Sname = nameField.getText();
        Snumber = numberField.getText();
        Sdate = dateField.getText();
        Ssource = (String) sourceField.getSelectedItem();
        Sdes = (String) desField.getSelectedItem();
        Stime = (String) timeField.getSelectedItem();

        SAddress = areaField.getText();

        if (single.isSelected()) {
            Stype = "Single";
        } else if (Return.isSelected()) {
            Stype = "Return";
        }

        Spreferences = "";
        if (preSeatSelection.isSelected()) {
            Spreferences += "Seat Selection Choice ";
        }
        if (preMeal.isSelected()) {
            Spreferences += "Meal ";
        }
        if (preExtraLuggage.isSelected()) {
            Spreferences += "Extra Luggage ";
        }
        if (preWifi.isSelected()) {
            Spreferences += "Wifi";
        }

        String record = i + "  No: of Seats: " + Sseats + "\n   Name: " + Sname + "\n   Contact #: " + Snumber + "\n   Type: " + Stype
                + "\n   Source: " + Ssource + "\n   Destination: " + Sdes + "\n   Date: " + Sdate + "\n   Time: " + Stime
                + "\n   Preferences: " + Spreferences + "\n   Address: " + SAddress + "\n\n";

        i++;       // increase serial number for next record

        summary += record;
    }

    public static void main(String[] args) {
        FinalProject a = new FinalProject();
    }
}
