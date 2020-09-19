<template>
    <!--组件标签化使用-->
    <ListRender/>
</template>

<script>
    //引入组件，组件名称不要小写，可能引起报错。
    import ListRender from './components/ListRender.vue'

    export default {
        name: 'App',
        //注册组件
        components: {
            //注册多个组件使用逗号分隔
            ListRender
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
