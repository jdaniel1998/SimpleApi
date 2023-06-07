package com.example.BondApi2.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, description, public_key, api_key;

    @Transient
    private String jid, image_url;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Server server;

}
