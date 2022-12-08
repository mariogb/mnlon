
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractContractInRepository implements CrudRepository<ContractIn, Long> {
    
    private final EntityManager entityManager;
    
    AbstractContractInRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<ContractIn> findById(Long id);

    abstract Optional<ContractIn> findByPkey(String pkey);

   } 
    
        