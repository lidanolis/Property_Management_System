package model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.systemTransaction;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-08-15T14:36:30")
@StaticMetamodel(systemProperty.class)
public class systemProperty_ { 

    public static volatile SingularAttribute<systemProperty, String> sales_type;
    public static volatile SingularAttribute<systemProperty, Float> price;
    public static volatile SingularAttribute<systemProperty, String> owner_username;
    public static volatile SingularAttribute<systemProperty, String> name;
    public static volatile SingularAttribute<systemProperty, String> description;
    public static volatile SingularAttribute<systemProperty, LocalDate> DOC;
    public static volatile SingularAttribute<systemProperty, Float> discount;
    public static volatile SingularAttribute<systemProperty, Long> id;
    public static volatile ListAttribute<systemProperty, systemTransaction> pTransaction;
    public static volatile SingularAttribute<systemProperty, String> priceDayMonthYear;
    public static volatile SingularAttribute<systemProperty, String> status;

}