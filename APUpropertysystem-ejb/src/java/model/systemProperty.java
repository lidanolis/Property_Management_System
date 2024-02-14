package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class systemProperty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String owner_username;
    private String status;
    private String description;
    private String sales_type;
    private float price;
    private LocalDate DOC;
    private float discount;
    private String priceDayMonthYear;
    
    @OneToMany
    private ArrayList<systemTransaction> pTransaction = new ArrayList<systemTransaction>();

    public ArrayList<systemTransaction> getpTransaction() {
        return pTransaction;
    }

    public void setpTransaction(ArrayList<systemTransaction> pTransaction) {
        this.pTransaction = pTransaction;
    }

    public systemProperty() {
    }

    public systemProperty(String name, String owner_username, String status, String description, String sales_type, float price, LocalDate DOC, float discount, String priceDayMonthYear) {
        this.name = name;
        this.owner_username = owner_username;
        this.status = status;
        this.description = description;
        this.sales_type = sales_type;
        this.price = price;
        this.DOC = DOC;
        this.discount = discount;
        this.priceDayMonthYear = priceDayMonthYear;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner_username() {
        return owner_username;
    }

    public void setOwner_username(String owner_username) {
        this.owner_username = owner_username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSales_type() {
        return sales_type;
    }

    public void setSales_type(String sales_type) {
        this.sales_type = sales_type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDate getDOC() {
        return DOC;
    }

    public void setDOC(LocalDate DOC) {
        this.DOC = DOC;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getPriceDayMonthYear() {
        return priceDayMonthYear;
    }

    public void setPriceDayMonthYear(String priceDayMonthYear) {
        this.priceDayMonthYear = priceDayMonthYear;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof systemProperty)) {
            return false;
        }
        systemProperty other = (systemProperty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.systemProperty[ id=" + id + " ]";
    }

}
