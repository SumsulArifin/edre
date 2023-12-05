package com.example.sysDevLtd.repository;

import com.example.sysDevLtd.entity.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckListRepository extends JpaRepository<CheckList,Integer> {
}
