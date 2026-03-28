package com.codingninjas.EVotingSystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codingninjas.EVotingSystem.entities.Election;
import com.codingninjas.EVotingSystem.entities.ElectionChoice;
import com.codingninjas.EVotingSystem.entities.User;
import com.codingninjas.EVotingSystem.entities.Vote;
import com.codingninjas.EVotingSystem.repositories.ElectionChoiceRepository;
import com.codingninjas.EVotingSystem.repositories.ElectionRepository;
import com.codingninjas.EVotingSystem.repositories.UserRepository;
import com.codingninjas.EVotingSystem.repositories.VoteRepository;

@Service
public class EVotingService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ElectionRepository electionRepository;

    @Autowired
    ElectionChoiceRepository electionChoiceRepository;

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public void addUser(User user) {
         userRepository.save(user);
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    public void addVote(Long userId, Long electionId, Long electionChoiceId) {
    	User user = userRepository.findById(userId).orElse(null);
    	Election election = electionRepository.findById(electionId).orElse(null);
    	ElectionChoice choice = electionChoiceRepository.findById(electionChoiceId).orElse(null);
    	
    	 if (AlreadyGivenVote(userId, electionId)) {
             throw new RuntimeException("You have already given your vote");
         }
    	 
    	 //Save vote
    	 
    	 Vote vote = new Vote();
    	 vote.setUser(user);
    	 vote.setElection(election);
    	 vote.setElectionChoice(choice);
    	 
    	 voteRepository.save(vote);
    	 
    	
    }

    public void addElection(Election election) {
    	electionRepository.save(election);
        
    }

    public boolean AlreadyGivenVote(Long userId, Long electionId) {
        List<Vote> votes = voteRepository.findAll();
        for(int i =0 ; i<votes.size();i++) {
        	 Vote vote = votes.get(i);

        	    if (vote.getUser().getId() == userId &&
        	        vote.getElection().getId() == electionId) {
        	        return true;
        	    }
        }
        return false;
    }

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public void addElectionChoice(ElectionChoice electionChoice) {
    	 Long electionId = electionChoice.getElection().getId();

    	    // Step 2: fetch real election from DB
    	    Election election = electionRepository.findById(electionId)
    	            .orElseThrow(() -> new RuntimeException("Election not found"));

    	    // Step 3: attach managed entity
    	    electionChoice.setElection(election);

    	    // Step 4: save
    	    electionChoiceRepository.save(electionChoice);

    	    System.out.println("Saved successfully ");
    }

    public List<ElectionChoice> getAllElectionChoices() {
        return electionChoiceRepository.findAll();
    }

    public Election findElectionByName(String electionName) {
        return electionRepository.findElectionByName(electionName);
    }

    public long countTotalVotes() {
    	return voteRepository.count();
    }

    public long countVotesByElectionName(String electionName) {
        Election election = electionRepository.findElectionByName(electionName);
        List<Vote> votes = voteRepository.findAll();
        
        long count = 0;
        
        for (Vote vote : votes) {
            if (vote.getElection().getId() == election.getId()) {
                count++;
            }
        }

        return count;
        
    }

    public long choicesByElection(Long electionId) {
    	List<ElectionChoice> choices = electionChoiceRepository.findAll();

        long count = 0;

        for (ElectionChoice choice : choices) {
            if (choice.getElection().getId() == electionId) {
                count++;
            }
        }

        return count;
    }

	public ElectionChoice findElectionWinner(String electionName) {
   
		Election election = electionRepository.findElectionByName(electionName);

        List<ElectionChoice> choices = electionChoiceRepository.findAll();
        List<Vote> votes = voteRepository.findAll();

        ElectionChoice winner = null;
        long maxVotes = 0;

        for (ElectionChoice choice : choices) {

            if (choice.getElection().getId() == election.getId()) {

                long count = 0;

                for (Vote vote : votes) {
                    if (vote.getElectionChoice().getId() == choice.getId()) {
                        count++;
                    }
                }

                if (count > maxVotes) {
                    maxVotes = count;
                    winner = choice;
                }
            }
        }

        return winner;
	}
}
