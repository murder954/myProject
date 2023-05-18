package com.example.myproject.repos;

import com.example.myproject.model.Part;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PartRepository extends CrudRepository<Part, Long> {

    List<Part> findByPartNumber(String partNumner);
}
