public class Rock implements ItemInterface {
    int expiry;

    public Rock(int expiry) {
        this.expiry = expiry;
    }

    @Override
    public InventoryTableRow getInventoryTableRow() {
        return new InventoryTableRow("Rock", "Rock :D", "5.0", String.valueOf(expiry));
    }

    @Override
    public CartTableRow getCartRow(String column3) {
        return new CartTableRow("Rock", "5.0", column3);
    }
}
