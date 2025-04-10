package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


/*
    @Entity: Bu sınıfın bir JPA varlığı olduğunu belirtir.
    @Table(name = "tasks"): Veritabanında bu varlık için oluşturulacak tablonun adını belirtir.
    @Data, @NoArgsConstructor, @AllArgsConstructor: Lombok kütüphanesinin anotasyonlarıdır. Getter, setter, toString, equals, hashCode metotlarını otomatik olarak oluşturur ve parametresiz ile tüm parametreleri içeren yapıcı metotları ekler.
    @Id ve @GeneratedValue: id alanının birincil anahtar olduğunu ve değerinin otomatik olarak oluşturulacağını belirtir.
    @Column(nullable = false): İlgili alanın boş olamayacağını belirtir.
    @Enumerated(EnumType.STRING): status alanının enum değerlerini string olarak saklar.
    @PrePersist ve @PreUpdate: Veritabanı işlemleri öncesinde createdAt ve updatedAt alanlarını otomatik olarak ayarlar.
 */


@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Status {
        TO_DO,
        IN_PROGRESS,
        DONE
    }
}
