/**
 * Hunter Class<br /><br />
 * This class represents the treasure hunter character (the player) in the Treasure Hunt game.
 * This code has been adapted from Ivan Turner's original program -- thank you Mr. Turner!
 */
import java.util.ArrayList;

public class Hunter {
    //instance variables
    private String hunterName;
    private ArrayList<String> kit;
    private int gold;

    //Constructor
    /**
     * The base constructor of a Hunter assigns the name to the hunter and an empty kit.
     *
     * @param name The hunter's name.
     */
    public Hunter(String hunterName, int startingGold) {
        this.hunterName = hunterName;
        kit = new ArrayList<String>();
        gold = startingGold;
    }

    //Accessors
    public String getHunterName() {
        return hunterName;
    }

    public ArrayList<String> getKit() {
        return kit;
    }

    public int getGold() {
        return gold;
    }

    public void changeGold(int modifier) {
        gold += modifier;
        if (gold < 0) {
            gold = 0;
        }
    }

    /**
     * Buys an item from a shop.
     *
     * @param item The item the hunter is buying.
     * @param costOfItem the cost of the item
     * @return true if the item is successfully bought.
     */
    public boolean buyItem(String item, int costOfItem) {
        if (costOfItem == 0 || gold < costOfItem || hasItemInKit(item)) {
            return false;
        }

        gold -= costOfItem;
        addItem(item);
        return true;
    }

    /**
     * The Hunter is selling an item to a shop for gold.<p>
     * This method checks to make sure that the seller has the item and that the seller is getting more than 0 gold.
     *
     * @param item The item being sold.
     * @param buyBackPrice the amount of gold earned from selling the item
     * @return true if the item was successfully sold.
     */
    public boolean sellItem(String item, int buyBackPrice) {
        if (buyBackPrice <= 0 || !hasItemInKit(item)) {
            return false;
        }

        gold += buyBackPrice;
        removeItemFromKit(item);
        return true;
    }

    /**
     * Removes an item from the kit.
     *
     * @param item The item to be removed.
     */
    public void removeItemFromKit(String item) {
        int itmIdx = findItemInKit(item);

        // if item is found
        if (itmIdx >= 0) {
            kit.remove(itmIdx);
        }
    }

    /**
     * Checks to make sure that the item is not already in the kit.
     * If not, it adds an item to the end of the String representing the hunter's kit.<br /><br />
     * A KIT_DELIMITER character is added to the end of the of String.
     *
     * @param item The item to be added to the kit.
     * @returns true if the item is not in the kit and has been added.
     */
    private boolean addItem(String item) {
        if (!hasItemInKit(item)) {
            kit.add(item);
            return true;
        }

        return false;
    }

    /**
     * Searches the kit String for a specified item.
     *
     * @param item The search item
     * @return true if the item is found.
     */
    public boolean hasItemInKit(String item) {
        for (String tmpItem : kit) {
            if (item.equals(tmpItem)) {
                // early return
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a printable representation of the inventory, which
     * is a list of the items in kit, with the KIT_DELIMITER replaced with a space
     *
     * @return The printable String representation of the inventory
     */
    public String getInventory() {
        String printableKit = "";
        String space = " ";

        for (String item : kit) {
            printableKit += item + space;
        }

        return printableKit;
    }

    /**
     * @return A string representation of the hunter.
     */
    public String toString() {
        String str = hunterName + " has " + gold + " gold";
        if (!kitIsEmpty()) {
            str += " and " + getInventory();
        }
        return str;
    }

    private int findItemInKit(String item) {
        for (int i = 0; i < kit.size(); i++) {
            String tmpItem = kit.get(i);

            if (item.equals(tmpItem)) {
                return i;
            }
        }
        return -1;
    }

    private boolean kitIsEmpty() {
        if (kit.isEmpty()) {
            return true;
        }
        return false;
    }
}