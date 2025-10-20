package data;

public class Store {
  /**
   * Saves a complaint to the database.
   *
   * @param username - the username of the user who submitted the complaint
   * @param subject - the subject of the complaint
   * @param description - the description of the complaint
   * @param priority - the priority of the complaint
   * @param ID - the ID of the complaint
   */
  public static void saveComplaint(
      String username, String subject, String description, String priority, int ID) {
    // TODO: Store the data to the database
    return;
  }

  /**
   * Updates the priority of a complaint with the given ID in the database.
   *
   * @param ID the ID of the complaint to be updated
   * @param priority the new priority of the complaint
   */
  public static void updatePriority(int ID, String priority) {
    // TODO: Update the priority of the complaint with the given ID in the database
    return;
  }
}
