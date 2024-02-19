/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{vue,js,ts,jsx,tsx}"],
  theme: {
    container: {
      screens: {
        lg: "72rem",
        xl: "84rem",
      },
    },
    extend: {
      lineHeight: {
        13: "7rem",
      },
      letterSpacing: {
        wide: "0.0015em",
        wider: "0.0025em",
      },
      fontSize: {
        sm: "15px",
        "2xl": "23px",
        "3xl": "33px",
      },
      colors: {
        client: "#FFF5F4",
        primary: "#FAA292",
        secondary: "F1F1F1",
        header: "#002634",
        title: "#002634",
        content: "#606060",
        side: "#E5E5E5",
        darkblue: "#0089BB",
        lightblue: "#00D1FF",
      },

      container: {
        center: true,
      },
      maxWidth: {
        "1/4": "25%",
        "1/2": "50%",
        "3/4": "75%",
        "home-box": "645px",
      },
      minHeight: {
        main: "75vh",
        slider: "80vh",
        "page-header": "200px",
      },
      maxHeight: {
        feature: "543px",
        sidebar: "260px",
      },
      strokeWidth: {
        0.5: 0.5,
        1.5: 1.5,
        2.5: 2.5,
      },
    },
  },
  plugins: [],
};
