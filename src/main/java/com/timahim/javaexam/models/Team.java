package com.timahim.javaexam.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name="teams")
public class Team {
    
	//------------AUTOMATIC--------------//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    // other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
        this.updatedAt = new Date(); //we manually added this to also update 'update it' when first created.
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    //------------END AUTOMATIC--------------//
    
    @NotEmpty(message="Team Name MUST be ADDED!")
    @Size(min=5, max=30, message="Team name must be a minimum of 5 characters and maximum of 30 characters!")
    private String teamName;
    
    @NotNull(message="MUST have a Skill Level!")
    @Min(value= 1, message="MUST have skill level minimum of 1!")
    @Max(value= 5, message="MUST have skill level minimum of 5!")
    private Integer skillLevel;
    
    @NotEmpty(message="Must provide the day of Gameday!")
    @Size(min=5, max=10, message="Must be a minimum of 5 characers and mazimum of 10 characters!")
    private String gameDay;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User creator;
    
    public Team() {}
    
	public Team(
			@NotEmpty(message = "Team Name MUST be ADDED!") @Size(min = 5, max = 30, message = "Team name must be a minimum of 5 characters and maximum of 30 characters!") String teamName,
			@NotNull(message = "MUST have a Skill Level!") @Size(min = 1, max = 5, message = "MUST have skill level minimum of 1 and Maximum of 5!") Integer skillLevel,
			@NotEmpty(message = "Must provide the day of Gameday!") @Size(min = 5, max = 10, message = "Must be a minimum of 5 characers and mazimum of 10 characters!") String gameDay,
			User creator) {
		super();
		this.teamName = teamName;
		this.skillLevel = skillLevel;
		this.gameDay = gameDay;
		this.creator = creator;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Integer getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(Integer skillLevel) {
		this.skillLevel = skillLevel;
	}
	public String getGameDay() {
		return gameDay;
	}
	public void setGameDay(String gameDay) {
		this.gameDay = gameDay;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
    
    
    }
