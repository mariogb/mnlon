
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractMateriaRepository implements CrudRepository<Materia, Long> {
    
    private final EntityManager entityManager;
    
    AbstractMateriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Materia> findById(Long id);

    abstract Optional<Materia> findByPkey(String pkey);

   } 
    
        