package org.example.springweb1.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springweb1.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> itmes = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> childs = new ArrayList<>();


    public void addChildCategory(Category child) {
        this.childs.add(child);
        child.setParent(this);
    }


}
