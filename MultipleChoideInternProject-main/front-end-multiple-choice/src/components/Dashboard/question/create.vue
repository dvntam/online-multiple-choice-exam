<template>
  <Navigation />
  <div class="flex justify-center">
    <form
      @submit.prevent="saveQuestions()"
      @keydown="validateFormQuestion()"
      class="w-3/5 px-20 rounded-md border-2 border-zinc-800 h-fit my-14"
    >
      <h1 class="flex uppercase justify-center font-medium text-2xl my-10">
        Create Bank Exam
      </h1>

      <div
        class="flex flex-col space-y-4 border border-gray-400 shadow-xl rounded-xl p-5"
        v-for="(formQuestion, index) in formQuestions"
        :key="formQuestion"
      >
        <Icon
          @click="removeQuestion(index)"
          name="delete"
          class="w-5 h-5 ml-5"
        />

        <div class="grid grid-cols-2 gap-6 px-5 mt-4">
          <div class="flex flex-col w-full">
            <p class="font-bold text-zinc-600 px-2">
              Question<span class="text-red-600 font-bold">*</span>
            </p>
            <textarea
              class="rounded-xl pl-2 border-2"
              v-model="formQuestion.contentQuestion"
              placeholder="Enter question"
              rows="4"
            />

            <template
              v-if="validate.$each.$response.$errors[index].contentQuestion"
            >
              <div
                v-for="(error, i) in validate.$each.$response.$errors[index]
                  .contentQuestion"
                :key="i"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error.$message, "Question") }}
              </div>
            </template>
          </div>
          <div
            class="flex flex-row items-center justify-items-center space-x-2"
          >
            <p class="font-bold text-zinc-600 px-2">
              Level <span class="text-red-600 font-bold">*</span>
            </p>
            <div class="flex items-center">
              <input
                id="default-radio-1"
                type="radio"
                value="easy"
                v-model="formQuestion.level"
                :name="`level-radio-${index}`"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Easy
              </label>
            </div>
            <div class="flex items-center">
              <input
                id="default-radio-2"
                type="radio"
                value="medium"
                v-model="formQuestion.level"
                :name="`level-radio-${index}`"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-2"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Medium</label
              >
            </div>
            <div class="flex items-center">
              <input
                type="radio"
                value="hard"
                v-model="formQuestion.level"
                :name="`level-radio-${index}`"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-2"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >Hard</label
              >
            </div>
          </div>
          <div class="flex flex-col space-y-2">
            <p class="font-bold text-zinc-600 px-2">
              Result 1 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              v-model="formQuestion.answer[0].contentAnswer"
              placeholder="Enter result 1"
            />

            <template
              v-if="validate.$each.$response.$errors[index].answer[index]"
            >
              <div
                v-for="(error, i) in validate.$each.$response.$errors[index]
                  .answer[index].$message[0]"
                :key="i"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error, "Question") }}
              </div>
            </template>
            <div class="flex flex-row space-x-4 items-center">
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >True
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="true"
                v-model="formQuestion.answer[0].status"
                :name="index + '0'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />

              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >False
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="false"
                v-model="formQuestion.answer[0].status"
                :name="index + '0'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
            </div>
          </div>

          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Result 2 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              v-model="formQuestion.answer[1].contentAnswer"
              id=""
              placeholder="Enter result"
            />
            <template
              v-if="validate.$each.$response.$errors[index].answer[index]"
            >
              <div
                v-for="(error, i) in validate.$each.$response.$errors[index]
                  .answer[index].$message[1]"
                :key="i"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error, "Question") }}
              </div>
            </template>
            <div class="flex flex-row space-x-4 items-center">
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >True
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="true"
                v-model="formQuestion.answer[1].status"
                :name="index + '1'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >False
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="false"
                v-model="formQuestion.answer[1].status"
                :name="index + '1'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
            </div>
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Result 3 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              id=""
              placeholder="Enter result"
              v-model="formQuestion.answer[2].contentAnswer"
            />
            <template
              v-if="validate.$each.$response.$errors[index].answer[index]"
            >
              <div
                v-for="(error, i) in validate.$each.$response.$errors[index]
                  .answer[index].$message[2]"
                :key="i"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error, "Question") }}
              </div>
            </template>
            <div class="flex flex-row space-x-4 items-center">
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >True
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="true"
                v-model="formQuestion.answer[2].status"
                :name="index + '2'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >False
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="false"
                v-model="formQuestion.answer[2].status"
                :name="index + '2'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
            </div>
          </div>
          <div class="flex flex-col">
            <p class="font-bold text-zinc-600 px-2">
              Result 4 <span class="text-red-600 font-bold">*</span>
            </p>
            <input
              class="h-12 rounded-xl w-full pl-2 border-2"
              name=""
              id=""
              v-model="formQuestion.answer[3].contentAnswer"
              placeholder="Enter result"
            />
            <template
              v-if="validate.$each.$response.$errors[index].answer[index]"
            >
              <div
                v-for="(error, i) in validate.$each.$response.$errors[index]
                  .answer[index].$message[3]"
                :key="i"
                class="text-red-500 mt-2 italic text-sm"
              >
                {{ validationMessage(error, "Question") }}
              </div>
            </template>
            <div class="flex flex-row space-x-4 items-center">
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >True
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="true"
                v-model="formQuestion.answer[3].status"
                :name="index + '3'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
              <label
                for="default-radio-1"
                class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                >False
              </label>
              <input
                checked
                id="default-radio-2"
                type="radio"
                value="false"
                v-model="formQuestion.answer[3].status"
                :name="index + '3'"
                class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
              />
            </div>
          </div>
        </div>
      </div>
      <div
        @click="addQuestion"
        class="rounded-xl px-3 mt-4 py-2 text-white bg-lightblue hover:bg-blue-600 w-40"
      >
        Add Question
      </div>
      <div class="flex-row gap-10 my-10 flex justify-center h-10">
        <button
          type="submit"
          class="rounded-md bg-green-300 w-20 hover:bg-green-600 font-bold text-white uppercase"
        >
          save
        </button>
        <button
          class="rounded-md bg-zinc-400 w-20 hover:bg-red-600 font-bold text-white uppercase"
        >
          cancel
        </button>
      </div>
    </form>
  </div>
