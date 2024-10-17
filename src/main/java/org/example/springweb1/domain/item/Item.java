package org.example.springweb1.domain.item;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springweb1.domain.Category;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long Id;

    private String name;

    private int price; //상품 가격
    
    private int stockQuantity;

    @ManyToMany(mappedBy = "itmes")
    private List<Category> categories = new ArrayList<>();


    /*
    *  재고 증가
    * */
    public void addStore(int quantity) {
        this.stockQuantity += stockQuantity;
    }

    /**
     * 재고 감소
     * @param quantity
     */
    public void removeStore(int quantity) {
        int resultStock = this.stockQuantity - quantity;
        if (resultStock < 0) {
            throw new NotEnuoughStockException("need more stock");
        }
        this.stockQuantity = resultStock;
    }

}
