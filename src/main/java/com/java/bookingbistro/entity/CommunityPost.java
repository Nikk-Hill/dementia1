package com.java.bookingbistro.entity;

import com.java.bookingbistro.model.enums.BookingRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "COMMUNITY_POST")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityPost {
    @Id
    @Column(name = "COMMUNITY_POST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer communityPostId;

    @Column(name = "POST_HEADER")
    private String postHeader;

    @Column(name = "POST_CONTENT")
    private String postContent;

    @Column(name = "DATE")
    private LocalTime date;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "USER_ID")
    private Integer userId;




}
