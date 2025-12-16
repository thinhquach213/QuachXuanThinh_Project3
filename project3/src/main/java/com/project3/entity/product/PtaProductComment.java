package com.project3.entity.product;

import com.project3.entity.user.PtaUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Pta_product_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PtaProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private int star; // 1–5

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private PtaProduct product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private PtaUser user;
}