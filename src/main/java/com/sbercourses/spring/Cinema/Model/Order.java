package com.sbercourses.spring.Cinema.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@SequenceGenerator(name = "default_generator", sequenceName = "order_sequence",allocationSize = 1)
public class Order extends GenericModel {


    @Column(name = "rent_data", nullable = false)
    private LocalDate rent_data;

    @Column(name = "rent_period", nullable = false)
    private LocalDate rent_period;

    @Column(name = "purchase", nullable = false)
    private Boolean purchase;

    @OneToOne
    @JoinColumn(name = "film_id",foreignKey = @ForeignKey(name = "FK_FILMS_ORDERS"))
    private Film film_id;

    @OneToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "FK_USERS_ORDERS"))
    private User user_id;





}
