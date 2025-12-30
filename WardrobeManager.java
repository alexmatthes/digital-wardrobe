import java.awt.BorderLayout;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Class for managing a wardrobe.
 * Responsible for taking a clothing item and exporting
 * it to a CSV file in proper format.
 *
 * @author Alex Matthes
 */
public class WardrobeManager {
  /*
    Valid options for each attribute.
   */
  static String[] CATEGORY_OPTIONS = {"Tops", "Bottoms", "Footwear", "Outerwear",
                                      "Accessories", "Undergarments", "Suiting"};
  static String[] PATTERN_OPTIONS = {"Solid", "Stripe", "Plaid", "Check", "Floral",
                                        "Graphic", "Micro-print"};
  static String[] MATERIAL_OPTIONS = {"Denim", "Wool", "Linen", "Cotton", "Leather",
                                      "Suede", "Silk", "Synthetic", "Other"};
  static String[] FIT_OPTIONS = {"Skinny", "Slim", "Regular", "Relaxed",
                                    "Oversized", "Tapered"};
  static String[] FORMALITY_OPTIONS = {"Loungewear", "Active", "Casual", "Smart Casual",
                                          "Business Professional", "Formal"};
  static String[] SEASON_OPTIONS = {"SS (Spring/Summer)", "FW (Fall/Winter)", "All-Year"};
  static String[] CONDITION_OPTIONS = {"New", "Excellent", "Good", "Distressed", "Worn"};
  static String[] STATUS_OPTIONS = {"Active", "Storage", "At Cleaner", "Needs Repair"};

  static ArrayList<ClothingItem> sessionList = new ArrayList<>();

  /*
    Constants for frame setup.
   */
  static final int FRAME_WIDTH = 500;
  static final int FRAME_HEIGHT = 700;

  static final int PANEL_ROWS = 0;
  static final int PANEL_COLS = 2;
  static final int PANEL_GAP = 10;

  static final int BORDER_PADDING = 20;

  static final int TEXT_AREA_ROWS = 10;
  static final int TEXT_AREA_COLS = 40;

  /**
   * Responsible for taking a clothing item and exporting
   * it to a CSV file in proper format.
   *
   * @param args arguments.
   */
  static void main(String[] args) {
    // Set up the Frame
    JFrame frame = new JFrame("Digital Wardrobe Inventory");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

    // Set up the Panel
    JPanel panel = new JPanel(new java.awt.GridLayout(PANEL_ROWS, PANEL_COLS,
            PANEL_GAP, PANEL_GAP));
    panel.setBorder(BorderFactory.createEmptyBorder(BORDER_PADDING, BORDER_PADDING,
            BORDER_PADDING, BORDER_PADDING));

    // Create Inputs
    // -- Identity --
    panel.add(new JLabel("Category:"));
    JComboBox<String> catBox = new JComboBox<>(CATEGORY_OPTIONS);
    panel.add(catBox);

    panel.add(new JLabel("Sub-Category (e.g. Chinos):"));
    JTextField subCatField = new JTextField();
    panel.add(subCatField);

    panel.add(new JLabel("Brand:"));
    JTextField brandField = new JTextField();
    panel.add(brandField);

    panel.add(new JLabel("Item Name:"));
    JTextField nameField = new JTextField();
    panel.add(nameField);

    // -- Visual --
    panel.add(new JLabel("Primary Color:"));
    JTextField color1Field = new JTextField();
    panel.add(color1Field);

    panel.add(new JLabel("Secondary Color:"));
    JTextField color2Field = new JTextField();
    panel.add(color2Field);

    panel.add(new JLabel("Pattern:"));
    JComboBox<String> patternBox = new JComboBox<>(PATTERN_OPTIONS);
    panel.add(patternBox);

    panel.add(new JLabel("Material:"));
    JComboBox<String> matBox = new JComboBox<>(MATERIAL_OPTIONS);
    panel.add(matBox);

    // -- Functional --
    panel.add(new JLabel("Fit Profile:"));
    JComboBox<String> fitBox = new JComboBox<>(FIT_OPTIONS);
    panel.add(fitBox);

    panel.add(new JLabel("Formality:"));
    JComboBox<String> formalityBox = new JComboBox<>(FORMALITY_OPTIONS);
    panel.add(formalityBox);

    panel.add(new JLabel("Seasonality:"));
    JComboBox<String> seasonBox = new JComboBox<>(SEASON_OPTIONS);
    panel.add(seasonBox);

    // -- Status --
    panel.add(new JLabel("Condition:"));
    JComboBox<String> conditionBox = new JComboBox<>(CONDITION_OPTIONS);
    panel.add(conditionBox);

    panel.add(new JLabel("Status:"));
    JComboBox<String> statusBox = new JComboBox<>(STATUS_OPTIONS);
    panel.add(statusBox);

    panel.add(new JLabel("Quantity:"));
    // SpinnerNumberModel(default, min, max, step)
    JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
    panel.add(quantitySpinner);

    // Add Buttons
    JButton addButton = new JButton("Add Item");
    JButton saveButton = new JButton("Save to CSV");

    panel.add(addButton);
    panel.add(saveButton);

    // Create a text area that is not editable (display only)
    JTextArea logArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLS);
    logArea.setEditable(false);
    logArea.setText("--- Session Log ---\n");

