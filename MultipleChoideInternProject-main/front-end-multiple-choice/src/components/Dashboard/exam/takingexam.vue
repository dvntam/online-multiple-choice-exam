<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import { ref, Ref, onBeforeMount, onMounted, watch } from "vue";
import { useAuthStore } from "../../../store/authStore";
import { useNotificationStore } from "../../../store/notificationStore";
import { ModalsContainer, useModal } from "vue-final-modal";
import ModalConfirm from "../../modals/ModalConfirm.vue";
import { api } from "../../../services/http-common";
import Navigation from "../../Navigation.vue";

const notificationStore = useNotificationStore();
const authStore = useAuthStore();
const route = useRoute();
const exam = ref();
const second = ref();
const timeCountDown = ref();
const allQuestions = ref();
const router = useRouter();
const resultAnswer = ref({});
const userAswer = ref({
  exam: {
    examId: route.params.id,
  },
  user: {
    userId: 0,
  },
  answerDtos: [
    {
      answer: {
        answerId: "",
      },
      status: "null",
    },
  ],
});
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
  size: 10,
});
const coundownInterval = setInterval(() => {
  if (second.value > 0) {
    second.value--;
  }
}, 1000);
function timeToSeconds(time: any) {
  const parts = time.split(":");
  const hours = parseInt(parts[0]) || 0;
  const minutes = parseInt(parts[1]) || 0;
  const seconds = parseInt(parts[2]) || 0;
  return hours * 3600 + minutes * 60 + seconds;
}
function getTime() {
  const hours = Math.floor(second.value / 3600);

  const minutes = Math.floor((second.value % 3600) / 60);
  const seconds = second.value % 60;
  return `${hours}:${minutes < 10 ? "0" : ""}${minutes}:${
    seconds < 10 ? "0" : ""
  }${seconds}`;
}
const submitExam = async () => {
  if (authStore.isAuthorized) {
    userAswer.value.user.userId = authStore.id;
    console.log(userAswer.value);
    try {
      const { data } = await api.post("/user-answers/create", userAswer.value);
      console.log(data);
      resultAnswer.value = data;
      clearInterval(coundownInterval);
    } catch (e) {
      console.error(e);
    }
  }
};
// console.log(resultAnswer.value !== "" ? "true" : "false");
const getExamByIdofCourse = async () => {
  const { data } = await api.get(`/course/exam/${route.params.id}`);
  exam.value = data;
  second.value = timeToSeconds(exam.value.duration);
  examName.value = exam.value.examName;
};
const examName = ref();
const getAllQuestions = async () => {
  try {
    const data = await api.get(
      `/exam/questions/exam-id=${route.params.id}?page=0&size=50`
    );
    allQuestions.value = data.data.content;
    console.log(allQuestions.value);
    allQuestions.value.forEach(() => {
      userAswer.value.answerDtos.push({
        answer: {
          answerId: 1,
        },
        status: "null",
      });
    });
    userAswer.value.answerDtos.splice(0, 1);
  } catch (e) {
    console.error(e);
  }
};
// const getList = async (page: number = 0) => {
//   try {
//     searchForm.value.page = page;
//     // @ts-ignore
//     const params = new URLSearchParams(searchForm.value).toString();
//     let data = await api.get(
//       `/exam/questions/exam-id=${route.params.id}?${params}`
//     );

