package com.example.invoivedemo;

import com.example.invoivedemo.model.Invoice;
import com.example.invoivedemo.repo.InvoiceRepo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional


class InvoiceApplicationTests {

	@Autowired
	private InvoiceRepo invoiceRepo;

	@Test
	@Order(1)
	public void getAll () {
		List list = invoiceRepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}


	@Test
	@Order(2)
	@Rollback
	public void testCreate () {
		Invoice p = new Invoice();
		p.setName("xukggg");
		p.setEmail("bjbss@gmail.com");
		p.setAddress("kjdfjksdfs");
		p.setCity("Ms");
		p.setCode(4564654);
		p.setCountry("Ind");
		p.setDueDate(LocalDate.parse("2020-01-08"));
		p.setDescription("hsfbd");
		p.setTotal(5000d);
		p.setIsPaid(true);
		invoiceRepo.save(p);
		assertNotNull(invoiceRepo.findAll());
	}


	@Test
	@Order(3)
	@Rollback()
	public void testUpdate () {
		Invoice p = invoiceRepo.findById(1L).get();
		p.setName("Arun Hridayam");
		invoiceRepo.save(p);
		assertNotEquals("Arun", invoiceRepo.findById(1L).get().getName());
	}

	@Test
	@Order(4)
	@Rollback
	public void testDelete () {
		invoiceRepo.deleteById(1L);
		assertThat(invoiceRepo.existsById(1L)).isFalse();
	}

}
