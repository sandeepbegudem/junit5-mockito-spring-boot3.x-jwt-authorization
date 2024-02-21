package com.sandeepbegudem.customer.payments.service.dto;import com.sandeepbegudem.customer.payments.service.entity.Customer;import com.sandeepbegudem.customer.payments.service.entity.PaymentMode;import com.sandeepbegudem.customer.payments.service.entity.PaymentType;import lombok.*;import java.math.BigDecimal;import java.util.Date;@Setter@Getter@NoArgsConstructor@AllArgsConstructorpublic class PaymentDto {    private Integer paymentId;    private PaymentMode paymentMode;    private String paymentTargetEntity;    private BigDecimal paymentAmount;    private Date paymentDate;    private PaymentType paymentType;    private Customer customer_payments_fk;}