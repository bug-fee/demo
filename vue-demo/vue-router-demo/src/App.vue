<template>
    <div id="app">
        <!--设置路由导航-->
        <div id="nav">
            <router-link to="/home">首页</router-link>
            <router-link to="/about">关于</router-link>
            <router-link to="/mine/张三">我的</router-link>
        </div>
        <!--设置路由出口-->
        <router-view></router-view>
    </div>
</template>

<script>

    export default {
        name: 'App',
        components: {}
    }
</script>

<style>
    #nav {
        text-align: center;
        padding: 20px;
    }

    #nav a {
        padding: 10px;
    }

    /*设置选中路由的样式*/
    #nav a.router-link-exact-active {
        color: red;
    }
</style>
