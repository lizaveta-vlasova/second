package org.jboss.as.quickstarts.ejbinwar.domain;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "options")
public class Option implements Comparable<Option> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idoption")
    private Integer optionId;
    @Column
    private String type;
    @Column
    private Integer price;
    @Column(name = "connection_price")
    private Integer connectionPrice;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tariff_options",
            joinColumns = {@JoinColumn(name = "id_tariff")},
            inverseJoinColumns = {@JoinColumn(name = "id_option")}
    )
    private Set<Option> availableOptions;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getConnectionPrice() {
        return connectionPrice;
    }

    public void setConnectionPrice(Integer connectionPrice) {
        this.connectionPrice = connectionPrice;
    }

    public Set<Option> getAvailableOptions() {
        return availableOptions;
    }

    public void setAvailableOptions(Set<Option> availableOptions) {
        this.availableOptions = availableOptions;
    }

    @Override
    public int compareTo(Option o) {
        return optionId > o.optionId ? 1 : Objects.equals(optionId, o.optionId) ? 0 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        return optionId.equals(option.optionId);
    }

    @Override
    public int hashCode() {
        return optionId.hashCode();
    }
}
