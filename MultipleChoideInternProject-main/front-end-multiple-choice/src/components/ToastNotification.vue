<template>
  <Notification
    refKey="successNotification"
    class="flex flex-row px-8 py-3 w-80 rounded-lg shadow-xl bg-gray-100 right-0 fixed top-0 space-x-4 items-center"
  >
    <Icon
      name="check"
      v-if="notificationStore.type === 'success'"
      class="w-5 h-5 bg-green-400 rounded-full fill-green-600 p-1"
    />
    <Icon
      name="uncheck"
      v-else
      class="w-5 h-5 bg-red-400 rounded-full p-1 fill-red-600"
    />
    <div class="space-y-4 w-full flex flex-col">
      <div
        v-if="notificationStore.type === 'success'"
        class="font-medium text-green-600"
      >
        Success!
      </div>
      <div v-else class="font-medium text-red-600">Error!</div>
      <div
        v-if="notificationStore.show && notificationStore.message"
        class="text-slate-500 mt-1"
      >
        {{ notificationStore.message }}
      </div>
    </div>
  </Notification>
</template>

<script setup lang="ts">
import { provide, ref, watch } from "vue";
import { useNotificationStore } from "../store/notificationStore";
import { useRoute } from "vue-router";
import Icon from "../icons/ClientDashboard.vue";
const notificationStore = useNotificationStore();
const successNotification = ref();
const route = useRoute();

provide("bind[successNotification]", (el: any) => {
  successNotification.value = el;
});

watch(
  notificationStore,
  (state) => {
    if (state.show && state.message !== "Có lỗi rồi.") {
      successNotification.value.showToast();
      setTimeout(() => {
        successNotification.value.hideToast();
        notificationStore.clearMessage();
      }, state.timeout);
    }
  },
  { deep: true }
);
</script>
