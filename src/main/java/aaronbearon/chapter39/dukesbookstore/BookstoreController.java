package aaronbearon.chapter39.dukesbookstore;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Aaron Blum, CIST 2373 Java 3, Lab 9, Duke's Bookstore part 1.
 */
@Controller
public class BookstoreController {

    // The inventory of books.
    private final List<Book> titles = List.of(
            new Book(0, "My Early Years: Growing Up on Star7", "Duke", 30.75),
            new Book(1, "Web Servers for Fun and Profit", "Jeeves", 40.75),
            new Book(2, "Web Components for Web Developers", "Webster Masterson", 27.75),
            new Book(3, "From Oak to Java: The Revolution of a Language", "Kevin Novation", 10.75),
            new Book(4, "The Green Project: Programming for Consumers Devices", "Ben Thrilled", 30.00),
            new Book(5, "Java Intermediate Bytecodes", "Happy Coding", 30.95)
    );

    @GetMapping("/")
    public String listBooks(Model model) {
        addCartAttributes(model, false);
        return "catalog";
    }

    @GetMapping("/selected")
    public String showSelectedPage(@RequestParam(name = "bookId") int bookId,
                                   Model model) {
        model.addAttribute("featured", titles.get(4));
        model.addAttribute("selected", titles.get(bookId));
        return "selected";
    }

    @GetMapping("/store")
    public String showStorePage(Model model) {
        addCartAttributes(model, false);
        return "store";
    }

    @PostMapping("/addBookToCart")
    public String addBook(@RequestParam("bookId") int index) {
        titles.get(index).addToCart();
        return "redirect:/store";
    }

    @PostMapping("/removeBookFromCart")
    public String removeBook(@RequestParam("bookId") int index) {
        titles.get(index).removeFromCart();
        return "redirect:/cart";
    }

    @PostMapping("/clearCart")
    public String clearCart() {
        for (Book book : titles) {
            book.removeAllFromCart();
        }
        return "redirect:/store";
    }

    @GetMapping("/cart")
    public String showCartPage(Model model) {
        addCartAttributes(model, true);
        return "cart";
    }

    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        double totalPrice = 0;
        for (Book title : titles) {
            totalPrice += title.getInCart() * title.price;
        }
        model.addAttribute("totalPrice", totalPrice);
        return "checkout";
    }

    // Helper method to put the current inventory into the model for the page.
    // If onlyCart is true, filter the list down to just what's in the cart.
    private void addCartAttributes(Model model, boolean onlyCart) {
        List<Book> titles = this.titles;
        if (onlyCart) {
            titles = titles.stream().filter(book -> book.getInCart() > 0).toList();
        }

        int total = 0;
        for (Book title : titles) {
            total += title.getInCart();
        }

        model.addAttribute("total", total);
        model.addAttribute("books", titles);
    }
}
