
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractCalificacionRepository implements CrudRepository<Calificacion, Long> {
    
    private final EntityManager entityManager;
    
    AbstractCalificacionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Calificacion> findById(Long id);

    abstract Optional<Calificacion> findByPkey(String pkey);

   } 
    
        