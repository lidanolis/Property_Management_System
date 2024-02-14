package model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class systemTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long propertyId;
    private String customerId;
    private String ownerId;
    private LocalDate DOC;
    
    private String customerReview;
    private int customerRating;
    
    private String ownerReview;
    private int ownerRating;
    
    private String approvalId;
    private float total;
    private String approveStatus;

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public systemTransaction() {
    }

    public systemTransaction(Long propertyId, String customerId, String ownerId, LocalDate DOC, String customerReview, int customerRating, String ownerReview, int ownerRating, String approvalId, float total, String approveStatus) {
        this.propertyId = propertyId;
        this.customerId = customerId;
        this.ownerId = ownerId;
        this.DOC = DOC;
        this.customerReview = customerReview;
        this.customerRating = customerRating;
        this.ownerReview = ownerReview;
        this.ownerRating = ownerRating;
        this.approvalId = approvalId;
        this.total = total;
        this.approveStatus = approveStatus;
    }


    

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getDOC() {
        return DOC;
    }

    public void setDOC(LocalDate DOC) {
        this.DOC = DOC;
    }

    public String getCustomerReview() {
        return customerReview;
    }

    public void setCustomerReview(String customerReview) {
        this.customerReview = customerReview;
    }

    public int getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(int customerRating) {
        this.customerRating = customerRating;
    }

    public String getOwnerReview() {
        return ownerReview;
    }

    public void setOwnerReview(String ownerReview) {
        this.ownerReview = ownerReview;
    }

    public int getOwnerRating() {
        return ownerRating;
    }

    public void setOwnerRating(int ownerRating) {
        this.ownerRating = ownerRating;
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
        if (!(object instanceof systemTransaction)) {
            return false;
        }
        systemTransaction other = (systemTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.systemTransaction[ id=" + id + " ]";
    }
    
}
