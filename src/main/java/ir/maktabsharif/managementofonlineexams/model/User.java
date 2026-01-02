package ir.maktabsharif.managementofonlineexams.model;

import ir.maktabsharif.managementofonlineexams.model.enums.Role;
import ir.maktabsharif.managementofonlineexams.model.enums.UserStatus;
import ir.maktabsharif.managementofonlineexams.model.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseModel<Long> {
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "student")
    private Set<StudentExam> takenExams = new HashSet<>();

    public Long Id() {
        return id;
    }
}
