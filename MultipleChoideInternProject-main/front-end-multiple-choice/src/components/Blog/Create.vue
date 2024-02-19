<script lang="ts" setup>
import Navigation from "../../components/Navigation.vue";
import SideBar from "../../components/SideBar.vue";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import { api } from "../../services/http-common";
import { ref, Ref, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import { useNotificationStore } from "../../store/notificationStore";
import { useVuelidate } from "@vuelidate/core";
import { required, minLength, maxLength } from "@vuelidate/validators";
import { useAuthStore } from "../../store/authStore";
const authStore = useAuthStore();
const $externalResults = ref({});
const form = ref({
  titleBlog: "",
  contentBlog: "",
  numberVisitors: "",
  user: {
    userId: authStore.userId,
  },
});
const rules = {
  titleBlog: { required, minLength: minLength(10), maxLength: maxLength(256) },
  contentBlog: {
    required,
    minLength: minLength(100),
    maxLength: maxLength(5000),
  },
};
const validate = useVuelidate(rules, form, { $externalResults });

const validationMessage = (error: any, text: string) => {
  if (error) {
    return error.replace("Value", text);
  }
  return error;
};
const validateFormBlog = () => {
  validate.value.$clearExternalResults();
  validate.value.$touch();
  if (validate.value.$invalid) {
    notificationStore.openError("Kiểm tra lại thông tin trong các trường.");
    return;
  }
};
const notificationStore = useNotificationStore();
const router = useRouter();

const createBlog = async () => {
  try {
    await api.post("/blogs/add", form.value);
    notificationStore.openSuccess("Thêm thành công");
    router.push("/blog");
  } catch (e) {
    console.error(e);
    notificationStore.openSuccess("Thêm không thành  công");
  }
};
</script>
<template>
  <Navigation />
  <SideBar />
  <div class="flex flex-col justify-center container">
    <form
      @submit.prevent="createBlog"
      @keydown="validateFormBlog"
      class="px-10 mt-20 py-10 border flex flex-col justify-center space-y-4 text-center rounded-xl"
    >
      <p class="uppercase text-2xl font-bold">Create blog</p>
      <div class="flex flex-col items-start">
        <label>Title <span class="text-red-600 font-bold">*</span></label>
        <input
          v-model="form.titleBlog"
          class="w-1/2 rounded-xl border border-black h-12 px-4"
          required
          type="text"
        />
        <template v-if="validate.titleBlog.$error">
          <div
            v-for="(error, index) in validate.titleBlog.$errors"
            :key="index"
            class="text-red-500 mt-2 italic text-sm"
          >
            {{ validationMessage(error.$message, "Tên bài viết") }}
          </div>
        </template>
      </div>
      <div class="flex flex-col items-start">
        <label
          >Content
          <span class="text-red-600 font-bold items-start">*</span></label
        >
        <template v-if="validate.contentBlog.$error">
          <div
            v-for="(error, index) in validate.contentBlog.$errors"
            :key="index"
            class="text-red-500 mt-2 italic text-sm"
          >
            {{ validationMessage(error.$message, "Nội dung môn học") }}
          </div>
        </template>
      </div>
      <ckeditor v-model="form.contentBlog" :editor="ClassicEditor"></ckeditor>
      <button
        class="text-white bg-gradient-to-r from-sky-800 from-10% to-purple-700 px-5 py-2 w-32 rounded-xl"
      >
        Create
      </button>
    </form>
  </div>
</template>
<style>
.ck-editor__editable_inline {
  min-height: 600px;
}
</style>
