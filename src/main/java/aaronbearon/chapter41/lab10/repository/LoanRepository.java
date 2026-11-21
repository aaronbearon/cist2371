package aaronbearon.chapter41.lab10.repository;

import aaronbearon.chapter41.lab10.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

}
