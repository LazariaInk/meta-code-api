package com.lazaria.metacode.repository;

import com.lazaria.metacode.model.FabricaDeCoduriInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricaDeCoduriInfoRepository extends JpaRepository<FabricaDeCoduriInfo, Integer> {
}
