package com.dev.notebook.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Entity
@Table(name = "tbl_confirmations")
public class Confirmation extends BaseAuditable{
    @Column(name = "confirmation_key", nullable = false)  // Renamed from 'key' to 'confirmation_key'
    private String confirmationKey;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;

    public Confirmation(User user) {
        this.confirmationKey = UUID.randomUUID().toString();
        this.user = user;
    }
}
