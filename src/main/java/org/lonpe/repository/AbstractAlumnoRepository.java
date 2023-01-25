
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractAlumnoRepository implements CrudRepository<Alumno, Long> {
    
    private final EntityManager entityManager;
    
    AbstractAlumnoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<Alumno> findById(Long id);

    abstract Optional<Alumno> findByPkey(String pkey);

   } 
    
        