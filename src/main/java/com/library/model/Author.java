package com.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String country;
	
	private LocalDate birthDate;

    private String photo;

	@OneToMany(
			mappedBy = "author",
			cascade = CascadeType.ALL
	)
	private Set<Book> books = new HashSet<>();
	
}
