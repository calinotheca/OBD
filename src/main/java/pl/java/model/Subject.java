package pl.java.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(length=20)
    private String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Rating> ratings = new HashSet<>();

    public Subject() {
    }

    public Subject(String name) {
        this.name = name;
        this.ratings = ratings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}
