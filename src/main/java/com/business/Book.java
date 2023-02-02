package com.business;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;

    @NonNull
    private String name;

    @NonNull
    private String summary;

    private int rating;

}
