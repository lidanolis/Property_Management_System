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
public class systemAdminFacade extends AbstractFacade<systemAdmin> {

    @PersistenceContext(unitName = "APUpropertysystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void removeAdmin(String adminUsername) {
        systemAdmin sysA = em.find(systemAdmin.class, adminUsername);
        em.remove(sysA);
        em.flush();
    }

    public systemAdminFacade() {
        super(systemAdmin.class);
    }

}
