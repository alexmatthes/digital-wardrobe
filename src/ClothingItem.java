/**
 * This class is the model for a singular clothing item. It contains data about the clothing item.
 *
 * @author Alex Matthes
 */
public class ClothingItem {
  /*
      Private Variables for clothing item attributes.
     */
  private String category;
  private String subCategory;
  private String brand;
  private String itemName;
  private String primaryColor;
  private String secondaryColor;
  private String pattern;
  private String material;
  private String fitProfile;
  private String formality;
  private String seasonality;
  private String condition;
  private String status;
  private int quantity;

  /**
   * Constructor for a clothing item.
   *
   * @param category High-level grouping used for primary sorting.
   * @param subCategory  Specific taxonomy defining the item's function.
   * @param brand The manufacturer.
   * @param itemName A concise, human-readable identifier.
   * @param primaryColor The dominant hue.
   * @param secondaryColor Accent colors present in the item.
   * @param pattern The visual design structure.
   * @param material The tactile quality of the fabric.
   * @param fitProfile Describes the silhouette.
   * @param formality A constraint variable used to prevent context errors.
   * @param seasonality Time-based filtering tags.
   * @param condition The physical state of the item.
   * @param status Availability flag.
   */
  public ClothingItem(String category, String subCategory, String brand,
                      String itemName, String primaryColor, String secondaryColor,
                      String pattern, String material, String fitProfile,
                      String formality, String seasonality, String condition,
                      String status, int quantity) {

    this.category = category;
    this.subCategory = subCategory;
    this.brand = brand;
    this.itemName = itemName;
    this.primaryColor = primaryColor;
    this.secondaryColor = secondaryColor;
    this.pattern = pattern;
    this.material = material;
    this.fitProfile = fitProfile;
    this.formality = formality;
    this.seasonality = seasonality;
    this.condition = condition;
    this.status = status;
    this.quantity = quantity;
  }

  public String getCategory() {
    return category;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public String getBrand() {
    return brand;
  }

  public String getItemName() {
    return itemName;
  }

  public String getPrimaryColor() {
    return primaryColor;
  }

  public String getSecondaryColor() {
    return secondaryColor;
  }

  public String getPattern() {
    return pattern;
  }

  public String getMaterial() {
    return material;
  }

  public String getFitProfile() {
    return fitProfile;
  }

  public String getFormality() {
    return formality;
  }

  public String getSeasonality() {
    return seasonality;
  }

  public String getCondition() {
    return condition;
  }

  public String getStatus() {
    return status;
  }

  /**
   * Helper method to format attribute string into CSV format string.
   *
   * @param attribute the attribute to be formatted.
   *
   * @return the formatted attribute.
   */
  private String attributeFormat(String attribute) {
    return attribute.replace(",", " ");
  }

  /**
   * Method for printing clothing item in CSV format.
   *
   * @return clothing item attributes in CSV format.
   */
  public String toCsv() {
    return attributeFormat(category) + "," + attributeFormat(subCategory) + ","
            + attributeFormat(brand) + "," + attributeFormat(itemName) + ","
            + attributeFormat(primaryColor) + "," + attributeFormat(secondaryColor) + ","
            + attributeFormat(pattern) + "," + attributeFormat(material) + ","
            + attributeFormat(fitProfile) + "," + attributeFormat(formality) + ","
            + attributeFormat(seasonality) + "," + attributeFormat(condition) + ","
            + attributeFormat(status) + "," + quantity;
  }

  @Override
  public String toString() {
    return quantity + "x " + itemName + " (" + brand + ", " + primaryColor + ")";
  }
}