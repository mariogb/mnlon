
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractTimePeriodRepository implements CrudRepository<TimePeriod, Long> {
    
    private final EntityManager entityManager;
    
    AbstractTimePeriodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<TimePeriod> findById(Long id);

    abstract Optional<TimePeriod> findByPkey(String pkey);

   } 
    
        