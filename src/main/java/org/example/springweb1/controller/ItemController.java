package org.example.springweb1.controller;


import lombok.RequiredArgsConstructor;
import org.example.springweb1.domain.item.Book;
import org.example.springweb1.domain.item.Item;
import org.example.springweb1.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/items/new")
    public String item(Model model) {
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm) {

        Book book = Book.CreateBook(bookForm);
        itemService.saveItem(book);

        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> itmes = itemService.findItems();
        model.addAttribute("items",itmes);

        return "items/itemList";
    }

    @GetMapping("/items/{itemid}/edit")
    public String updateItemForm(@PathVariable("itemid") Long itemid,Model model) {
        Book book = (Book) itemService.itemOne(itemid);

        BookForm bookForm = new BookForm();

        bookForm.setId(book.getId());
        bookForm.setName(book.getName());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(book.getStockQuantity());
        bookForm.setAuthor(bookForm.getAuthor());
        bookForm.setIsbn(book.getIsbn());

        model.addAttribute("form",bookForm);

        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemid}/edit")
    public String updateItem(@ModelAttribute("form") BookForm form) {

        Book book = new Book();
        change(form, book);

        itemService.saveItem(book);

        return "redirect:/items";
    }

    private static void change(BookForm form, Book book) {
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
    }


}
