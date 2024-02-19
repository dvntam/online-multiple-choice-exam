package com.fpt.onlineTest.dto;

import com.fpt.onlineTest.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChapterDto {
    private Integer chapterId;
    private String description;
    private List<LessonDto> lessons;
}
