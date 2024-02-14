/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class systemOwnerFacade extends AbstractFacade<systemOwner> {

    @PersistenceContext(unitName = "APUpropertysystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public systemOwnerFacade() {
        super(systemOwner.class);
    }

    public void removePropertyFromOwner(String ownerUsername, Long propertyId) {
        systemOwner sysO = em.find(systemOwner.class, ownerUsername);
        systemProperty oProp = em.find(systemProperty.class, propertyId);
        sysO.getProperties().remove(oProp);
        em.flush();
    }

    public void removeOwner(String ownerUsername) {
        systemOwner sysO = em.find(systemOwner.class, ownerUsername);
        em.remove(sysO);
        em.flush();
    }

}
