package data.utility;

public class Pudding {

    private String id;
    private String puddName;
    private int price;
    private int stock;

    public Pudding(String id, String puddName, int price, int stock) {
        this.id = id;
        this.puddName = puddName;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuddName() {
        return puddName;
    }

    public void setPuddName(String puddName) {
        this.puddName = puddName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
