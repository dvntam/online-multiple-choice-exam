import { Chapter } from "./chapter";
export interface Course {
  courseId: Number;
  courseName: String;
  imageCourse: String;
  numberStudent: Number;
  status: Boolean;
  subject: String;
  chapters: Array<Chapter>;
}
