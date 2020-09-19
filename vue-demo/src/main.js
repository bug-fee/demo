import { createApp } from 'vue'
import App from './App.vue'

/**
 * mount,手动挂载
 * ES5
 * render:function(createElement){
 *     return createElement(App)
 * }
 *
 * Es6
 * render(createElement){
 *     return createLement(App)
 * }
 *
 * render : h => h(App);
 */
createApp(App).mount('#app');
