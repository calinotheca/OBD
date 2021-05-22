package pl.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.java.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Long> {

    Rate findByRate(Float rate);
}