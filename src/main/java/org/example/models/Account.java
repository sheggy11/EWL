package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "med_center_id")
    private MedCenter medCenter;


    @Column(nullable = false, length = 64)
    private String password;


    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    public boolean hasRole(String roleName) {
        for (Role role : roles) {
            if (role.getRoleName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

}