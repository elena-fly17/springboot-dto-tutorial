package com.elenamukhina.springboot.repository;

import com.elenamukhina.springboot.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
