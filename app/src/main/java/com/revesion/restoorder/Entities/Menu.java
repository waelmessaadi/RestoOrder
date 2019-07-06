package com.revesion.restoorder.Entities;

public class Menu {

    String nameMenu;
    String photoMenu;
    String description;
    String price;

    public Menu() {
    }

    public Menu(String nameMenu, String photoMenu, String description, String price) {
        this.nameMenu = nameMenu;
        this.photoMenu = photoMenu;
        this.description = description;
        this.price = price;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public String getPhotoMenu() {
        return photoMenu;
    }

    public void setPhotoMenu(String photoMenu) {
        this.photoMenu = photoMenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
