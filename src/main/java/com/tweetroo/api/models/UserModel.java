package com.tweetroo.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indica ao banco que isso é uma entidade a ser mapeada
@Table(name = "users") // Permite escolher o nome da tabela

public class UserModel {

    @Id // Identifica que é o id, a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estratégia gerar IDs
    private Long id;

    @Column(nullable = false) // Coluna da tabela + constraints
    private String avatar;

    @Column(nullable = false, length = 100)
    private String username;
}
