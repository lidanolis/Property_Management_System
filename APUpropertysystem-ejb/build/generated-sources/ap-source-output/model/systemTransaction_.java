package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-15T14:36:30")
@StaticMetamodel(systemTransaction.class)
public class systemTransaction_ { 

    public static volatile SingularAttribute<systemTransaction, String> approveStatus;
    public static volatile SingularAttribute<systemTransaction, Float> total;
    public static volatile SingularAttribute<systemTransaction, Integer> customerRating;
    public static volatile SingularAttribute<systemTransaction, String> customerReview;
    public static volatile SingularAttribute<systemTransaction, String> customerId;
    public static volatile SingularAttribute<systemTransaction, LocalDate> DOC;
    public static volatile SingularAttribute<systemTransaction, String> approvalId;
    public static volatile SingularAttribute<systemTransaction, Long> id;
    public static volatile SingularAttribute<systemTransaction, String> ownerId;
    public static volatile SingularAttribute<systemTransaction, Integer> ownerRating;
    public static volatile SingularAttribute<systemTransaction, Long> propertyId;
    public static volatile SingularAttribute<systemTransaction, String> ownerReview;

}