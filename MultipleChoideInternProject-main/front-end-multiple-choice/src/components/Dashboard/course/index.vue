<template>
  <Navigation />
  <SideBarDasboard />
  <div class="w-4/6 ml-96">
    <router-link
      class="bg-lightblue px-3 py-2 rounded-xl flex-row flex items-center w-fit"
      :to="{ name: 'Dashboard.Course.Create' }"
    >
      Add new course
      <Icon name="plus" class="ml-2 w-5 h-5" />
    </router-link>
    <table
      class="w-full text border-collapse border border-slate-400 rounded-xl overflow-hidden"
    >
      <caption class="caption-top uppercase text-3xl font-bold">
        Courses
      </caption>
      <thead class="bg-gray-50 rounded-xl overflow-hidden">
        <tr class="uppercase">
          <th scope="col" class="border border-slate-300 col-span-1 px-6 py-4">
            ID
          </th>
          <th scope="col" class="border border-slate-300 px-6 py-4">
            Course name
          </th>
          <th scope="col" class="border border-slate-300 px-6 py-4">Teacher</th>
          <th scope="col" class="border border-slate-300 px-6 py-4">Image</th>
          <th scope="col" class="border border-slate-300 px-6 py-4">
            Num of students
          </th>
          <th scope="col" class="border border-slate-300 px-6 py-4">Exam</th>
          <th scope="col" class="border border-slate-300 px-6 py-4">Action</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="course in allCouses"
          :key="Number(course.courseId)"
          class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600 text-justify"
        >
          <td class="w-20 px-6 py-4">{{ course.courseId }}</td>
          <td class="px-6 py-4">
            {{ course.courseName }}
          </td>
          <td class="line-clamp-4 px-6 py-4">
            {{ course.fullName }}
          </td>
          <td class="px-6 py-4">
            <img :src="String(course.imageCourse)" class="w-40 mx-auto" />
          </td>
          <td class="px-6 py-4">
            {{ course.numberStudent }}
          </td>
          <td class="px-6 py-4 grid grid-cols-2 gap-2">
            <router-link
              :to="{
                name: 'Dashboard.Question.Create',
                params: { subject: String(course.subject) },
              }"
              class="flex flex-col space-y-2 justify-center items-center hover:bg-slate-200 py-2 px-2 rounded-xl bg-lightblue"
            >
              Add Question
            </router-link>
            <router-link
              :to="{
                name: 'Dashboard.Question.Index',
                params: { subject: String(course.subject) },
              }"
              class="flex flex-col space-y-2 justify-center items-center hover:bg-slate-200 py-2 px-6 rounded-xl bg-lightblue"
            >
              Questions
            </router-link>
            <router-link
              :to="{
                name: 'Dashboard.Exam.Create',
                params: { idcourse: String(course.courseId) },
              }"
              class="flex flex-col space-y-2 justify-center items-center hover:bg-slate-200 py-2 px-2 rounded-xl bg-lightblue"
            >
              Create Exam
            </router-link>
            <router-link
              :to="{
                name: 'Dashboard.Exam.List',
              }"
              class="flex flex-col space-y-2 justify-center items-center hover:bg-slate-200 py-2 px-2 rounded-xl bg-lightblue"
            >
              Exams
            </router-link>
          </td>
          <td class="px-6 py-4">
            <div class="flex flex-row space-x-4 w-full justify-center">
              <router-link
                :to="{
                  name: 'Dashboard.Course.Update',
                  params: { id: Number(course.courseId) },
                }"
              >
                <Icon
                  name="edit"
                  class="w-6 h-6 stroke-green-400 cursor-pointer"
                />
              </router-link>
              <Icon
                @click="deleteCourseItem(course.courseId)"
                name="delete"
                class="w-6 h-6 fill-red-500 cursor-pointer"
              />
            </div>
          </td>
        </tr>
      </tbody>
    </table>
    <Pagination
      @get-list="getList"
      :last-page="response.last_page"
      :current-page="response.current_page"
    />
    <ModalsContainer />
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { Course } from "../../../model/course";
import { api } from "../../../services/http-common";
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import { useRoute } from "vue-router";
import Icon from "../../../icons/ClientDashboard.vue";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import SideBarDasboard from "../SideBarDashboard.vue";
import Pagination from "../../Pagination.vue";
const allCouses: Ref<Array<Course>> = ref([]);
import { useNotificationStore } from "../../../store/notificationStore";
const notificationStore = useNotificationStore();
const currentIdCourse: Ref<Number | undefined> = ref(undefined);

interface ResponseData {
  last_page: number;
  current_page: number;
}
interface SearchForm {
  page: number;
  size: number;
}
const response: Ref<ResponseData> = ref({
  last_page: 1,
  current_page: 0,
});
const searchForm: Ref<SearchForm> = ref({
  page: 0,
  size: 4,
});
const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Xác nhận!",

    async onConfirm() {
      try {
        await api.delete(`/courses/delete-course/${currentIdCourse.value}`);
        await getAllCourses();
        notificationStore.openSuccess("Xóa thành công");
      } catch (e) {
        notificationStore.openError("Xóa không thành công");
        console.error(e);
      }
      close();
    },
    onReject() {
      currentIdCourse.value = undefined;

      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">Bạn có chắc chắn muốn xóa</p>`,
  },
});

const deleteCourseItem = async (id: Number) => {
  currentIdCourse.value = id;
  open();
};
const getAllCourses = async () => {
  try {
    const data = await api.get("/courses");
    allCouses.value = data.data.content;
    console.log(allCouses.value);
  } catch (e) {
    console.error(e);
  }
};
const getList = async (page: number = 0) => {
  try {
    searchForm.value.page = page;
    // @ts-ignore
    const params = new URLSearchParams(searchForm.value).toString();
    let data = await api.get(`/courses?${params}`);
    allCouses.value = data.data.content;
    response.value.current_page = data.data.number;
    response.value.last_page = data.data.totalPages - 1;
  } catch (e) {}
};
onBeforeMount(() => {
  getAllCourses();
});
onMounted(() => {
  getList();
});
</script>
