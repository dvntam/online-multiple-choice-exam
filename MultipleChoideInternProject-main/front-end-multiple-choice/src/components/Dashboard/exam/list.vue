<template>
  <Navigation />
  <SideBarDashboard />
  <div class="w-2/3 mx-auto flex flex-col items-center">
    <div class="flex flex-row justify-between w-full">
      <select
        v-model="courseId"
        class="bg-gray-50 border w-1/4 border-gray-300 text-gray-900 mb-6 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
      >
        <option v-for="course in allCourse" :value="course.courseId">
          {{ course.courseName }}
        </option>
      </select>

      <button
        class="text-white bg-gradient-to-r from-sky-800 from-10% to-purple-700 rounded-xl px-4 py-1 text-center text-lg font-semibold"
        @click="downloadExecl"
      >
        Export All Result
      </button>
    </div>
    <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
      <thead
        class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400 w-full"
      >
        <tr>
          <th scope="col" class="px-6 py-3">Exam id</th>
          <th scope="col" class="px-6 py-3">Num Question</th>
          <th scope="col" class="px-6 py-3">Duration</th>
          <th scope="col" class="px-6 py-3">Action</th>
        </tr>
      </thead>
      <tbody>
        <tr
          class="bg-white border-b dark:bg-gray-900 dark:border-gray-700"
          v-for="exam in listExams"
          :key="exam.examId"
        >
          <th
            scope="row"
            class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
          >
            {{ exam.examId }}
          </th>
          <td class="px-6 py-4">{{ exam.numQuestion }}</td>
          <td class="px-6 py-4">{{ exam.duration }}</td>

          <td class="px-6 py-4 space-x-2">
            <button
              @click="deleteExamById(exam.examId)"
              href="#"
              class="font-medium text-red-600 hover:underline"
            >
              Delete
            </button>
            <button @click="downloadExam(exam.examId)" class="text-green-500">
              Export Exam
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script lang="ts" setup>
import { ref, Ref, onBeforeMount, onMounted, watch } from "vue";
import { api } from "../../../services/http-common";
import SideBarDashboard from "../SideBarDashboard.vue";
import Navigation from "../../Navigation.vue";
const listExams = ref();
const allCourse = ref();
const courseId = ref(4);

const downloadExecl = async () => {
  await api
    .get("/result-exam/export-file-excel", { responseType: "blob" })
    .then((response) => {
      const link = document.createElement("a");

      // Tell the browser to associate the response data to
      // the URL of the link we created above.
      link.href = window.URL.createObjectURL(new Blob([response.data]));

      // Tell the browser to download, not render, the file.
      link.setAttribute("download", "report.xlsx");

      // Place the link in the DOM.
      document.body.appendChild(link);

      // Make the magic happen!
      link.click();
    });
};
const downloadExam = async (examId) => {
  await api.get(`/export-file-excel/${examId}`).then((response) => {
    const link = document.createElement("a");

    // Tell the browser to associate the response data to
    // the URL of the link we created above.
    link.href = window.URL.createObjectURL(new Blob([response.data]));

    // Tell the browser to download, not render, the file.
    link.setAttribute("download", "exam.xlsx");

    // Place the link in the DOM.
    document.body.appendChild(link);

    // Make the magic happen!
    link.click();
  });
};
const getListExams = async () => {
  const { data } = await api.get(`/course=${courseId.value}/exams`);
  listExams.value = data;
  console.log(listExams.value);
};
const deleteExamById = async (id) => {
  console.log(id);
  await api.delete(`/course/exam/delete/${id}`);
};
const getAllCourse = async () => {
  const { data } = await api.get("/courses");
  allCourse.value = data.content;
  console.log(allCourse.value);
};
watch(courseId, () => {
  getListExams();
});
onMounted(() => {
  getListExams();
});
onBeforeMount(() => {
  getListExams();
  getAllCourse();
});
</script>