</template>
<script lang="ts" setup>
import Navigation from "../../Navigation.vue";
import { ref, Ref } from "vue";
import { api } from "../../../services/http-common";
import { useNotificationStore } from "../../../store/notificationStore";
import { useRouter, useRoute } from "vue-router";
import Icon from "../../../icons/ClientDashboard.vue";
import { useVuelidate } from "@vuelidate/core";

import { required, minLength, maxLength, helpers } from "@vuelidate/validators";
const notificationStore = useNotificationStore();
const $externalResults = ref({});
// const = (value)=> value.forEach(element => {

// });

const rules = {
  $each: helpers.forEach({
    contentQuestion: {
      required,
      minLength: minLength(5),
      maxLength: maxLength(256),
    },
    level: {
      required,
    },
    answer: {
      $each: helpers.forEach({
        contentAnswer: {
          required,
          minLength: minLength(1),
          maxLength: maxLength(256),
        },
      }),
    },
  }),
};

const route = useRoute();
const router = useRouter();
const subject: Ref<String> = ref("");
subject.value = String(route.params.subject);

const formQuestions = ref([
  {
    contentQuestion: "",
    level: "medium",
    subject: subject.value,
    answer: [
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
    ],
  },
]);

let validate = useVuelidate(rules, formQuestions, {
  $externalResults,
});

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
const validateFormQuestion = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  console.log(validate.value);
  if (validate.value.$invalid) {
    notificationStore.openError("Check information fields.");
    return true;
  }

  return true;
};

const removeQuestion = (index: Number) => {
  formQuestions.value.splice(Number(index), 1);
};
const addQuestion = () => {
  validate = useVuelidate(rules, formQuestions, {
    $externalResults,
  });
  formQuestions.value.push({
    contentQuestion: "",
    level: "",
    subject: subject.value,
    answer: [
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
      {
        contentAnswer: "",
        status: "false",
      },
    ],
  });
};
const saveQuestions = async () => {
  if (validateFormQuestion())
    try {
      await api.post("/questions/create", formQuestions.value);
      notificationStore.openSuccess("Tạo Question thành công");
      await router.push(`/dashboard/subject/${subject.value}`);
    } catch (e) {
      console.error("Tạo Question thất bại");
    }
};
</script>
