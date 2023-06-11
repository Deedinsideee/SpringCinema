package com.sbercourses.spring.Cinema.Controllers.rest;


import com.sbercourses.spring.Cinema.Model.Order;
import com.sbercourses.spring.Cinema.dto.OrderDTO;
import com.sbercourses.spring.Cinema.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Tag(name="Заказы",description = "Контроллеры для работы с заказами")
public class OrderController extends GenericController<Order, OrderDTO>
{
 private final OrderService orderService;
    protected OrderController(OrderService orderService) {
        super(orderService);
        this.orderService=orderService;

    }





}