//     allQuestions.value = data.data.content;
//     response.value.current_page = data.data.number;
//     response.value.last_page = data.data.totalPages - 1;
//   } catch (e) {}
// };
const { open, close } = useModal({
  component: ModalConfirm,
  attrs: {
    title: "Cofirm!",

    async onConfirm() {
      try {
        submitExam();
        notificationStore.openSuccess("Submit Exam success.");
        close();
      } catch (e) {
        console.error(e);
      }
    },
    onReject() {
      close();
    },
  },
  slots: {
    default: `<p class="text-center font-semibold text-xl">
Are you sure you want to submit your essay?</p>`,
  },
});
watch(second, () => {
  timeCountDown.value = getTime();
  if (second.value === 0) {
    submitExam();
  }
});
const scrollToQuestion = (id) => {
  document.getElementById(id)?.scrollIntoView({ behavior: "smooth" });
};
onBeforeMount(async () => {
  await getExamByIdofCourse();
  await getAllQuestions();
  clearInterval;
});
window.onpopstate = function (event) {
  if (confirm("System will automatically submit") == true) {
    submitExam();
    notificationStore.openSuccess("Submited exam");
  }
};
// onMounted(async () => {
//   await getAllQuestions();
// });
</script>
<template>
  <Navigation />
  <div class="flex flex-col w-full">
    <p class="text-3xl font-semibold text-center">Exam{{ examName }}</p>
    <template v-if="Object.keys(resultAnswer).length !== 0">
      <div
        class="w-1/2 flex flex-col mx-auto p-5 rounded-xl border border-gray-400"
      >
        <div
          v-for="(question, index) in allQuestions"
          :key="question.question.questionId"
          :id="index"
          class="rounded-xl border py-5 border-gray-200 mb-4"
        >
          <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
            <div class="flex flex-col w-full col-span-2 text-xl font-semibold">
              <p class="font-bold text-zinc-600 px-2">
                Question {{ index + 1 }}:
              </p>

              <p
                v-if="resultAnswer.userAnswer[index].status == 'false'"
                class="text-red-500"
              >
                {{ question.question.contentQuestion }}
              </p>
              <p v-else class="text-green-600">
                {{ question.question.contentQuestion }}
              </p>
            </div>

            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">A</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[0].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[0].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">B</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[1].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[1].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">C</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[2].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[2].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">D</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[3].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[3].contentAnswer }}
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="flex items-center py-5 flex-col w-1/6 space-y-4 fixed top-20 right-20 rounded-xl overflow-hidden shadow-xl bg-gray-100"
      >
        <p class="text-2xl font-semibold">Result</p>
        <p
          v-if="resultAnswer.resultExam.point > 60"
          class="text-green-600 text-4xl font-bold"
        >
          {{ resultAnswer.resultExam.point }}
        </p>
        <p v-else class="text-red-600 text-4xl font-bold">
          {{ resultAnswer.resultExam.point }}
        </p>
        <router-link
          :to="{
            name: 'Exam.ReultExam',
            params: { id: exam.examId },
          }"
          class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-white font-semibold px-3 py-2 w-32 rounded-xl text-center"
        >
          View Result</router-link
        >
      </div>
    </template>
    <template v-else>
      <form
        @submit.prevent="submitExam()"
        class="w-1/2 flex flex-col mx-auto p-5 rounded-xl border border-gray-400"
      >
        <div
          v-for="(question, index) in allQuestions"
          :key="question.question.questionId"
          :id="index"
          class="rounded-xl border py-5 border-gray-200 mb-4"
        >
          <div class="grid grid-cols-2 gap-6 px-5 mt-4 w-full">
            <div class="flex flex-col w-full col-span-2 text-xl font-semibold">
              <p class="font-bold text-zinc-600 px-2">
                Question{{ index + 1 }}:
              </p>
              <p>{{ question.question.contentQuestion }}</p>
            </div>

            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">A</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[0].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[0].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">B</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[1].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[1].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">C</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[2].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[2].contentAnswer }}
                </p>
              </div>
            </div>
            <div class="flex flex-col w-full">
              <p class="font-bold text-zinc-600 px-2">D</p>
              <div
                class="flex flex-row items-center space-x-2 text-lg font-semibold"
              >
                <input
                  type="radio"
                  :value="question.question.answer[3].answerId"
                  :name="`aswer-radio-${index}`"
                  v-model="userAswer.answerDtos[index].answer.answerId"
                  class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
                />
                <p class="capitalize">
                  {{ question.question.answer[3].contentAnswer }}
                </p>
              </div>
            </div>
          </div>
        </div>
        <button
          type="submit"
          class="bg-gradient-to-r from-sky-800 from-10% to-purple-700 w-28 py-2 mx-auto rounded-xl text-white"
        >
          Submit
        </button>
      </form>
      <div
        class="flex flex-col w-fit fixed top-20 right-20 rounded-xl overflow-hidden shadow-xl bg-gray-200"
      >
        <p
          class="w-full text-3xl font-bold bg-gradient-to-r from-sky-800 from-10% to-purple-700 text-center py-2 text-white"
        >
          Time:
          <span>{{ timeCountDown }}</span>
        </p>
        <div class="grid grid-cols-5 gap-5 mt-5 py-5 px-5">
          <div
            class="bg-white rounded-md w-10 h-10 flex flex-col items-center justify-center font-semibold"
            v-for="(question, index) in userAswer.answerDtos.length"
            :key="`question-${index}`"
            @click="scrollToQuestion(index)"
          >
            <p
              v-if="userAswer.answerDtos[index].answer.answerId != 1"
              class="bg-gray-500 w-full h-full text-center rounded-xl"
            >
              {{ index + 1 }}
            </p>
            <p v-else class="rounded-xl">{{ index + 1 }}</p>
          </div>
        </div>
        <button
          @click="submitExam()"
          class="mb-2 bg-gradient-to-r from-sky-800 from-10% to-purple-700 w-28 py-2 mx-auto rounded-xl text-white"
        >
          Submit
        </button>
      </div>
    </template>
  </div>
  <ModalConfirm />
</template>
