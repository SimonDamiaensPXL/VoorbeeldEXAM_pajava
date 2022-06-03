package be.pxl.paj.olympicgames.api;

import be.pxl.paj.olympicgames.api.data.AthleteDTO;
import be.pxl.paj.olympicgames.api.data.CreateRaceCommand;
import be.pxl.paj.olympicgames.api.data.RaceDTO;
import be.pxl.paj.olympicgames.api.data.RegisterScoreCommand;
import be.pxl.paj.olympicgames.exception.BusinessException;
import be.pxl.paj.olympicgames.exception.NotFoundException;
import be.pxl.paj.olympicgames.service.OlympicGamesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("olympicgames")
public class OlympicGamesController {

	private final OlympicGamesService olympicGamesService;

	public OlympicGamesController(OlympicGamesService olympicGamesService) {
		this.olympicGamesService = olympicGamesService;
	}

	@GetMapping("/athletes")
	public List<AthleteDTO> getAthletes() {
		return olympicGamesService.getAthletes();
	}

	@GetMapping("/races")
	public List<RaceDTO> getRaces() {
		return olympicGamesService.getRaces();
	}

	@PostMapping("/races")
	public ResponseEntity<RaceDTO> createRace(@RequestBody CreateRaceCommand command) {
		return new ResponseEntity<>(olympicGamesService.createRace(command), HttpStatus.CREATED);
	}

	@PutMapping("/races/{raceId}/{athleteId}")
	public ResponseEntity<Void> addAthlete(@PathVariable Long raceId, @PathVariable Long athleteId) {
		try {
			olympicGamesService.addAthlete(raceId, athleteId);
		}
		catch (NotFoundException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		catch (BusinessException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
