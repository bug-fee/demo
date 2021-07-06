import Vue from 'vue'
import VueRouter from 'vue-router'

// 一级路由，引入页面
import Home from './views/Home'
import About from './views/About'
import Mine from './views/Mine'
import Props from './views/Props'

// 二级路由，引入页面
import News from './views/News';
import Shop from './views/Shop';

Vue.use(VueRouter);

//路由通过自定义函数传递props，切记要写在引用上面，否则不生效
/*let func = (route) => {
    console.log(route);
    return {
        height: route.query.height,
        name: route.params.name
    }
};*/
//使用ES6 语法 接收入参
let func = ({params, query}) => {
    //直接获取路由对象里面的params和query对象
    console.log(params);
    console.log(query);
    return {
        height: query.height,
        name: params.name
    }
};

// 暴露路由入口
export default new VueRouter({
    // vue-router 默认 hash 模式（地址栏#号后面代表路由地址），可能影响SEO排行因素。可以配置为传统history模式。
    // mode: 'history',
    routes: [
        // 配置重定向路径，访问根目录时，定向到home组件
        // {path: '/', redirect: '/home'},
        // 使用name来标识路由的唯一对象
        // {path: '/', redirect: {name: 'home'}},
        // 使用匿名函数来定向路由
        {
            path: '/', redirect: (to) => {
                // 输出请求对象，含全路径、参数等元数据信息
                console.log(to)
                return '/about'
            }
        },
        // {name: 'home', path: '/home', component: Home},
        {name: 'about', path: '/about', component: About},
        // 配置使用别名
        // /a 的别名是 /b，意味着，当用户访问 /b 时，URL 会保持为 /b，但是路由匹配则为 /a，就像用户访问 /a 一样。
        // “别名”的功能让你可以自由地将 UI 结构映射到任意的 URL，而不是受限于配置的嵌套路由结构。
        // {path: '/a', component: A, alias: '/b'},
        /*
         * 动态路由，匹配路径为127.0.0.1:8080/#/mine/张三，:name、:sex作为动态参数被传入，通过this.$route获取参数
         */
        {name: 'mine', path: '/mine/:name/:sex', component: Mine},
        // 通过props静态传递参数，Component端使用props:['name']绑定参数
        {name: 'props', path: '/props', component: Props, props: {name: '张三'}},
        // props 被设置为 true，route.params 将会被设置为组件属性,component上使用props：['name']绑定参数
        {name: 'static-props', path: '/static/props/:name', component: Props, props: true},
        // 通过函数转换传递props
        {name: 'fun-props', path: '/fun/props/:name', component: Props, props: func},
        // 嵌套路由，注意此处嵌套路由的path前缀不能带/
        {
            name: 'home', path: '/home', component: Home,
            children: [
                // 嵌套路由使用重定向
                {path: '/home', redirect: '/home/news'},
                {path: 'news', component: News},
                {path: 'shop', component: Shop}
            ]
        },

    ]
});
