import java.util.ArrayList;

public class Player {
    private String name;
    private Inventory inventory;
    private double money;
    private Basket shoppingBasket;
    private Inventory viewOfStoreInventory;

    public Player(String playerName, double startingMoney, Inventory startingInventory) {
        name = playerName;
        money = startingMoney;
        inventory = startingInventory;
        shoppingBasket = new Basket();
    }

    public void buy(ItemInterface item) {
        if (Double.valueOf(item.getInventoryTableRow().getColumnThree().trim()) > money) {
            return;
        }
        inventory.addOne(item);
        money -= Double.valueOf(item.getInventoryTableRow().getColumnThree().trim());
    }

    public ItemInterface sell(String itemName) {
        ItemInterface i = removeItem(itemName);
        if (i != null) {
            money += Double.valueOf(i.getInventoryTableRow().getColumnThree().trim());
            return i;
        }
        return null;
    }

    public void addItem(ItemInterface item) {
        inventory.addOne(item);
    }

    public ItemInterface removeItem(String itemName) {
        return inventory.removeOne(itemName);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public Basket getShoppingBasket() {
        return shoppingBasket;
    }

    public void addToShoppingBasket(ItemInterface itemDataRow) {
        shoppingBasket.add(itemDataRow);
    }

    public void removeFromShoppingBasket(String itemName) {
        shoppingBasket.remove(itemName);
    }

    public ArrayList<ItemInterface> getStoreInventoryView() {
        return viewOfStoreInventory.getStock();
    }

    public Inventory getStoreView() {
        return viewOfStoreInventory;
    }

    public void setStoreView(Inventory storeInventory) {
        viewOfStoreInventory = storeInventory;
    }

    public double getMoney() {
        return money;
    }
}