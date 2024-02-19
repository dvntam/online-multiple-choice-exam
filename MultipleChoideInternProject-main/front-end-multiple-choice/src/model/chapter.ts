import { Lesson } from "./lesson";
export interface Chapter {
  chapterId: Number;
  description: String;
  lessons: Array<Lesson>;
}
