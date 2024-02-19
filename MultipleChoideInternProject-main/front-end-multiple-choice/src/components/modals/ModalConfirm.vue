<script setup lang="ts">
import { VueFinalModal } from "vue-final-modal";

defineProps<{
  title?: string;
}>();

const emit = defineEmits<{
  (e: "confirm"): void;
  (e: "reject"): void;
}>();
</script>

<template>
  <VueFinalModal
    class="flex justify-center items-center"
    content-class="flex flex-col max-w-xl mx-4 p-6 bg-white dark:bg-gray-900 border dark:border-gray-700 rounded-lg space-y-2 w-1/3 h-60 justify-between"
  >
    <div class="text-3xl font-bold text-center">
      {{ title }}
    </div>
    <slot />
    <div class="flex flex-row justify-between items-center">
      <button
        class="mt-1 px-2 border rounded-xl w-28 py-2 bg-green-600"
        @click="emit('confirm')"
      >
        Confirm
      </button>
      <button
        class="mt-1 px-2 border bg-gray-500 w-28 py-2 rounded-xl"
        @click="emit('reject')"
      >
        Cancel
      </button>
    </div>
  </VueFinalModal>
</template>
