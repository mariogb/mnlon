
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractFligthInstanceRepository implements CrudRepository<FligthInstance, Long> {
    
    private final EntityManager entityManager;
    
    AbstractFligthInstanceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<FligthInstance> findById(Long id);

    abstract Optional<FligthInstance> findByPkey(String pkey);

   } 
    
        