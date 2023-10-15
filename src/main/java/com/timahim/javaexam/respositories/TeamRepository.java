package com.timahim.javaexam.respositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.timahim.javaexam.models.Team;


@Repository
public interface TeamRepository extends CrudRepository< Team, Long> {
	List<Team>findAll();
}
