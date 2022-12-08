
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractProductRepository implements CrudRepository<Product, Long> {
    
    private final EntityManager entityManager;
    
    AbstractProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Product> findById(Long id);

    abstract Optional<Product> findByPkey(String pkey);

   } 
    
        