package com.example.invoivedemo.repo;

import com.example.invoivedemo.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {


}
