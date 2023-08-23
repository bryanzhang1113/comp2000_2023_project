public class Tomato implements ItemInterface {
    int expiry;

    public Tomato(int expiry) {
        this.expiry = expiry;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow("Tomato", "Red and round", "15.0", String.valueOf(expiry));
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow("Tomato", "15.0", column3);
    }
}
