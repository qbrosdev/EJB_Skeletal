package model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by V.Ghasemi
 * on 10/24/2017.
 */

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private String street;
    private String city;
    private String province;
    private String country;
    private String postcode;

    /**
     * JPA entity needs an empty constructor just in case you add custom construcotrs later on
     * https://stackoverflow.com/a/18099188/3593084
     */
    public Address() { }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
