package com.ezhevikina.daohomework.domain;

public class Account {

  private final int id;
  private final String holder;
  private int amount;
  private boolean isActive;

  public Account(int id, String holder, int amount, boolean isActive) {
    this.id = id;
    this.holder = holder;
    this.amount = amount;
    this.isActive = isActive;
  }

  public int getId() {
    return id;
  }

  public String getHolder() {
    return holder;
  }

  public int getBalance() {
    return amount;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}

