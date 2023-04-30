package main.java;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "city")

public class City {
    // Включение полной каскадности entity.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)

    // Hibernate автоматически загрузит все Entity упомянутого города.
    @Fetch(FetchMode.JOIN)
    private List<Employee> employees = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int city_id;

    @Column(name = "city_id")
    private String city_name;

    public City(String city_name) {
        this.city_name = city_name;
    }

    public City() {
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return city_id == city.city_id && city_name.equals(city.city_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id, city_name);
    }

    public String getCity_name() {
        return city_name;
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}
