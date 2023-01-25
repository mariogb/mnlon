
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractPlaneRepository implements CrudRepository<Plane, Long> {
    
    private final EntityManager entityManager;
    
    AbstractPlaneRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Plane> findById(Long id);

    abstract Optional<Plane> findByPkey(String pkey);

   } 
    
        