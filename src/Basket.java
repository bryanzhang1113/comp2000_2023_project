import java.util.ArrayList;

public class Basket implements BasketInterface {
    ArrayList<ItemInterface> items;
    ArrayList<Integer> quantities;
    
    private static final int ITEM_NOT_FOUND = -1;
    private static final int DEFAULT_QUANTITY = 1;
    private static final int STARTING_INDEX = 0;

    public Basket() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public int itemIndex(String itemName) {
        for (int i = STARTING_INDEX; i < items.size(); i++) {
            if (items.get(i).getInventoryTableRow().getColumnOne().equalsIgnoreCase(itemName)) {
                return i;
            }
        }
        return ITEM_NOT_FOUND;
    }

    public ArrayList<CartTableRow> getRowData() {
        ArrayList<CartTableRow> data = new ArrayList<>();

        for (int i = STARTING_INDEX; i < items.size(); i++) {
            data.add(items.get(i).getCartRow(quantities.get(i) + ""));
        }

        return data;
    }

    @Override
    public void setItemQuantity(String itemName, int qty) {
        int index = itemIndex(itemName);
        if (index != ITEM_NOT_FOUND) {
            quantities.set(index, qty);
        }
    }

    public void add(ItemInterface item) {
        int index = itemIndex(item.getInventoryTableRow().getColumnOne());
        if (index != ITEM_NOT_FOUND) {
            quantities.set(index, quantities.get(index) + DEFAULT_QUANTITY);
        } else {
            items.add(item);
            quantities.add(DEFAULT_QUANTITY);
        }
    }

    @Override
    public void remove(String itemName) {
        int index = itemIndex(itemName);

        if (index != ITEM_NOT_FOUND) {
            items.remove(index);
            quantities.remove(index);
        }
    }

    @Override
    public void processTransaction(Player from, Seller to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = STARTING_INDEX; i < items.size() && !rollback; i++) {
            for (int q = STARTING_INDEX; q < quantities.get(i); q++) {
                ItemInterface saleItem = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                if (saleItem == null) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
                transactionItems.add(saleItem);
            }
        }

        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items;
            }
        }
    }

    @Override
    public void processTransaction(Seller from, Player to) {
        ArrayList<ItemInterface> transactionItems = new ArrayList<>();
        boolean rollback = false;
        // Remove/sell items from the `from` parameter
        for (int i = STARTING_INDEX; i < items.size() && !rollback; i++) {
            for (int q = STARTING_INDEX; q < quantities.get(i); q++) {
                ItemInterface saleItem = from.sell(items.get(i).getInventoryTableRow().getColumnOne());
                if (saleItem == null) {
                    rollback = true;
                    break;  // Trigger transaction rollback
                }
                transactionItems.add(saleItem);
            }
        }
        if (rollback) {
            for (ItemInterface item : transactionItems) {
                from.buy(item);  // Return to `from`
            }
        } else {
            for (ItemInterface item : transactionItems) {
                to.buy(item);  // Have `to` buy each of the transaction items
            }
        }
    }
}
