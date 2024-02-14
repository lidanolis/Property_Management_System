/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class systemPropertyFacade extends AbstractFacade<systemProperty> {

    @PersistenceContext(unitName = "APUpropertysystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public systemPropertyFacade() {
        super(systemProperty.class);
    }

    public void removeTransactionFromProperty(Long PropertyId, Long transactionId) {
        systemProperty aprop = em.find(systemProperty.class, PropertyId);
        systemTransaction aTran = em.find(systemTransaction.class, transactionId);
        aprop.getpTransaction().remove(aTran);
        em.flush();
    }

    public void removeProperty(Long propertyId) {
        systemProperty oProp = em.find(systemProperty.class, propertyId);
        em.remove(oProp);
        em.flush();
    }

}