    // Wrap it in a ScrollPane (so it scrolls if you add 50 items)
    JScrollPane scrollPane = new JScrollPane(logArea);

    // Finalize
    frame.setLayout(new BorderLayout());

    // Put the form (panel) in the Center
    frame.add(panel, BorderLayout.CENTER);

    // Put the log (scrollPane) at the Bottom (South)
    frame.add(scrollPane, BorderLayout.SOUTH);

    frame.setVisible(true);

    /*
      When the add button gets pressed:
      grab the data from the UI, create an Object, then add it into memory.
     */
    addButton.addActionListener(e -> {
      // Gather Data
      String cat = (String) catBox.getSelectedItem();
      String subCat = subCatField.getText();
      String brand = brandField.getText();
      String name = nameField.getText();
      String color1 = color1Field.getText();
      String color2 = color2Field.getText();
      String pattern = (String) patternBox.getSelectedItem();
      String material = (String) matBox.getSelectedItem();
      String fit = (String) fitBox.getSelectedItem();
      String formality = (String) formalityBox.getSelectedItem();
      String season = (String) seasonBox.getSelectedItem();
      String condition = (String) conditionBox.getSelectedItem();
      String status = (String) statusBox.getSelectedItem();
      int quantity = (Integer) quantitySpinner.getValue();

      // Create Object
      ClothingItem newItem = new ClothingItem(
              cat, subCat, brand, name,
              color1, color2, pattern, material,
              fit, formality, season, condition, status,
              quantity
      );

      // Add to Memory
      sessionList.add(newItem);

      // Update the Log
      logArea.append("Added: " + newItem.toString() + "\n");

      // Clear the Text Fields (Reset for next item)
      subCatField.setText("");
      brandField.setText("");
      nameField.setText("");
      color1Field.setText("");
      color2Field.setText("");

      patternBox.setSelectedIndex(0);
      matBox.setSelectedIndex(0);
      fitBox.setSelectedIndex(0);
      formalityBox.setSelectedIndex(0);
      seasonBox.setSelectedIndex(0);
      conditionBox.setSelectedIndex(0);
      statusBox.setSelectedIndex(0);

      quantitySpinner.setValue(1);
    });

    /*
      When the save button gets pressed, take everything in sessionList, and add it into a CSV file.
     */
    saveButton.addActionListener(e -> {
      String filename = "wardrobe_inventory.csv";
      File newFile = new File(filename);
      boolean isNewFile = !newFile.exists();

      try (FileWriter fileWriter = new FileWriter(newFile, true);
           PrintWriter writer = new PrintWriter(fileWriter)) {

        // Write Header (Only if file didn't exist)
        if (isNewFile) {
          writer.println("Category,Sub-Category,Brand,Name,Primary Color,"
                  + "Secondary Color,Pattern,Material,Fit,Formality,"
                  + "Season,Condition,Status,Quantity");
        }

        // Write Data
        for (ClothingItem item : sessionList) {
          writer.println(item.toCsv());
        }

        // Cleanup
        writer.flush(); // Forces data to disk
        sessionList.clear(); // Empty the memory so we don't double-save
        logArea.setText("--- Success! Inventory saved to "
                + filename + "---\n"); // Reset the visual log

      } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage());
      }
    });
  }
}