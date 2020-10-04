import Vue from 'vue'
import { Button, Select } from 'element-ui';
import App from './App.vue'

Vue.config.productionTip = false
Vue.use(Button);
Vue.use(Select);

new Vue({
  render: h => h(App),
}).$mount('#app')
