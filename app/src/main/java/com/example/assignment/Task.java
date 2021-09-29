package com.example.assignment;

public class Task {
    private int id;
    private String name;
    private String description;
    private String information;
    private String money;
    private String datetime;
    private String category;

    public Task(int id, String name, String description, String information, String money, String datetime, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.information = information;
        this.money = money;
        this.datetime = datetime;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
