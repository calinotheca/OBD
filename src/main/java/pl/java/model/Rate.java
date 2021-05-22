package pl.java.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private float rate;

    @Column(length=20)
    private String description;

    @OneToMany(mappedBy = "rate", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();

    public Rate() {
    }

    public Rate(float rate, String description) {
        this.rate = rate;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
