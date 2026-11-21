package aaronbearon.chapter41.lab10.controller;

import aaronbearon.chapter41.lab10.model.Loan;
import aaronbearon.chapter41.lab10.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 10, Loan Service.
 */
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    LoanRepository repo;

    @GetMapping
    public List<Loan> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Loan getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Loan create(@RequestBody Loan loan) {
        // if a field is invalid, the loan isn't created
        try {
            loan.validate();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return repo.save(loan);
    }

    @PutMapping("/{id}")
    public Loan update(@PathVariable Long id, @RequestBody Loan updated) {
        // if a loan doesn't exist, it can't be updated
        Loan loan = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        loan.setLoanAmount(updated.getLoanAmount());
        loan.setInterestRate(updated.getInterestRate());
        loan.setYears(updated.getYears());
        // if a field is invalid, the update doesn't occur
        try {
            loan.validate();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return repo.save(loan);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "Loan deleted!";
    }
}
