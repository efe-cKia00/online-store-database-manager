package cs366onlinestore;

import java.time.LocalDateTime;

public class PriceChange {

    private int priceChangeId;
    private int productId;
    private float oldPrice;
    private float newPrice;
    private LocalDateTime changeDate;

    public PriceChange(int priceChangeId,
            int productId,
            float oldPrice,
            float newPrice,
            LocalDateTime changeDate) {

        this.priceChangeId = priceChangeId;
        this.productId = productId;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.changeDate = changeDate;
    }

    public int getPriceChangeId() {
        return priceChangeId;
    }

    public void setPriceChangeId(int priceChangeId) {
        this.priceChangeId = priceChangeId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(float oldPrice) {
        this.oldPrice = oldPrice;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(LocalDateTime changeDate) {
        this.changeDate = changeDate;
    }
}
