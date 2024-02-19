<template>
  <Navigation />
  <div
    @click="manuallyQuestion()"
    class="fixed top-28 right-10 bg-slate-400 text-xl p-5 font-semibold rounded-xl"
    :class="isShow == false ? 'hidden' : 'show'"
  >
    Need more<span class="px-1 text-red-600">{{
      numQuestion - form.length
    }}</span>
    questions
  </div>
  <div class="w-2/3 mx-auto">
    <p class="text-xl font-semibold text-orange-500:">Options</p>
    <div class="flex flex-row space-x-4">
      <button
        @click="automaticallyExam"
        class="px-5 py-4 bg-orange-500 rounded-xl"
      >
        Automatically Exam
      </button>
      <button
        class="px-5 py-4 bg-lightblue rounded-xl"
        @click="getAllQuestionBySubject()"
      >
        Manually Exam
      </button>
    </div>
  </div>
  <div class="w-4/6 ml-96 flex flex-col items-center">
    <h1 class="text-3xl font-bold">List Question</h1>
    <div
      v-for="(question, index) in allQuestions"
      :key="question.questionId"
      class="rounded-xl border py-5 border-gray-200 mb-4 w-full"
    >
      <input
        type="checkbox"
        class="w-5 h-5 ml-5"
        :value="question"
        v-model="form"
      />
      <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">Question {{ index + 1 }}</p>
          <p class="text-red">{{ question.contentQuestion }}</p>
        </div>
        <div
          class="flex flex-row items-center justify-items-center space-x-2 w-full"
        >
          <p class="font-bold text-zinc-600 px-2">Level:</p>
          <div class="flex items-center">
            <p>{{ question.level }}</p>
          </div>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">A</p>
          <P>{{ question.answer[0].contentAnswer }}</P>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">B</p>
          <p>{{ question.answer[1].contentAnswer }}</p>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">C</p>
          <p>{{ question.answer[2].contentAnswer }}</p>
        </div>
        <div class="flex flex-col w-full">
          <p class="font-bold text-zinc-600 px-2">D</p>
          <P>{{ question.answer[3].contentAnswer }}</P>
        </div>
      </div>
    </div>
    <ModalsContainer />
  </div>
</template>
<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import { ref, onBeforeMount, watch } from "vue";
import { api } from "../../../services/http-common";
import Navigation from "../../Navigation.vue";
import { useNotificationStore } from "../../../store/notificationStore";
const allQuestions = ref();
const notificationStore = useNotificationStore();
const route = useRoute();
const router = useRouter();
const exam = ref();
const isShow = ref(false);
const form = ref([]);
const formQuestion = ref([]);
const getExamById = async () => {
  const { data } = await api.get(`/course/exam/${route.params.id}`);
  exam.value = data;
};
console.log(form.value);
const numQuestion = ref();

const getAllQuestionBySubject = async () => {
  const { data } = await api.get(`/questions/subject/${route.params.subject}`);
  allQuestions.value = data;
  isShow.value = !isShow.value;
  numQuestion.value = exam.value.numQuestion;
};
const manuallyQuestion = async () => {
  console.log(numQuestion.value);
  const num = numQuestion.value - form.value.length;
  console.log(num);
  if (num == 0) {
    await form.value.forEach((e) => {
      formQuestion.value.push({
        exam: {
          examId: exam.value.examId,
        },
        question: {
          questionId: e.questionId,
        },
      });
    });
    try {
      const { data } = await api.post(
        "/exam/question/add-multiple",
        formQuestion.value
      );
      console.log(data);
    } catch (e) {
      console.error(e);
    }
    notificationStore.openSuccess("Add question success");
    router.push({ name: "Dashboard.Exam.List" });
  } else {
    notificationStore.openError("Didn't choose enough questions");
  }
};
const automaticallyExam = async () => {
  try {
    await api.post(
      `/exam/create-test/num=${exam.value.numQuestion}/subject=${route.params.subject}/exam-id=${exam.value.examId}`
    );
    notificationStore.openSuccess("Create success");
    router.push({ name: "Dashboard.Exam.List" });
  } catch (e) {
    notificationStore.openError(
      "The thread bank is not enough, please create it manually"
    );
  }
};
watch(form.value, () => {
  console.log(form.value);
});
onBeforeMount(() => {
  getExamById();
});
</script>
