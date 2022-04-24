package com.softclub.model.controller;

import com.softclub.model.dto.CoffeeDTO;
import com.softclub.model.dto.OrderDTO;
import com.softclub.model.entity.order.DeliveryType;
import com.softclub.model.service.CoffeeService;
import com.softclub.model.service.OrderService;
import com.softclub.model.service.impl.CoffeeServiceImpl;
import com.softclub.model.service.impl.OrderServiceImpl;
import lombok.NoArgsConstructor;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ManagedBean
@RequestScoped
@NoArgsConstructor
public class OrderBean {

    private OrderDTO orderDTO = new OrderDTO();
    private CoffeeDTO coffeeDTO = new CoffeeDTO();
    private final OrderService orderService = new OrderServiceImpl();
    private final CoffeeService coffeeService = new CoffeeServiceImpl();
    private List<OrderDTO> allOrders;
    private double coffeePricePerGram;
    private int gramsAmount;

    public String persistOrder() {
        Optional<CoffeeDTO> coffeeDTOOptional = coffeeService.findById(coffeeDTO.getId());
        if (coffeeDTOOptional.isPresent()) {
            CoffeeDTO coffee = coffeeDTOOptional.get();
            orderDTO.setCoffeeDTO(coffee);
            double finalPrice = coffee.getPricePerGram() * orderDTO.getGramsAmount();
            if (orderDTO.getDeliveryType().equals(DeliveryType.COURIER)) {
                finalPrice = finalPrice + 10;
            }
            orderDTO.setFinalPrice(finalPrice);
            Optional<OrderDTO> optionalOrderDTO = orderService.findById(orderDTO.getId());
            if (optionalOrderDTO.isEmpty()) {
                orderService.save(orderDTO);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                Flash flash = facesContext.getExternalContext().getFlash();
                flash.setKeepMessages(true);
                flash.setRedirect(true);
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Статус заказа", "Заказ принят в обработку"));
                return "index";
            } else {
                orderService.update(orderDTO);
                return "allOrders";
            }
        }
        return "allOrders";
    }

    public String deleteOrderPage() {
        getOrderByParam();
        return "deleteOrder";
    }

    public String deleteOrder() {
        Optional<OrderDTO> byId = orderService.findById(orderDTO.getId());
        byId.ifPresent(orderService::delete);
        return "index";
    }

    public void selectCoffeePricePerGram() {
        Optional<CoffeeDTO> coffeeDTOOptional = coffeeService.findById(coffeeDTO.getId());
        if (coffeeDTOOptional.isPresent()) {
            CoffeeDTO coffeedto = coffeeDTOOptional.get();
            coffeePricePerGram = coffeedto.getPricePerGram();
        }
    }

    public void selectGramsAmount() {
        gramsAmount = orderDTO.getGramsAmount();
    }

    public String editOrderPage() {
        getOrderByParam();
        return "order";
    }

    private void getOrderByParam() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> parameterMap = facesContext.getExternalContext().getRequestParameterMap();
        String orderId = parameterMap.get("orderId");
        Optional<OrderDTO> optionalOrderDTO = orderService.findById(Long.parseLong(orderId));
        optionalOrderDTO.ifPresent(order -> orderDTO = order);
    }

    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }

    public int getOrderAmount() {
        return orderService.findAll().size();
    }

    public OrderDTO getOrderDTO() {
        return orderDTO;
    }

    public void setOrderDTO(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public void setCoffeeDTO(CoffeeDTO coffeeDTO) {
        this.coffeeDTO = coffeeDTO;
    }

    public Object getCoffeeDTO() {
        return coffeeDTO;
    }

    public double getCoffeePricePerGram() {
        return coffeePricePerGram;
    }

    public void setCoffeePricePerGram(double coffeePricePerGram) {
        this.coffeePricePerGram = coffeePricePerGram;
    }

    public int getGramsAmount() {
        return gramsAmount;
    }

    public void setGramsAmount(int gramsAmount) {
        this.gramsAmount = gramsAmount;
    }
}
