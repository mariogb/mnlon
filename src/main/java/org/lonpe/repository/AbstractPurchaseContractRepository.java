
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractPurchaseContractRepository implements CrudRepository<PurchaseContract, Long> {
    
    private final EntityManager entityManager;
    
    AbstractPurchaseContractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<PurchaseContract> findById(Long id);

    abstract Optional<PurchaseContract> findByPkey(String pkey);

   } 
    
        