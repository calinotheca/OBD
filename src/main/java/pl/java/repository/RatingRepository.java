package pl.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
