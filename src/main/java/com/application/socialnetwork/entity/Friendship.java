package com.application.socialnetwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Friendship extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "first_friend_id")
    private User firstFriend;

    @ManyToOne
    @JoinColumn(name = "second_friend_id")
    private User secondFriend;

    private LocalDate dateOfFriendship;

}
