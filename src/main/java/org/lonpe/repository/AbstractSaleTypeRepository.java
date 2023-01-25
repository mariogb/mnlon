
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractSaleTypeRepository implements CrudRepository<SaleType, Long> {
    
    private final EntityManager entityManager;
    
    AbstractSaleTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<SaleType> findById(Long id);

    abstract Optional<SaleType> findByPkey(String pkey);

   } 
    
        