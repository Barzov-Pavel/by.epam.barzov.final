package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

public class Purchase implements Serializable {
    private Long id;
    private User user;
    private Tour tour;
    private Date date;
    private BigDecimal price;
    private String status;

    public Purchase() {
    }

    public Purchase(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) &&
                Objects.equals(user, purchase.user) &&
                Objects.equals(tour, purchase.tour) &&
                Objects.equals(date, purchase.date) &&
                Objects.equals(status, purchase.status) &&
                price == null ? purchase.price == null :
                price.compareTo(purchase.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, tour, date, price, status);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "date=" + date +
                ", id=" + id +
                ", user=" + user +
                ", tour=" + tour +
                ", price=" + price +
                '}';
    }
}