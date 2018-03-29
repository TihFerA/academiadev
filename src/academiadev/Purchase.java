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
public class Purchase {

    private String purchaseId;
    private String paymentMethod;
    private LocalDate date;
    private Integer nPayment;
    private Double price;

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
