/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academiadev;

import java.time.LocalDate;

/**
 *
 * @author tiago
 */
public class Sale {

    private String saleId;
    private String productId;
    private String paymentMethod;
    private LocalDate date;
    private Integer nPayment;

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getnPayment() {
        return nPayment;
    }

    public void setnPayment(Integer nPayment) {
        this.nPayment = nPayment;
    }
}
