package com.example.cus_prov_with_sb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provinceName;

    @OneToMany(targetEntity = Customer.class)
    @JsonBackReference
    private List<Customer> customers;


    public Province() {
    }

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public Province(Long id, String provinceName, List<Customer> customers) {
        this.id = id;
        this.provinceName = provinceName;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}