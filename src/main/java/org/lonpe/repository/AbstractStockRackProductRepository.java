
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractStockRackProductRepository implements CrudRepository<StockRackProduct, Long> {
    
    private final EntityManager entityManager;
    
    AbstractStockRackProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<StockRackProduct> findById(Long id);

    abstract Optional<StockRackProduct> findByPkey(String pkey);

   } 
    
        