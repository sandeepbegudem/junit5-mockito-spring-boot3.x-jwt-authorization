package com.sandeepbegudem.customer.payments.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;

    private String paymentTargetEntity;
    private BigDecimal paymentAmount;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "hh:mm:ss")
    private Date paymentDate;

    @Enumerated(EnumType.ORDINAL)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Customer customer_payments_fk;

    public Payment(Integer paymentId, PaymentMode paymentMode, String paymentTargetEntity, BigDecimal paymentAmount, Date paymentDate, PaymentType paymentType, Customer customer_payments_fk) {
        this.paymentId = paymentId;
        this.paymentMode = paymentMode;
        this.paymentTargetEntity = paymentTargetEntity;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.customer_payments_fk = customer_payments_fk;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = new Date();
    }
}
