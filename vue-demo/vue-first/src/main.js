import {createApp} from 'vue';
import App from './App.vue';

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
const app = createApp(App);
/**
 自定义大写指令
 */
app.directive('upper-word', {
    // When the bound element is mounted into the DOM...
    mounted(el, binding) {
        // Focus the element
        console.log(el);
        console.log(binding);
        el.textContent = binding.value.toUpperCase();
    }
});

app.mount('#app');