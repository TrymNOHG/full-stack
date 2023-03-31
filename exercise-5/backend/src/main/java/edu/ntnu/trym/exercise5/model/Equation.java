package edu.ntnu.trym.exercise5.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This class represents an equation. It takes in the intrinsic parts of a simple expression.
 *
 * @author Trym Hamer Gudvangen
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name = "equations")
public class Equation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "expression", nullable = false)
    private String expression;

    @Column(name = "result", nullable = false)
    private double result;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
}
