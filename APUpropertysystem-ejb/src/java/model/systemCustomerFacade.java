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
public class systemCustomerFacade extends AbstractFacade<systemCustomer> {

    @PersistenceContext(unitName = "APUpropertysystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void removeTransactionFromCustomer(String customerUsername, Long transactionId) {
        systemCustomer sysC = em.find(systemCustomer.class, customerUsername);
        systemTransaction aTran = em.find(systemTransaction.class, transactionId);
        sysC.getpTransaction().remove(aTran);
        em.flush();
    }

    public void removeCustomer(String customerUsername) {
        systemCustomer sysC = em.find(systemCustomer.class, customerUsername);
        em.remove(sysC);
        em.flush();
    }

    public systemCustomerFacade() {
        super(systemCustomer.class);
    }
}
