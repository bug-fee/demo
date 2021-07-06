import Vue from 'vue';
import App from './App.vue';
import router from './router';

Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App),
}).$mount('#app');

$api.post("Course/StudyLog", {couid: '779', olid: '9386', playTime: '100', studyTime: '883', totalTime: '883'}, function () {vdata.studylogUpdate = true;}, function () {vdata.studylogUpdate = false;}).then(function (req) {if (!req.data.success) {if (vdata.playready()) {vdata.player.pause();vdata.player.destroy();vdata.player = null;}alert(req.data.message);return;}vdata.studylogState = 1;window.setTimeout(function () {vdata.studylogState = 0;}, 2000);}).catch(function (err) {vdata.studylogState = -1;/*alert(err);*/window.setTimeout(function () {vdata.studylogState = 0;}, 2000);});