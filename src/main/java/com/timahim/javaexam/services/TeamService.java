package com.timahim.javaexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timahim.javaexam.models.Team;
import com.timahim.javaexam.respositories.TeamRepository;

@Service

public class TeamService {
	@Autowired
	private TeamRepository teamRepo;
	
	//creating a team in DB
	public Team createTeam(Team newTeam) {
		return teamRepo.save(newTeam);
	}
	
	//Read all Teams from DB
	public List<Team> readAllTeams() {
		return teamRepo.findAll();
	}
	
	//Read one team by id from DB
	public Team readOneTeam(Long id ) {
		Optional<Team> possibleTeam = teamRepo.findById(id);
		return possibleTeam.isPresent() ? possibleTeam.get() : null; //Tenary Operator : 'condition ? valueIfTrue : valueIfFalse" 
	}
	
	//update Team
	public Team updateTeam(Team changedTeam) {
		return teamRepo.save(changedTeam);
	}
	
	//Delete oneTeam
	public void deleteTeam(Long id) {
		teamRepo.deleteById(id);
	}
}
