
        
package org.lonpe.repository;            

            


import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;   
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;
    
@Repository
public abstract class AbstractComercialDocumentInRepository implements CrudRepository<ComercialDocumentIn, Long> {
    
    private final EntityManager entityManager;
    
    AbstractComercialDocumentInRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public abstract Optional<ComercialDocumentIn> findById(Long id);

    abstract Optional<ComercialDocumentIn> findByPkey(String pkey);

   } 
    
        