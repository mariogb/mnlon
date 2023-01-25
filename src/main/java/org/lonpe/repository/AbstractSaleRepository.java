
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractSaleRepository implements CrudRepository<Sale, Long> {
    
    private final EntityManager entityManager;
    
    AbstractSaleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Sale> findById(Long id);

    abstract Optional<Sale> findByPkey(String pkey);

   } 
    
        