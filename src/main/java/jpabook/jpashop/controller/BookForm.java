package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;

@Getter @Setter
public class BookForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
