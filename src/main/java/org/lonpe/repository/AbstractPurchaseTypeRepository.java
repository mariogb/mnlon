
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractPurchaseTypeRepository implements CrudRepository<PurchaseType, Long> {
    
    private final EntityManager entityManager;
    
    AbstractPurchaseTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<PurchaseType> findById(Long id);

    abstract Optional<PurchaseType> findByPkey(String pkey);

   } 
    
        