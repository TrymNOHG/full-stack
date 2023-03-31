package edu.ntnu.trym.exercise5.service;

import edu.ntnu.trym.exercise5.dto.ExpressionDTO;
import edu.ntnu.trym.exercise5.mapper.CalculationMapper;
import edu.ntnu.trym.exercise5.model.Equation;
import edu.ntnu.trym.exercise5.repo.EquationRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private final EquationRepository equationRepository;
    private final CalculationMapper calculationMapper;
    private final CalculationService calculationService;
    private final Logger logger = LoggerFactory.getLogger(CalculationService.class);

    public void deleteLog() {

        equationRepository.deleteAll();

    }

}
