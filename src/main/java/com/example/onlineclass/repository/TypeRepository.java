package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jhlyh
 */
public interface TypeRepository extends JpaRepository<Type, Long> , JpaSpecificationExecutor<Type> {
}
