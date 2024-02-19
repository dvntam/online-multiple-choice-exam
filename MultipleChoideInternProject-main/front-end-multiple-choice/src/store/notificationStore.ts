import { defineStore } from "pinia";
type NotificationState = {
  message: string | null;
  type: string | null;
  show: boolean;
  timeout: number;
};
export const useNotificationStore = defineStore("notification", {
  state: (): NotificationState => ({
    message: null,
    type: "error",
    show: false,
    timeout: 3000,
  }),
  getters: {
    isShow: (state) => {
      return state.show;
    },
  },
  actions: {
    openError(message: string) {
      this.message = message;
      this.show = true;
      this.type = "error";
    },
    openSuccess(message: string) {
      this.message = message;
      this.show = true;
      this.type = "success";
    },
    clearMessage() {
      this.message = null;
      this.show = false;
    },
  },
});
