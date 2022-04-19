package com.example.invoivedemo;

import com.example.invoivedemo.model.Invoice;
import com.example.invoivedemo.repo.InvoiceRepo;
import com.example.invoivedemo.service.InvoiceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ServiceMockTest {

    @MockBean
    private InvoiceRepo invoiceRepo;

    @Autowired
    private InvoiceService invoiceService;


    @Test
    public void getInvoiceTest(){
        when(invoiceRepo.findAll()).thenReturn(Stream.of(new Invoice(1L,"abc",
                "bjbss@gmail.com",
                "urban",
                "mys",
                23423,
                "in",
                "sdf",
                "asdsd",
                500d,
                true)).collect(Collectors.toList()));
        assertEquals(1,invoiceService.findAllInvoices().size());
    }

    @Test
    void getAllInvoices()
    {
        invoiceService.findAllInvoices();
        verify(invoiceRepo).findAll();
    }

    @Test
    public void saveInvoiceTest(){
        Invoice invoice = new Invoice(4L,"seenu", "abc@gmail.com",
                "Stett", "Mysore", 2342344, "fsd",
                "dfgbdf","dfgfg", 450d, false);
        when(invoiceRepo.save(invoice)).thenReturn(invoice);
        assertEquals(invoice,invoiceService.addInvoice(invoice));
    }



@Test
public void UpdateTest(){
        Invoice invoice=new Invoice(11L,"abc",
                "bjbss@gmail.com",
                "urban",
                "mys",
                23423,
                "in",
                "sdf",
                "asdsd",
                500d,
                true);
        assertThat(invoice.getId()).isGreaterThan(0);
        invoice.setEmail("Naa@gmail.com");
        invoiceRepo.save(invoice);
        assertThat(invoice.getEmail()).isEqualTo("Naa@gmail.com");
}

    @Test
    public void DeleteTest(){
        Invoice deltest = new Invoice(4L,"John", "test@gmail.com",
                "sddfds", "Mysore", 2342344, "fsd",
                "10/12/15","hkujkj", 450d, true);
        invoiceService.deleteInvoice(deltest.getId());
        assertThat(invoiceRepo.existsById(4L)).isFalse();
    }

}