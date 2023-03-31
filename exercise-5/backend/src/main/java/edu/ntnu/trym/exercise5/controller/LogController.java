package edu.ntnu.trym.exercise5.controller;

import edu.ntnu.trym.exercise5.dto.EquationDTO;
import edu.ntnu.trym.exercise5.dto.ExpressionDTO;
import edu.ntnu.trym.exercise5.dto.UserDTO;
import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.service.CalculationService;
import edu.ntnu.trym.exercise5.service.LogService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Expression;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/calculator/log")
@RestController
@RequiredArgsConstructor
public class LogController {

    private final Logger logger = LoggerFactory.getLogger(CalculationController.class);
    private final CalculationService calculationService;
    private final LogService logService;


    @CrossOrigin(origins = "*")
    @PostMapping("/load")
    @ResponseBody
    public ResponseEntity<List<EquationDTO>> loadData(@RequestBody UserDTO userDTO) {
        try {
            List<EquationDTO> equations = calculationService.getAllEquations(userDTO);
            logger.info(String.format("%s has accessed their log", userDTO.getUsername()));
            return ResponseEntity.ok(equations);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }

    }


    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Object> delete() {
        try {
            logService.deleteLog();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }

    }


}
