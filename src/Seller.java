public class Seller {
    private String name;
    private Inventory inventory;

    public Seller(String storeName, Inventory startingInventory) {
        name = storeName;
        inventory = startingInventory;
    }

    public void buy(ItemInterface item) {
        inventory.addOne(item);
    }

    public ItemInterface sell(String itemName) {
        ItemInterface result = removeItem(itemName);
        if (result != null) {
            return result;
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
}

