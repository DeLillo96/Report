package Server.Entity;

import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@FilterDefs({
        @FilterDef(name = "id", parameters = @ParamDef(name = "id", type = "integer")),
        @FilterDef(name = "name", parameters = @ParamDef(name = "name", type = "string")),
        @FilterDef(name = "address", parameters = @ParamDef(name = "address", type = "string")),
        @FilterDef(name = "zip", parameters = @ParamDef(name = "zip", type = "string")),
        @FilterDef(name = "city", parameters = @ParamDef(name = "city", type = "string")),
        @FilterDef(name = "region", parameters = @ParamDef(name = "region", type = "string")),
        @FilterDef(name = "country", parameters = @ParamDef(name = "country", type = "string"))
})
@Filters({
        @Filter(name = "id", condition = "id = :id"),
        @Filter(name = "name", condition = "name like :name"),
        @Filter(name = "address", condition = "address like :address"),
        @Filter(name = "zip", condition = "zip like :zip"),
        @Filter(name = "city", condition = "city like :city"),
        @Filter(name = "region", condition = "region like :region"),
        @Filter(name = "country", condition = "country like :country")
})
@Table(name = "Location")
public class Location extends AbstractEntity {
    @Id
    @GeneratedValue
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(unique = true, nullable = false, length = 16)
    private String name;

    @Column(nullable = false, length = 127)
    private String address;

    @Column(nullable = false, length = 5)
    private String zip;

    @Column(nullable = false, length = 127)
    private String city;

    @Column(nullable = false, length = 127)
    private String region;

    @Column(nullable = false, length = 127)
    private String country;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(
            name = "target",
            joinColumns = {@JoinColumn(name = "location_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Set<Person> persons = new HashSet<>();

    public Location() {
        this("", "");
    }

    public Location(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Location(String name) {
        this(name, "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
