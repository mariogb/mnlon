
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractPurchaseRepository implements CrudRepository<Purchase, Long> {
    
    private final EntityManager entityManager;
    
    AbstractPurchaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Purchase> findById(Long id);

    abstract Optional<Purchase> findByPkey(String pkey);

   } 
    
        