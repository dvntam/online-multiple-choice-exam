<template>
  <div class="h-screen w-full" id="meetingSDKElement">
    <!-- Zoom Meeting SDK Rendered Here -->
  </div>
</template>
<script lang="ts" setup>
import ZoomMtgEmbedded from "@zoomus/websdk/embedded";
import ZoomMtg from "@zoomus/websdk";
import KJUR from "jsrsasign";
import { ref, Ref, onBeforeMount, onMounted } from "vue";
import axios from "axios";
const payload = ref({
  client: ZoomMtgEmbedded.createClient(),
  sdkKey: "u1RAQJFeSWTI2CSt8UWLA",
  sdkSecret: "91GQB2b74FCvHMEpJ5fhujlTA2VKVPNc",
  meetingNumber: 87137618028,
  password: "tx9a8a",
  user: "Duy Minh",
  userEmail: "ddminh.19it5@vku.udn.vn",
  role: 0,
});
function generateSignature() {
  const iat = Math.round(new Date().getTime() / 1000) - 30;
  const exp = iat + 60 * 60 * 2;
  const oHeader = { alg: "HS256", typ: "JWT" };

  const oPayload = {
    sdkKey: payload.value.sdkKey,
    appKey: payload.value.sdkKey,
    mn: payload.value.meetingNumber,
    role: payload.value.role,
    iat: iat,
    exp: exp,
    tokenExp: exp,
  };

  const sHeader = JSON.stringify(oHeader);
  const sPayload = JSON.stringify(oPayload);
  const sdkJWT = KJUR.jws.JWS.sign(
    "HS256",
    sHeader,
    sPayload,
    payload.value.sdkSecret
  );
  return sdkJWT;
}

function joinMeeting() {
  let meetingSDKElement = document.getElementById("meetingSDKElement");
  payload.value.client.init({
    debug: true,
    zoomAppRoot: meetingSDKElement,
    language: "en-US",
    customize: {
      meetingInfo: [
        "topic",
        "host",
        "mn",
        "pwd",
        "telPwd",
        "invite",
        "participant",
        "dc",
        "enctype",
      ],
      toolbar: {
        buttons: [
          {
            text: "Custom Button",
            className: "CustomButton",
            onClick: () => {
              console.log("custom button");
            },
          },
        ],
      },
    },
  });
  let sig = generateSignature();
  payload.value.client.join({
    signature: sig,
    sdkKey: payload.value.sdkKey,
    meetingNumber: Number(payload.value.meetingNumber),
    password: payload.value.password,
    userName: payload.value.user,
    userEmail: payload.value.userEmail,
  });
}
onMounted(() => {
  joinMeeting();
});
</script>
