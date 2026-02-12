package com.iclass.video.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "area_video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
