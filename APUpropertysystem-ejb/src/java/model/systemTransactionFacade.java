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
public class systemTransactionFacade extends AbstractFacade<systemTransaction> {

    @PersistenceContext(unitName = "APUpropertysystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public void removeTransaction(Long transactionId) {
        systemTransaction aTran = em.find(systemTransaction.class, transactionId);
        em.remove(aTran);
        em.flush();
    }
    public systemTransactionFacade() {
        super(systemTransaction.class);
    }
    
}
