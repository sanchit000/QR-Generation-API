//To store the json data for a person



package com.example.repeat4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JSON_Store_Repo extends JpaRepository<Store_JSON, Long>{
	@Query(" FROM Store_JSON  where client_id = :client_id AND key_gen = :key_gen")
    
    public Store_JSON findByIDandKey(@Param("client_id") long client_id, 
                                                    @Param("key_gen") long key_gen);
}