
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractSaleContractRepository implements CrudRepository<SaleContract, Long> {
    
    private final EntityManager entityManager;
    
    AbstractSaleContractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<SaleContract> findById(Long id);

    abstract Optional<SaleContract> findByPkey(String pkey);

   } 
    
        