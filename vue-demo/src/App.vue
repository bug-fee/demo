<template>
    <PubSubComponent/>
</template>

<script>
    //引入组件，组件名称不要小写，可能引起报错。
    import PubSubComponent from './components/PubSubComponent.vue';

    export default {
        name: 'App',
        //注册组件
        components: {
            //注册多个组件使用逗号分隔
            // HelloWorld, //双括号表达式
            // BothWayBind, //双向绑定
            // ClassAndStyle, //属性绑定
            // ComputedAndWatch, //计算属性和监视
            // EventMonitor, //事件绑定
            // IfAndShow, //if else show 逻辑输出/显示隐藏属性
            // ListRender, //动态遍历和展示
            // ListRenderTwo,//搜索过滤、升序排序
            // OtherInstruct,//其他指令
            // dealEvent,//事件常用修饰符
            // customEventFather,//自定义事件，组件参数传递
            // LKFilters,//局部过滤器
            // TransitionAndAnimation,//过渡和动画
            // TransitionAndAnimationTwo, //自定义动画
            // TransitionAndAnimationThree, //集成第三方动画插件animate
            // LifeCircle, //vue 组件生命周期
            // ParentComponent, //props，父组件向子组件通信
            PubSubComponent, //pubsub插件使用，实现消息订阅组件通信
        },

    }
</script>

<style>
    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
