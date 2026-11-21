package aaronbearon.chapter39.dukesbookstore;

import lombok.Getter;

class Book {
    public final int id;
    public final String title;
    public final String author;
    public final double price;
    @Getter
    private int currInventory;
    @Getter
    private int inCart;

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        currInventory = 20;
        inCart = 0;
    }

    public void addToCart() {
        if (currInventory > 0) {
            currInventory -= 1;
            inCart += 1;
        }
    }

    public void removeFromCart() {
        if (inCart > 0) {
            inCart -= 1;
            currInventory += 1;
        }
    }

    public void removeAllFromCart() {
        currInventory += inCart;
        inCart = 0;
    }
}
