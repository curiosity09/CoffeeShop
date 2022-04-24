package com.softclub.model.controller;

import com.softclub.model.dto.CoffeeDTO;
import com.softclub.model.service.CoffeeService;
import com.softclub.model.service.impl.CoffeeServiceImpl;
import lombok.NoArgsConstructor;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
@NoArgsConstructor
public class CoffeeBean {

    private final CoffeeService coffeeService = new CoffeeServiceImpl();
    private List<CoffeeDTO> allCoffee;
    private CoffeeDTO coffeeDTO = new CoffeeDTO();

    public List<CoffeeDTO> getAllCoffee() {
        return coffeeService.findAll();
    }

    public String saveCoffee() {
        coffeeService.save(coffeeDTO);
        return "index";
    }

    public CoffeeDTO getCoffeeDTO() {
        return coffeeDTO;
    }

    public void setCoffeeDTO(CoffeeDTO coffeeDTO) {
        this.coffeeDTO = coffeeDTO;
    }
}
