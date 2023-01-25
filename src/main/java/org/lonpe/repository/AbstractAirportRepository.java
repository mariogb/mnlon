
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractAirportRepository implements CrudRepository<Airport, Long> {
    
    private final EntityManager entityManager;
    
    AbstractAirportRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Airport> findById(Long id);

    abstract Optional<Airport> findByPkey(String pkey);

   } 
    
        