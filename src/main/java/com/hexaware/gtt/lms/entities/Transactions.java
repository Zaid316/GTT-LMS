package com.hexaware.gtt.lms.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.hexaware.gtt.lms.enums.TransType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transId;

    @ManyToOne
    @JoinColumn(name = "u_id")
    private Users users;

    private long paymentId;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupons coupons;

    @Enumerated(EnumType.STRING)
    private TransType transactionType;

    private double pointsGained;
    private double pointsSpent; 
    private double amount;

    @Column(updatable = false)
    private final LocalDateTime creationDate = LocalDateTime.now();

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(UUID transId, Users users, long paymentId, Coupons coupons, TransType transactionType,
			double pointsGained, double pointsSpent, double amount) {
		super();
		this.transId = transId;
		this.users = users;
		this.paymentId = paymentId;
		this.coupons = coupons;
		this.transactionType = transactionType;
		this.pointsGained = pointsGained;
		this.pointsSpent = pointsSpent;
		this.amount = amount;
	}

	public UUID getTransId() {
		return transId;
	}

	public void setTransId(UUID transId) {
		this.transId = transId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public TransType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransType transactionType) {
		this.transactionType = transactionType;
	}

	public double getPointsGained() {
		return pointsGained;
	}

	public void setPointsGained(double pointsGained) {
		this.pointsGained = pointsGained;
	}

	public double getPointsSpent() {
		return pointsSpent;
	}

	public void setPointsSpent(double pointsSpent) {
		this.pointsSpent = pointsSpent;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", users=" + users + ", paymentId=" + paymentId + ", coupons="
				+ coupons + ", transactionType=" + transactionType + ", pointsGained=" + pointsGained + ", pointsSpent="
				+ pointsSpent + ", amount=" + amount + ", creationDate=" + creationDate + "]";
	}

   

}
