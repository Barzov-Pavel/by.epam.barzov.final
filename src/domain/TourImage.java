package domain;

import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

public class TourImage implements Serializable {
    private Long id;
    private Tour tour;
    private URL imageUrl;
    private URL thumbnailUrl;

    public TourImage() {}

    public TourImage(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(URL imageUrl) {
        this.imageUrl = imageUrl;
    }

    public URL getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(URL thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourImage tourImage = (TourImage) o;
        return Objects.equals(id, tourImage.id) &&
                Objects.equals(tour, tourImage.tour) &&
                Objects.equals(imageUrl, tourImage.imageUrl) &&
                Objects.equals(thumbnailUrl, tourImage.thumbnailUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tour, imageUrl, thumbnailUrl);
    }

    @Override
    public String toString() {
        return "TourImage{" +
                "id=" + id +
                ", tour=" + tour +
                ", imageUrl=" + imageUrl +
                ", thumbnailUrl=" + thumbnailUrl +
                '}';
    }
}
