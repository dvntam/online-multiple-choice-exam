<script lang="ts" setup>
import Icon from "../icons/ClientDashboard.vue";
interface Props {
  lastPage: number;
  currentPage: number;
}
defineProps<Props>();
const emit = defineEmits(["getList"]);
</script>
<template>
  <!-- BEGIN: Pagination -->
  <div
    v-if="lastPage > 0"
    class="intro-y col-span-12 flex flex-wrap sm:flex-row sm:flex-nowrap items-center py-10"
  >
    <nav class="w-full">
      <ul
        class="pagination flex justify-center items-center text-lg font-semibold space-x-2"
      >
        <li v-if="currentPage > 0" class="page-item">
          <button
            class="items-center rounded-full border-blue-600 border-2 px-1 flex"
            @click.prevent="emit('getList', 0)"
          >
            <Icon name="left" class="w-4 h-6" />
          </button>
        </li>
        <li v-if="currentPage > 0" class="page-item">
          <button
            class="items-center rounded-full border-blue-600 border-2 px-1 flex"
            @click.prevent="emit('getList', currentPage - 1)"
          >
            <Icon name="left" class="w-4 h-6" />
          </button>
        </li>
        <template v-for="page in lastPage">
          <li
            class="px-2 text-center rounded-full"
            :key="`page-${page}`"
            v-if="
              page === currentPage ||
              page === currentPage - 1 ||
              page === currentPage - 2 ||
              page === currentPage + 1 ||
              page === currentPage + 2
            "
            :class="
              page === currentPage
                ? 'border-2 border-red-600'
                : 'border-blue-700 border-2'
            "
          >
            <button @click.prevent="emit('getList', page)" class="page-link">
              {{ page }}
            </button>
          </li>
        </template>
        <li v-if="currentPage < lastPage" class="page-item">
          <button
            class="items-center rounded-full border-blue-600 border-2 px-1 flex"
            @click.prevent="emit('getList', currentPage + 1)"
          >
            <Icon name="right" class="w-4 h-6" />
          </button>
        </li>
        <li v-if="currentPage < lastPage" class="page-item">
          <button
            class="items-center rounded-full border-blue-600 border-2 px-1 flex"
            @click.prevent="emit('getList', lastPage)"
          >
            <Icon name="right" class="w-4 h-6" />
            <Icon name="right" class="w-4 h-6" />
          </button>
        </li>
      </ul>
    </nav>
  </div>
  <!-- END: Pagination -->
</template>
