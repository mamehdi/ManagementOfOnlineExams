package ir.maktabsharif.managementofonlineexams.model.base;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
public abstract class BaseModel <ID extends Number> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
    @Column(updatable = false)
    protected Instant createAt;

    @PrePersist
    public void prePersist() {
        createAt = Instant.now();
    }

    protected Instant updateAt;

    @PreUpdate
    public void preUpdate() {
        updateAt = Instant.now();
    }
}
